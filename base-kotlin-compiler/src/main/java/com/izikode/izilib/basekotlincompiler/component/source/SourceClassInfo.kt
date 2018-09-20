package com.izikode.izilib.basekotlincompiler.component.source

import javax.lang.model.element.Element

data class SourceClassInfo(

        private val element: Element

) {

    val className get() = element.asType().toString()

}