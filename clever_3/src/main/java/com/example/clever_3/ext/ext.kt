package com.example.clever_3.ext

import com.example.clever_3.db.ContactsEntity


fun MutableList<ContactsEntity>.toArrayOfStrings(): Array<String> {

    var array: Array<String> = Array(size) { "" }

    for (i in 0 until this.size) {
        array[i] = this[i].name + " " + this[i].email + " " + this[i].number

    }

    return array
}

