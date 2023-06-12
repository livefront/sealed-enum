# ksp-java-tests

Tests that verify the KSP processor's Java-specific functionality.

This module uses shared `commonMain`, `jvmMain`, `commonTest` and `jvmTest` code from [common/src](../common/src), and also uses shared `commonMain` and `commonTest` code from [ksp-common-tests/src](../ksp-common-tests/src).

This module exists because we want to apply `ksp` only to `jvmMain` code, and we can't do that while also applying `ksp` to `commonMain` code.
