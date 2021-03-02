# 0.3.0
## Breaking changes:
- Artifact ids were updated (`sealedenum` -> `runtime` and `sealedenumprocessor` -> `processor`).
  This was done to accomodate alternate generation implementations, such as by [KSP](https://github.com/google/ksp)

## Miscellaneous updates:
- Switch to GitHub actions for CI
- Various dependency updates

# 0.2.0
## Features:
- Added extension properties and methods to make `sealed-enum` easier to use in common cases.
  In particular, access to `values`, ordinals, names and the `SealedEnum` implementation can all be achieved without having to use a generated class name.
- Added support for isomorphic enum generation via the `generateEnum` argument for `@GenSealedEnum`.
  Isomorphism and class information can be retrieved from the implemented `EnumForSealedEnumProvider` interface.

## Breaking changes:
- `@GenSealedEnum` must now be applied to the companion object of `sealed class`, rather than the `sealed class` itself.
- Processor option for auto-generation without `@GenSealedEnum` was removed.

## Miscellaneous updates:
- Updated to Kotlin 1.4
- Switched to [`kotlin-compile-testing`](https://github.com/tschuchortdev/kotlin-compile-testing) for testing
- Updated dependencies and created `buildSrc` for version management
- Generated code is explicit-api-mode compatible by default (courtesy of KotlinPoet 1.7.x)

# 0.1.1
Publish dokka documentation and sources

# 0.1.0
Initial preview release
