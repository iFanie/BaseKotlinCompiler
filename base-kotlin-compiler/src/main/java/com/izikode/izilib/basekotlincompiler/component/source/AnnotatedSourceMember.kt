package com.izikode.izilib.basekotlincompiler.component.source

import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit
import javax.lang.model.element.Element

class AnnotatedSourceMember<AnnotationInstance>(

        element: Element,
        processingKit: ProcessingKit,
        val annotation: AnnotationInstance

) : SourceMember(element, processingKit) where AnnotationInstance : Annotation {

}