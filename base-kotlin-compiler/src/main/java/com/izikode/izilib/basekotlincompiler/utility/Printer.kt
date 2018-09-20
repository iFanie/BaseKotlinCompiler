package com.izikode.izilib.basekotlincompiler.utility

import javax.annotation.processing.Messager
import javax.tools.Diagnostic

class Printer(private val messager: Messager) {

    fun note(note: String) {
        messager.printMessage(Diagnostic.Kind.NOTE, note)
    }

    fun warn(warning: String) {
        messager.printMessage(Diagnostic.Kind.WARNING, warning)
    }

    fun error(error: Exception) {
        messager.printMessage(Diagnostic.Kind.ERROR, error.toString())
    }

}