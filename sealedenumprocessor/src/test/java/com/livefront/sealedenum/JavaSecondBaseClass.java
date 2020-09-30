package com.livefront.sealedenum;

import java.util.Collection;

public class JavaSecondBaseClass<A, B, C extends Collection<String>>
        extends KotlinSecondBaseClass<A, B, C, JavaInterface3<C>>
        implements JavaBaseClassesInterface5<JavaBaseClassesInterface1>, JavaInterface3<A> {
}
