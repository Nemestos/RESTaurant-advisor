package com.tah.fourmetal.ui.form

import android.util.Log

class FormState {
    var fields: List<Field> = listOf()
    fun validate(): Boolean {
        var valids = ArrayList<Boolean>()
        for (field in fields) {
            valids.add(field.validate())
        }
        return valids.all { it }
    }

    fun clear() {
        fields.forEach {
            it.clear()

        }
    }

    fun getData(): Map<String, String> = fields.map { it.name to it.text }.toMap()
}