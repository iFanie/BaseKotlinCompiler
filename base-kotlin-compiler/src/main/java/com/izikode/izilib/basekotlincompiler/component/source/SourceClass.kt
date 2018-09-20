package com.izikode.izilib.basekotlincompiler.component.source

import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit
import javax.lang.model.element.Element
import kotlin.reflect.KClass

open class SourceClass(

        private val element: Element,
        private val processingKit: ProcessingKit

) {

    val info = SourceClassInfo(element)

    fun <AnnotationType> membersWith(annotation: KClass<out AnnotationType>)
            : ArrayList<AnnotatedSourceMember<AnnotationType>> where AnnotationType : Annotation {

        val members = arrayListOf<AnnotatedSourceMember<AnnotationType>>()

        processingKit.getAllMembers(element)?.forEach { member ->
            member.getAnnotation(annotation.java)?.let {
                members.add(AnnotatedSourceMember(member, processingKit, it))
            }
        }

        return members
    }

}