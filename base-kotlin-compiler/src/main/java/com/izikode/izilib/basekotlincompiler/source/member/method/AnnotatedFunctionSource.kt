package com.izikode.izilib.basekotlincompiler.source.member.method

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import javax.lang.model.element.ExecutableElement

class AnnotatedFunctionSource<FunctionAnnotation>(

        executableElement: ExecutableElement,
        val annotation: FunctionAnnotation,
        compilationUtilities: CompilationUtilities

) : FunctionSource(executableElement, compilationUtilities) where FunctionAnnotation : Annotation {

    override fun toString(): String = super.toString()

}