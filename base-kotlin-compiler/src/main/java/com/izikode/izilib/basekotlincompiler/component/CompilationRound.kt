package com.izikode.izilib.basekotlincompiler.component

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import com.izikode.izilib.basekotlincompiler.source.member.field.AnnotatedVariableSource
import com.izikode.izilib.basekotlincompiler.source.member.method.AnnotatedFunctionSource
import com.izikode.izilib.basekotlincompiler.source.type.AnnotatedClassSource
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.util.ElementFilter
import kotlin.reflect.KClass

data class CompilationRound(

        private val compilationUtilities: CompilationUtilities,

        private val annotations: MutableSet<out TypeElement>,
        private val roundEnvironment: RoundEnvironment

) {

    fun <ClassAnnotation> classesWith(annotation: KClass<out ClassAnnotation>)
            : ArrayList<AnnotatedClassSource<ClassAnnotation>> where ClassAnnotation : Annotation {

        val classes = arrayListOf<AnnotatedClassSource<ClassAnnotation>>()

        ElementFilter.typesIn(roundEnvironment.getElementsAnnotatedWith(annotation.java)).forEach {
            classes.add(AnnotatedClassSource(
                    it,
                    it.getAnnotation(annotation.java),
                    compilationUtilities
            ))
        }

        return classes
    }

    fun <VariableAnnotation> variablesWith(annotation: KClass<out VariableAnnotation>)
            : ArrayList<AnnotatedVariableSource<VariableAnnotation>> where VariableAnnotation : Annotation {

        val variables = arrayListOf<AnnotatedVariableSource<VariableAnnotation>>()

        ElementFilter.fieldsIn(roundEnvironment.getElementsAnnotatedWith(annotation.java)).forEach {
            variables.add(AnnotatedVariableSource(
                    it,
                    it.getAnnotation(annotation.java),
                    compilationUtilities
            ))
        }

        return variables
    }

    fun <FunctionAnnotation> functionsWith(annotation: KClass<out FunctionAnnotation>)
            : ArrayList<AnnotatedFunctionSource<FunctionAnnotation>> where FunctionAnnotation : Annotation {

        val methods = arrayListOf<AnnotatedFunctionSource<FunctionAnnotation>>()

        ElementFilter.methodsIn(roundEnvironment.getElementsAnnotatedWith(annotation.java)).forEach {
            methods.add(AnnotatedFunctionSource(
                    it,
                    it.getAnnotation(annotation.java),
                    compilationUtilities
            ))
        }

        return methods
    }

}