package com.izikode.izilib.basekotlincompiler.source.type

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import javax.lang.model.element.TypeElement

class AnnotatedClassSource<ClassAnnotation>(

        typeElement: TypeElement,
        val annotation: ClassAnnotation,
        compilationUtilities: CompilationUtilities

) : ClassSource(typeElement, compilationUtilities) where ClassAnnotation : Annotation