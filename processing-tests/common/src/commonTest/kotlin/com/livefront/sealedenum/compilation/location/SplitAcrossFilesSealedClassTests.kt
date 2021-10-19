package com.livefront.sealedenum.compilation.location

import kotlin.test.Test
import kotlin.test.assertEquals

class SplitAcrossFilesSealedClassTests {
    @Test
    fun object_split_across_files() {
        assertEquals(
            listOf(
                SplitAcrossFilesSubclassA,
                SplitAcrossFilesSubclassB,
                SplitAcrossFilesSubclassC
            ),
            SplitAcrossFilesSealedClass.values
        )
    }

    @Test
    fun enum_for_objects_split_across_files() {
        assertEquals(
            SplitAcrossFilesSealedClass.values.map(SplitAcrossFilesSealedClass::enum),
            enumValues<SplitAcrossFilesSealedClassEnum>().toList()
        )
    }
}
