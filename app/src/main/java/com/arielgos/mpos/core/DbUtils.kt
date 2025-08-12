package com.arielgos.mpos.core

import com.arielgos.mpos.core.annotations.Ignore
import com.arielgos.mpos.core.annotations.Autoincrement
import com.arielgos.mpos.core.annotations.Key
import java.util.Date
import kotlin.reflect.jvm.kotlinProperty

fun <T> createTableQuery(model: Class<T>): String {
    val declaredFields = model.declaredFields
    val fields = mutableListOf<String>()
    declaredFields.forEach { declaredField ->
        if (declaredField.isAnnotationPresent(Ignore::class.java)) {
            return@forEach
        }
        var field = "${declaredField.name}"
        if (declaredField.type === String::class.java) {
            field = "$field TEXT"
        } else if (declaredField.type === Int::class.java || declaredField.type === Long::class.java || declaredField.type === java.lang.Long::class.java) {
            field = "$field INTEGER"
        } else if (declaredField.type === Boolean::class.java) {
            field = "$field INTEGER"
        } else if (declaredField.type === Float::class.java || declaredField.type === Double::class.java) {
            field = "$field NUMERIC"
        } else if (declaredField.type === Date::class.java) {
            field = "$field TEXT"
        }
        if (declaredField.kotlinProperty?.returnType?.isMarkedNullable == false) {
            field = "$field NOT NULL"
        }
        if (declaredField.isAnnotationPresent(Key::class.java)) {
            field = "$field PRIMARY KEY"
        }
        if (declaredField.isAnnotationPresent(Autoincrement::class.java)) {
            field = "$field AUTOINCREMENT"
        }
        fields.add(field)
    }
    return "CREATE TABLE `${model.simpleName}` (${fields.joinToString(", ")});"
}

fun <T> dropTableQuery(model: Class<T>): String {
    return "DROP TABLE IF EXISTS `${model.simpleName}`;"
}