package com.izikode.izilib.basekotlincompiler.component.source

import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit
import javax.lang.model.element.Element

open class SourceMember(

        private val element: Element,
        private val processingKit: ProcessingKit

) {

    val info = SourceMemberInfo(element, processingKit)

}