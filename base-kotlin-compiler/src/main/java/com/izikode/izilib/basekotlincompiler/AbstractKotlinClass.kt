package com.izikode.izilib.basekotlincompiler

abstract class AbstractKotlinClass {

    /**
     * Class package name.
     */
    abstract val packageName: String

    /**
     * Class name.
     */
    abstract val simpleName: String

    /**
     * Class source code.
     */
    abstract val sourceCode: String

    /**
     * Class canonical name.
     */
    val getCanonicalName: String
        get() = "$packageName.$simpleName"

    /**
     * Class folder based on package.
     */
    val relativePath: String
        get() = packageName.replace(".", "/")

}