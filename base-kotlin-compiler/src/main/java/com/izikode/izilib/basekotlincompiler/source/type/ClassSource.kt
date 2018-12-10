package com.izikode.izilib.basekotlincompiler.source.type

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import com.izikode.izilib.basekotlincompiler.source.AbstractSource
import com.izikode.izilib.basekotlincompiler.source.enclosure.EnclosureSource
import com.izikode.izilib.basekotlincompiler.source.member.field.AnnotatedVariableSource
import com.izikode.izilib.basekotlincompiler.source.member.method.AnnotatedFunctionSource
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.lang.model.util.ElementFilter
import kotlin.reflect.KClass

open class ClassSource(

        protected val typeElement: TypeElement,
        compilationUtilities: CompilationUtilities

) : AbstractSource(typeElement, compilationUtilities) {

    val parent by lazy { EnclosureSource(typeElement.enclosingElement as PackageElement, compilationUtilities) }
    val info by lazy { Info(typeElement) }

    fun <VariableAnnotation> variablesWith(annotation: KClass<out VariableAnnotation>)
            : ArrayList<AnnotatedVariableSource<VariableAnnotation>> where VariableAnnotation : Annotation {

        val variables = arrayListOf<AnnotatedVariableSource<VariableAnnotation>>()

        ElementFilter.fieldsIn(typeElement.enclosedElements).forEach { variable ->
            variable.getAnnotation(annotation.java)?.let { annotation ->
                variables.add(AnnotatedVariableSource(
                        variable,
                        annotation,
                        compilationUtilities
                ))
            }
        }

        return variables
    }

    fun <FunctionAnnotation> functionsWith(annotation: KClass<out FunctionAnnotation>)
            : ArrayList<AnnotatedFunctionSource<FunctionAnnotation>> where FunctionAnnotation : Annotation {

        val methods = arrayListOf<AnnotatedFunctionSource<FunctionAnnotation>>()

        ElementFilter.methodsIn(typeElement.enclosedElements).forEach { method ->
            method.getAnnotation(annotation.java)?.let { annotation ->
                methods.add(AnnotatedFunctionSource(
                        method,
                        annotation,
                        compilationUtilities
                ))
            }
        }

        return methods
    }

    override fun toString(): String = typeElement.toString()

    class Info(private val typeElement: TypeElement) {

        val name: String
            get() = typeElement.asType().toString()

    }

}