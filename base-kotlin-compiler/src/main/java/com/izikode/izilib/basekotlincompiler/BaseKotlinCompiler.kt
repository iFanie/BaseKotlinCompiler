package com.izikode.izilib.basekotlincompiler

import com.izikode.izilib.basekotlincompiler.component.CompilationRound
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
     * Array of annotation classes processed by this compiler.
     */
    abstract val processes: Array<KClass<out Any>>

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val types = arrayListOf<String>()
        processes.forEach { types.add(it.java.canonicalName) }

        return TreeSet(types)
    }

    /**
     * Utilities for completing compilation rounds.
     */
    protected lateinit var compilationUtilities: CompilationUtilities

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)

        compilationUtilities = CompilationUtilities(
            ClassGenerator(File(processingEnv.options[KOTLIN_GENERATED_SOURCE])),
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

    /**
     * Invoked on every processing round.
     */
    abstract fun handle(compilationRound: CompilationRound)

    /**
     * Invoked after the last processing round ends.
     */
    abstract fun finally()

}
