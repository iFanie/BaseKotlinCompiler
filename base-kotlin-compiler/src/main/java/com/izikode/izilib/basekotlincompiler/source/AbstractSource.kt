package com.izikode.izilib.basekotlincompiler.source

import com.izikode.izilib.basekotlincompiler.CompilationUtilities
import javax.lang.model.element.Element

abstract class AbstractSource(

        protected val element: Element,
        protected val compilationUtilities: CompilationUtilities

)