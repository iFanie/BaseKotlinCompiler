package com.izikode.izilib.basekotlincompiler.component.source

import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit
import javax.lang.model.element.Element

data class SourceMemberInfo(

        private val element: Element,
        private val processingKit: ProcessingKit

) {

    val memberName = element.simpleName.toString()
    val memberType = processingKit.getKotlinType(element)

}