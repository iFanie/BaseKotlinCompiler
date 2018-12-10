package com.izikode.izilib.basekotlincompiler.source.member.method

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import com.izikode.izilib.basekotlincompiler.source.member.AbstractMemberSource
import javax.lang.model.element.ExecutableElement

open class FunctionSource(

        protected val executableElement: ExecutableElement,
        compilationUtilities: CompilationUtilities

) : AbstractMemberSource(executableElement, compilationUtilities) {

    override fun toString(): String = executableElement.toString()

}