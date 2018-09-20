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
     * Class generator.
     */
    protected lateinit var generator: ClassGenerator

    /**
     * Handles message printing.
     */
    protected lateinit var printer: Printer

    /**
     * Kit of annotation processing utilities.
     */
    protected lateinit var processingKit: ProcessingKit

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)

        generator = ClassGenerator(File(processingEnv.options[KOTLIN_GENERATED_SOURCE]))
        printer = Printer(processingEnv.messager)
        processingKit = ProcessingKit(processingEnv.elementUtils, processingEnv.typeUtils)
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        handle(CompilationRound(processingKit, annotations, roundEnv))

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
