package com.livefront.sealedenum.compilation.kitchensink;

import java.util.Collection;

public class JavaSecondBaseClass<A, B, C extends Collection<String>>
        extends KotlinSecondBaseClass<A, B, C, JavaInterface3<C>>
        implements KotlinInterface5<KotlinInterface1>, JavaInterface3<A> {
}
