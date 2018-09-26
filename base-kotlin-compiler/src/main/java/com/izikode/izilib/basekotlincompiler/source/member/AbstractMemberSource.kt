package com.izikode.izilib.basekotlincompiler.source.member

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import com.izikode.izilib.basekotlincompiler.source.AbstractSource
import com.izikode.izilib.basekotlincompiler.source.type.ClassSource
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

abstract class AbstractMemberSource(

        element: Element,
        compilationUtilities: CompilationUtilities

) : AbstractSource(element, compilationUtilities) {

    val parent by lazy { ClassSource(element.enclosingElement as TypeElement, compilationUtilities) }
    val info by lazy { Info(element, compilationUtilities) }

    class Info(

            private val element: Element,
            private val compilationUtilities: CompilationUtilities

    ) {

        val name: String
            get() = element.simpleName.toString()

        val type: String
            get() = compilationUtilities.processingKit.getKotlinType(element)

    }

}