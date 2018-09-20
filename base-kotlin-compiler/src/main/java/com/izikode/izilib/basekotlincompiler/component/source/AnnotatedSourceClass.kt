package com.izikode.izilib.basekotlincompiler.component.source

import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit
import javax.lang.model.element.Element

class AnnotatedSourceClass<AnnotationInstance>(

        element: Element,
        processingKit: ProcessingKit,

        val annotation: AnnotationInstance

) : SourceClass(element, processingKit) where AnnotationInstance : Annotation {

}