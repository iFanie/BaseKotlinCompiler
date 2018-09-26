package com.izikode.izilib.basekotlincompiler.component

abstract class AbstractKotlinClass {

    /**
     * Class package name.
     */
    abstract val packageName: String

    /**
     * Class folder based on package.
     */
    val relativePath: String
        get() = packageName.replace(".", "/")

    /**
     * Class name.
     */
    abstract val simpleName: String

    /**
     * Class canonical name.
     */
    val canonicalName: String
        get() = "$packageName.$simpleName"

    /**
     * Class source code.
     */
    abstract val sourceCode: String

}