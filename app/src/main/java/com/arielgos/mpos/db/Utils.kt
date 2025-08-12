package com.arielgos.mpos.db

import com.arielgos.mpos.db.annotations.DoNotPersist
import com.arielgos.mpos.db.annotations.Autoincrement
import com.arielgos.mpos.db.annotations.Key
import java.util.Date

fun <T> createTableQuery(model: Class<T>): String {
    val declaredFields = model.declaredFields
    val fields = mutableListOf<String>()
    declaredFields.forEach { declaredField ->
        if (declaredField.isAnnotationPresent(DoNotPersist::class.java)) {
            return@forEach
        }
        var field = "`${declaredField.name}`"
        if (declaredField.type === String::class.java) {
            field = "$field TEXT"
        } else if (declaredField.type === Int::class.java || declaredField.type === Long::class.java) {
            field = "$field INTEGER"
        } else if (declaredField.type === Boolean::class.java) {
            field = "$field INTEGER"
        } else if (declaredField.type === Float::class.java || declaredField.type === Double::class.java) {
            field = "$field NUMERIC"
        } else if (declaredField.type === Date::class.java) {
            field = "$field TEXT"
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