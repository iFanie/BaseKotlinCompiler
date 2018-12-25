package com.izikode.izilib.basekotlincompiler.utility

import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

class ProcessingKit(private val elementUtils: Elements, private val typeUtils: Types) {

    /**
     * Returns the package of an element. The package of a package is itself.
     *
     * @param typeElement the element being examined
     * @return the package of an element
     */
    fun getPackageOf(typeElement: TypeElement) = elementUtils.getPackageOf(typeElement)

    /**
     * Returns the package of an element. The package of a package is itself.
     *
     * @param element the element being examined
     * @return the package of an element
     */
    fun getPackageOf(element: Element) = getPackageOf(element as TypeElement)

    /**
     * Tests whether two {@code TypeMirror} objects represent the same type.
     *
     * <p>Caveat: if either of the arguments to this method represents a
     * wildcard, this method will return false.  As a consequence, a wildcard
     * is not the same type as itself.  This might be surprising at first,
     * but makes sense once you consider that an example like this must be
     * rejected by the compiler:
     * <pre>
     *   {@code List<?> list = new ArrayList<Object>();}
     *   {@code list.add(list.get(0));}
     * </pre>
     *
     * <p>Since annotations are only meta-data associated with a type,
     * the set of annotations on either argument is <em>not</em> taken
     * into account when computing whether or not two {@code
     * TypeMirror} objects are the same type. In particular, two
     * {@code TypeMirror} objects can have different annotations and
     * still be considered the same.
     *
     * @param firstTypeMirror  the first type
     * @param secondTypeMirror  the second type
     * @return {@code true} if and only if the two types are the same
     */
    fun isSameType(firstTypeMirror: TypeMirror, secondTypeMirror: TypeMirror)
            = typeUtils.isSameType(firstTypeMirror, secondTypeMirror)

    /**
     * Returns all members of a type element, whether inherited or
     * declared directly.  For a class the result also includes its
     * constructors, but not local or anonymous classes.
     *
     * <p>Note that elements of certain kinds can be isolated using
     * methods in {@link ElementFilter}.
     *
     * @param type  the type being examined
     * @return all members of the type
     * @see Element#getEnclosedElements
     */
    fun getAllMembers(element: Element) = elementUtils.getAllMembers(element as TypeElement)

    /**
     * Returns the element's kotlin type name equivalent.
     *
     * @param element  the element being examined
     * @return the kotlin type equivalent's name
     */
    fun getKotlinType(element: Element): String = element.asType().toString().let { type ->
        TypeMatcher.toKotlinType(type) +
                if (element.getAnnotation(org.jetbrains.annotations.NotNull::class.java) == null
                        || type.contains("?")) "?" else ""
    }

}