package com.izikode.izilib.basekotlincompiler

import com.izikode.izilib.basekotlincompiler.utility.ClassGenerator
import com.izikode.izilib.basekotlincompiler.utility.Printer
import com.izikode.izilib.basekotlincompiler.utility.ProcessingKit

data class CompilationUtilities(

        val options: Map<String, String>,
        val classGenerator: ClassGenerator,
        val printer: Printer,
        val processingKit: ProcessingKit

)