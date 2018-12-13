package com.izikode.izilib.basekotlincompiler

import com.izikode.izilib.basekotlincompiler.component.CompilationRound
import com.izikode.izilib.basekotlincompiler.source.member.field.AnnotatedVariableSource
import com.izikode.izilib.basekotlincompiler.source.member.method.AnnotatedFunctionSource
import com.izikode.izilib.basekotlincompiler.source.type.AnnotatedClassSource
import com.izikode.izilib.basekotlincompiler.utility.ClassGenerator
import com.izikode.izilib.basekotlincompiler.utility.Printer
import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit
import java.io.File
import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import kotlin.reflect.KClass

abstract class BaseKotlinCompiler : AbstractProcessor() {

    override fun getSupportedOptions(): MutableSet<String> = mutableSetOf(KOTLIN_GENERATED_SOURCE)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.RELEASE_8

    /**
     * Provides the directory for generated source classes.
     * <i>By default, uses the [KOTLIN_GENERATED_SOURCE] argument.</i>
     *
     * @param options  The processing environment arguments.
     * @return The generated source directory.
     */
    open fun getGeneratedSourceDirectory(options: Map<String, String>) = File(options[KOTLIN_GENERATED_SOURCE])

    /**
     * Array of annotation classes processed by this compiler.
     */
    abstract val processes: Array<KClass<out Any>>

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val types = mutableSetOf<String>()
        processes.forEach { types.add(it.java.canonicalName) }

        return types
    }

    /**
     * Utilities for completing compilation rounds.
     */
    protected lateinit var compilationUtilities: CompilationUtilities

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)

        compilationUtilities = CompilationUtilities(
                processingEnv.options,
                ClassGenerator(getGeneratedSourceDirectory(processingEnv.options)),
                Printer(processingEnv.messager),
                ProcessingKit(processingEnv.elementUtils, processingEnv.typeUtils)
        )
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        handle(CompilationRound(compilationUtilities, annotations, roundEnv))

        if (roundEnv.processingOver()) {
            finally()
        }

        return true
    }

    private fun handle(compilationRound: CompilationRound) {
        object : CompilationRoundHandler(compilationRound) {

            override fun handleRound() {
                roundHandler()
            }

        }
    }

    private fun finally(compilationUtilities: CompilationUtilities) {
        object : FinallyHandler(compilationUtilities) {

            override fun handleFinally() {
                finallyHandler()
            }

        }
    }

    /**
     * Invoked on every processing round.
     */
    abstract val roundHandler: CompilationRoundHandler.() -> Unit

    /**
     * Invoked after the last processing round ends.
     */
    abstract val finallyHandler: FinallyHandler.() -> Unit

    abstract class CompilationRoundHandler(private val compilationRound: CompilationRound) {

        protected abstract fun handleRound()

        fun <ClassAnnotation : Annotation> fetchClasses(
                classAnnotation: KClass<ClassAnnotation>,
                block: (ArrayList<AnnotatedClassSource<ClassAnnotation>>) -> Unit) {

            block(compilationRound.classesWith(classAnnotation))
        }

        fun <VariableAnnotation : Annotation> fetchVariables(
                variableAnnotation: KClass<VariableAnnotation>,
                block: (ArrayList<AnnotatedVariableSource<VariableAnnotation>>) -> Unit) {

            block(compilationRound.variablesWith(variableAnnotation))
        }

        fun <FunctionAnnotation : Annotation> fetchFunctions(
                functionAnnotation: KClass<FunctionAnnotation>,
                block: (ArrayList<AnnotatedFunctionSource<FunctionAnnotation>>) -> Unit) {

            block(compilationRound.functionsWith(functionAnnotation))
        }

    }

    abstract class FinallyHandler(private val compilationUtilities: CompilationUtilities) {

        protected abstract fun handleFinally()

        fun generateClass(block: () -> AbstractKotlinClass) {
            compilationUtilities.classGenerator.generate(block())
        }

        fun generateClasses(block: () -> Array<out AbstractKotlinClass>) {
            block().forEach {
                compilationUtilities.classGenerator.generate(it)
            }
        }

    }

}
