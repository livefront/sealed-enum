# sealed-enum (Beta)
*Enums are dead, long live enums!*

![CI](https://github.com/livefront/sealed-enum/workflows/CI/badge.svg)
[![Release](https://jitpack.io/v/livefront/sealed-enum.svg)](https://jitpack.io/#livefront/sealed-enum)

Generates enum-like behavior for sealed classes of objects.

Just annotate the `companion object` with `@GenSealedEnum`, and you'll see generated extensions like `ordinal` and `values`:

```kotlin
sealed class Alpha {
    object Beta : Alpha()
    object Gamma : Alpha()
    
    @GenSealedEnum
    companion object
}

println(Beta.ordinal) // 0

println(Alpha.values) // [Alpha$Beta@491cc5c9, Alpha$Gamma@74ad1f1f]
```

This tool is currently in beta, while any issues are worked through. Please feel free to try it out and report any bugs that you may encounter.

### Background

[Enums in Kotlin](https://kotlinlang.org/docs/reference/enum-classes.html) are quite useful for managing state and control flows, especially in combination with [`when`](https://kotlinlang.org/docs/reference/control-flow.html#when-expression).

However, enums have a few drawbacks:
- [Enum classes can't have type parameters](https://discuss.kotlinlang.org/t/enum-class-with-type-parameters/)
- Enum classes can only implement interfaces, and can't be subclasses of other classes
- As a consequence of the above, enum classes can't have hierarchies (unlike [sealed classes](https://kotlinlang.org/docs/reference/sealed-classes.html))
- The full list of values can only be retrieved [generically in a reified way](https://kotlinlang.org/docs/reference/enum-classes.html#working-with-enum-constants)

Kotlin also has [sealed classes](https://kotlinlang.org/docs/reference/sealed-classes.html), which are
> ... in a sense, an extension of enum classes: the set of values for an enum type is also restricted, but each enum constant exists only as a single instance, whereas a subclass of a sealed class can have multiple instances which can contain state.

Sealed classes are certainly more powerful than enums, with a lot of the same benefits and can also be used to great effect with `when`.
However, the only way to retrieve a full list of a sealed class's subclasses [is with reflection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/sealed-subclasses.html) and they have no inherent ordinal value.

Now, suppose you have a sealed class that only has `object` subclasses (or a sealed subclass with only `object` subclasses, ad infinitum).

This restriction would allow defining a `values` list without any `KClass`s, with ordinals naturally derived from the order of the list.
For more complex hierarchies, the `values` list can be a well-defined order based on a [traversal of the sealed class hierarchy tree](https://en.wikipedia.org/wiki/Tree_traversal).

Creating these lists manually [is possible](https://discuss.kotlinlang.org/t/list-of-sealed-class-objects), but maintaining them is error prone and doesn't solve the problem generically.

In addition, unique names can be associated with these objects, and thus can be converted to and from a string representation.

Thus, we can define an interface `SealedEnum` that, when implemented, provides equivalent functionality to a normal enum, generically:

```kotlin
interface SealedEnum<T> : Comparator<T> {

    val values: List<T>

    fun ordinalOf(obj: T): Int

    fun nameOf(obj: T): String

    fun valueOf(name: String): T

    override fun compare(first: T, second: T) = ordinalOf(first) - ordinalOf(second)
}
```

This annotation processor automatically creates and maintains `SealedEnum` instances for sealed classes of only objects (sealed enums, if you will), creating something that is strictly more feature-rich and powerful than normal enums.

For interoperability, it is also possible to create a `SealedEnum` object from a normal enum class, as well as generating a mostly equivalent enum from a sealed enum that implements all of the sealed enum's interfaces by delegation.

### Usage

By applying `@GenSealedEnum` to the companion object of a sealed class with only object subclasses, an object implementing [`SealedEnum`](sealedenum/src/main/kotlin/com/livefront/sealedenum/SealedEnum.kt) for that sealed class will be generated.

For example,
```kotlin
sealed class Alpha {
    object Beta : Alpha()
    object Gamma : Alpha()
    
    @GenSealedEnum
    companion object
}
```

will generate the following object:
```kotlin
object AlphaSealedEnum : SealedEnum<Alpha> {
    override val values: List<Alpha> = listOf(
        Alpha.Beta,
        Alpha.Gamma
    )

    override fun ordinalOf(obj: Alpha): Int = when (obj) {
        Alpha.Beta -> 0
        Alpha.Gamma -> 1
    }

    override fun nameOf(obj: AlphaSealedEnum): String = when (obj) {
        Alpha.Beta -> "Alpha_Beta"
        Alpha.Gamma -> "Alpha_Gamma"
    }

    override fun valueOf(name: String): AlphaSealedEnum = when (name) {
        "Alpha_Beta" -> Alpha.Beta
        "Alpha_Gamma" -> Alpha.Gamma
        else -> throw IllegalArgumentException("""No sealed enum constant $name""")
    }
  }
```

For convenience, extension properties and methods will be added to the sealed class and its companion object:

```kotlin
val Alpha.ordinal: Int
    get() = AlphaSealedEnum.ordinalOf(this)

val Alpha.name: String
    get() = AlphaSealedEnum.nameOf(this)

val Alpha.Companion.values: List<Alpha>
    get() = AlphaSealedEnum.values

val Alpha.Companion.sealedEnum: AlphaSealedEnum
    get() = AlphaSealedEnum

fun Alpha.Companion.valueOf(name: String): Alpha = AlphaSealedEnum.valueOf(name)
```

These extension properties and methods allow for easy access to `SealedEnum` with a syntax that is extremely close to normal enums.

For nested hierarchies, the traversal order can be manually specified via `traversalOrder`, with a default value of `TreeTraversalOrder.IN_ORDER`. Multiple objects for different traversal orders can also be generated by repeating the annotation:
```kotlin
sealed class Alpha {
    sealed class Beta : Alpha() {
        object Gamma : Beta()
    }
    object Delta : Alpha()
    sealed class Epsilon : Alpha() {
        object Zeta : Epsilon()
    }

    @GenSealedEnum(traversalOrder = TreeTraversalOrder.IN_ORDER)
    @GenSealedEnum(traversalOrder = TreeTraversalOrder.LEVEL_ORDER)
    companion object
}
```
will generate two objects:
```kotlin
object AlphaLevelOrderSealedEnum : SealedEnum<Alpha> {
    override val values: List<Alpha> = listOf(
        Alpha.Delta,
        Alpha.Beta.Gamma,
        Alpha.Epsilon.Zeta
    )

    override fun ordinalOf(obj: Alpha): Int = when (obj) {
        Alpha.Delta -> 0
        Alpha.Beta.Gamma -> 1
        Alpha.Epsilon.Zeta -> 2
    }

    override fun nameOf(obj: AlphaLevelOrderSealedEnum): String = when (obj) {
        Alpha.Delta -> "Alpha_Delta"
        Alpha.Beta.Gamma -> "Alpha_Beta_Gamma"
        Alpha.Epsilon.Zeta -> "Alpha_Epsilon_Zeta"
    }

    override fun valueOf(name: String): AlphaLevelOrderSealedEnum = when (name) {
        "Alpha_Delta" -> Alpha.Delta
        "Alpha_Beta_Gamma" -> Alpha.Beta.Gamma
        "Alpha_Epsilon_Zeta" -> Alpha.Epsilon.Zeta
        else -> throw IllegalArgumentException("""No sealed enum constant $name""")
    }
}

object AlphaInOrderSealedEnum : SealedEnum<Alpha> {
    override val values: List<Alpha> = listOf(
        Alpha.Beta.Gamma,
        Alpha.Delta,
        Alpha.Epsilon.Zeta
    )

    override fun ordinalOf(obj: Alpha): Int = when (obj) {
        Alpha.Beta.Gamma -> 0
        Alpha.Delta -> 1
        Alpha.Epsilon.Zeta -> 2
    }

    override fun nameOf(obj: AlphaInOrderSealedEnum): String = when (obj) {
        Alpha.Beta.Gamma -> "Alpha_Beta_Gamma"
        Alpha.Delta -> "Alpha_Delta"
        Alpha.Epsilon.Zeta -> "Alpha_Epsilon_Zeta"
    }

    override fun valueOf(name: String): AlphaInOrderSealedEnum = when (name) {
        "Alpha_Beta_Gamma" -> Alpha.Beta.Gamma
        "Alpha_Delta" -> Alpha.Delta
        "Alpha_Epsilon_Zeta" -> Alpha.Epsilon.Zeta
        else -> throw IllegalArgumentException("""No sealed enum constant $name""")
    }
}
```

The extension properties and methods will also be prefixed with the traversal order to disambiguate them:
```kotlin
val Alpha.levelOrderOrdinal: Int
    get() = AlphaLevelOrderSealedEnum.ordinalOf(this)

val Alpha.levelOrderName: String
    get() = AlphaLevelOrderSealedEnum.nameOf(this)

val Alpha.Companion.levelOrderValues: List<Alpha>
    get() = AlphaLevelOrderSealedEnum.values

val Alpha.Companion.levelOrderSealedEnum: AlphaLevelOrderSealedEnum
    get() = AlphaLevelOrderSealedEnum

fun Alpha.Companion.levelOrderValueOf(name: String): Alpha = AlphaLevelOrderSealedEnum.valueOf(name)

val Alpha.inOrderOrdinal: Int
    get() = AlphaInOrderSealedEnum.ordinalOf(this)

val Alpha.inOrderName: String
    get() = AlphaInOrderSealedEnum.nameOf(this)

val Alpha.Companion.inOrderValues: List<Alpha>
    get() = AlphaInOrderSealedEnum.values

val Alpha.Companion.inOrderSealedEnum: AlphaInOrderSealedEnum
    get() = AlphaInOrderSealedEnum

fun Alpha.Companion.inOrderValueOf(name: String): Alpha = AlphaInOrderSealedEnum.valueOf(name)
```

The runtime library includes support from creating a `SealedEnum` from a normal enum class, with `createSealedEnumFromEnum()` and `createSealedEnumFromEnumArray(values: Array<E>, enumClass: Class<E>)`.

If `generateEnum` is set to `true` on the `@GenSealedEnum` annotation, then an isomorphic enum class will be generated for the sealed class.

The generated object will implement both `SealedEnum` and [`EnumForSealedEnumProvider`](sealedenum/src/main/kotlin/com/livefront/sealedenum/EnumForSealedEnumProvider.kt), which specifies the isomorphism and can provide the underlying `Class` for the enum class:

```kotlin
interface EnumForSealedEnumProvider<T, E : Enum<E>> {

    fun sealedObjectToEnum(obj: T): E

    fun enumToSealedObject(enum: E): T

    val enumClass: Class<E>
}
```

For example,
```kotlin
sealed class Alpha {
    object Beta : Alpha()
    object Gamma : Alpha()

    @GenSealedEnum(generateEnum = true)
    companion object
}
```

will also generate the following enum class and properties:
```kotlin
enum class AlphaEnum {
    Alpha_Beta,
    Alpha_Gamma
}

val Alpha.enum: AlphaEnum
    get() = AlphaSealedEnum.sealedObjectToEnum(this)

val AlphaEnum.sealedObject: Alpha
    get() = AlphaSealedEnum.enumToSealedObject(this)
    
object AlphaSealedEnum : SealedEnum<Alpha>, SealedEnumWithEnumProvider<Alpha, AlphaEnum>,
        EnumForSealedEnumProvider<Alpha, AlphaEnum> {
    ...

    override val values: List<Alpha> = listOf(
        Alpha.Beta,
        Alpha.Gamma
    )

    override fun sealedObjectToEnum(obj: Alpha): AlphaEnum = when (obj) {
        Alpha.Beta -> AlphaEnum.Alpha_Beta
        Alpha.Gamma -> AlphaEnum.Alpha_Gamma
    }

    override fun enumToSealedObject(enum: AlphaEnum): Alpha = when (enum) {
        AlphaEnum.Alpha_Beta -> Alpha.Beta
        AlphaEnum.Alpha_Gamma -> Alpha.Gamma
    }
}
```

The generated enum will implement any interface that the sealed class or any of its superclasses implement, with their implementations delegated to the isomorphic sealed objects.
See below for details on how generics are handled.

### Generic Handling

Because the generated `SealedEnum` implementations are objects, they cannot have type parameters, even though the underlying sealed class might.

To workaround this, the generated code attempts to match the bounds set by the sealed class, if it is able to.
If it can't, due to variance or multiple bounds, it simply use a wildcard `*` instead.

| Sealed Class | Sealed Enum |
| --- | --- |
| `sealed class Alpha`                              | `object AlphaSealedEnum : SealedEnum<Alpha>` |
| `sealed class Beta<T>`                            | `object BetaSealedEnum : SealedEnum<Any?>` |
| `sealed class Gamma<T : Omega>`                   | `object GammaSealedEnum : SealedEnum<Omega>` |
| `sealed class Delta<T> where T : Omega, T : Psi`  | `object DeltaSealedEnum : SealedEnum<Delta<*>>` |
| `sealed class Epsilon<out T : Omega>`             | `object EpsilonSealedEnum : SealedEnum<Epsilon<*>>` |
| `sealed class Zeta<in T : Omega>`                 | `object ZetaSealedEnum : SealedEnum<Zeta<*>>` |
| `sealed class Eta<out T>`                         | `object EtaSealedEnum : SealedEnum<Eta<*>>` |
| `sealed class Theta<in T>`                        | `object ThetaSealedEnum : SealedEnum<Theta<*>>` |
| `sealed class Iota<T : Omega, in U : Psi>`        | `object IotaSealedEnum : SealedEnum<Iota<Omega, *>>` |

Similarly, the interfaces implemented by generated enum classes cannot directly have any variance or wildcards, so only simple generics will work.
To avoid failing compilation, interfaces that are impossible to specify will be skipped.

| Sealed Class | Enum Class |
| --- | --- |
| `sealed class Alpha`                                              | `enum class AlphaEnum` |
| `sealed class Beta<T> : Sigma<T>`                                 | `enum class BetaEnum(sealedObject : Beta<Any?>) : Sigma<Any?> by sealedObject` |
| `sealed class Gamma<T : Omega> : Sigma<T>`                        | `enum class GammaEnum(sealedObject : Gamma<Omega>) : Sigma<Omega> by sealedObject` |
| `sealed class Delta<T> : Tau, Sigma<T> where T : Omega, T : Psi`  | `// Sigma skipped`<br/>`enum class DeltaEnum(sealedObject: Delta<*>) : Tau` |
| `sealed class Epsilon<out T : Omega> : Sigma<T>`                  | `// Sigma skipped`<br/>`enum class EpsilonEnum` |
| `sealed class Zeta<in T : Omega> : Sigma<T>`                      | `// Sigma skipped`<br/>`enum class ZetaEnum` |
| `sealed class Eta<out T> : Tau, Sigma<T>`                         | `// Sigma skipped`<br/>`enum class EtaEnum(sealedObject: Eta<*>) : Tau` |
| `sealed class Theta<in T> : Tau, Sigma<T>`                        | `// Sigma skipped`<br/>`enum class ThetaEnum(sealedObject: Theta<*>) : Tau` |
| `sealed class Iota<T : Omega, in U : Psi> : Sigma<T>, Upsilon<U>` | `// Upsilon skipped`<br/>`enum class IotaEnum(sealedObject: Iota<Omega, *>): Sigma<Omega>` |
| `sealed class Kappa<out T> : Sigma<Upsilon<T>>`                   | `enum class KappaEnum(sealedObject: Kappa<*>) : Sigma<Upsilon<*>>` |

### Installation

The code generation portion of `sealed-enum` can be performed in one of two ways, either using `kapt` or [`ksp`](https://github.com/google/ksp).

[`ksp`](https://github.com/google/ksp) is itself experimental, but the `ksp` processor for `sealed-enum` is equivalent to the `kapt` processor (or at least, they pass the same tests!)

#### Via `kapt`

```kotlin
plugins {
    kotlin("kapt")
}

repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.livefront.sealed-enum:runtime:0.3.0")
    kapt("com.github.livefront.sealed-enum:processor:0.3.0")
}
```

#### Via `ksp`

```kotlin
plugins {
    id("com.google.devtools.ksp") version "1.4.30-1.0.0-alpha04"
}

repositories {
    google()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.livefront.sealed-enum:runtime:0.3.0")
    ksp("com.github.livefront.sealed-enum:ksp:0.3.0")
}
```

### License
```
Copyright 2020 Livefront

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
