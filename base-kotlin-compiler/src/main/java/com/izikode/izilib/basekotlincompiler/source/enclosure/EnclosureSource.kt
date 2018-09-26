package com.izikode.izilib.basekotlincompiler.source.enclosure

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import com.izikode.izilib.basekotlincompiler.source.AbstractSource
import javax.lang.model.element.PackageElement

class EnclosureSource(

        protected val packageElement: PackageElement,
        compilationUtilities: CompilationUtilities

) : AbstractSource(packageElement, compilationUtilities) {

    val info by lazy { Info(packageElement) }

    class Info(private val packageElement: PackageElement) {

        val name: String
            get() = packageElement.qualifiedName.toString()

    }

}