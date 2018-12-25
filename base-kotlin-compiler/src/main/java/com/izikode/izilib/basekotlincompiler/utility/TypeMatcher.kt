package com.izikode.izilib.basekotlincompiler.utility

object TypeMatcher {

    fun toKotlinType(javaType: String) = cleanup(javaType).let { type ->
        when (type) {

            "int" -> "kotlin.Int"
            "float" -> "kotlin.Float"
            "long" -> "kotlin.Long"
            "boolean" -> "kotlin.Boolean"

            "java.lang.String" -> "kotlin.String"
            "java.lang.Integer" -> "kotlin.Int"
            "java.lang.CharSequence" -> "kotlin.CharSequence"

            else -> type

        }
    }

    private fun cleanup(javaType: String)
            = javaType.replace("\\s*\\([^)]*\\)\\s*".toRegex(), "")
                      .replace("?", "")

}