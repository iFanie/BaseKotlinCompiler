package com.izikode.izilib.basekotlincompiler.utility

object TypeMatcher {

    fun toKotlinType(javaType: String) = when(javaType) {

        "java.lang.String" -> "kotlin.String"
        "java.lang.Integer" -> "kotlin.Int"
        "int" -> "kotlin.Int"
        "java.lang.CharSequence" -> "kotlin.CharSequence"
        else -> javaType

    }

}