package com.izikode.izilib.basekotlincompiler.utility

import com.izikode.izilib.basekotlincompiler.AbstractKotlinClass
import java.io.File

class ClassGenerator(private val sourceDir: File) {

    /**
     * Creates a Kotlin class file from the provided data.
     *
     * @param kotlinClass  The class to be generated
     * @return {@code true} on success, {@code false} on failure
     */
    fun generate(kotlinClass: AbstractKotlinClass): Boolean
            = try {

                File(

                        /* Source file parent directory */
                        File(sourceDir, kotlinClass.relativePath).apply { mkdirs() },

                        /* Source file name */
                        "${kotlinClass.simpleName}.kt"

                ).writeText(cleanup(kotlinClass.sourceCode))

                /* Generation successful */
                true

            } catch (ignored: Exception) {

                /* Generation failed */
                false

            }

    private fun cleanup(sourceCode: String): String {
        var source = sourceCode.trim()

        while(source.contains("\n\n    \n")) {
            source = source.replace("\n\n    \n", "\n\n\n")
        }

        while(source.contains("\n    \n")) {
            source = source.replace("\n    \n", "\n\n")
        }

        while(source.contains("\n\n\t\n")) {
            source = source.replace("\n\n\t\n", "\n\n\n")
        }

        while(source.contains("\n\t\n")) {
            source = source.replace("\n\t\n", "\n\n")
        }

        while(source.contains("\n\n\n")) {
            source = source.replace("\n\n\n", "\n\n")
        }

        return source
    }

}