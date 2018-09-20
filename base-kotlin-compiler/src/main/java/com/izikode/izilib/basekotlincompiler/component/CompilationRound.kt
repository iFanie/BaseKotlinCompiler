package com.izikode.izilib.basekotlincompiler.component

import com.izikode.izilib.basekotlincompiler.component.source.AnnotatedSourceClass
import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import kotlin.reflect.KClass

data class CompilationRound(

        private val processingKit: ProcessingKit,

        private val annotations: MutableSet<out TypeElement>,
        private val roundEnvironment: RoundEnvironment

) {

    fun <AnnotationType> classesWith(annotation: KClass<out AnnotationType>)
            : ArrayList<AnnotatedSourceClass<AnnotationType>> where AnnotationType : Annotation {

        val classes = arrayListOf<AnnotatedSourceClass<AnnotationType>>()

        roundEnvironment.getElementsAnnotatedWith(annotation.java).forEach {
            classes.add(AnnotatedSourceClass(it, processingKit, it.getAnnotation(annotation.java)))
        }

        return classes
    }

}