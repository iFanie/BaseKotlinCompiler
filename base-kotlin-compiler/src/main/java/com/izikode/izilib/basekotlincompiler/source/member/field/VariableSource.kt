package com.izikode.izilib.basekotlincompiler.source.member.field

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import com.izikode.izilib.basekotlincompiler.source.member.AbstractMemberSource
import javax.lang.model.element.VariableElement

open class VariableSource(

        protected val variableElement: VariableElement,
        compilationUtilities: CompilationUtilities

) : AbstractMemberSource(variableElement, compilationUtilities) {

    override fun toString(): String = variableElement.toString()

}