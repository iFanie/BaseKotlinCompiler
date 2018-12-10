package com.izikode.izilib.basekotlincompiler.source.member.field

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import javax.lang.model.element.VariableElement

class AnnotatedVariableSource<VariableAnnotation>(

        variableElement: VariableElement,
        val annotation: VariableAnnotation,
        compilationUtilities: CompilationUtilities

) : VariableSource(variableElement, compilationUtilities) where VariableAnnotation : Annotation {

    override fun toString(): String = super.toString()

}