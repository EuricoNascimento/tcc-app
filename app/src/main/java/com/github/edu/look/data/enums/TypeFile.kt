package com.github.edu.look.data.enums

enum class TypeFile(val type: String) {
    VIDEO("Video"),
    IMAGE("Image"),
    FILE("File"),
    FORM("Form"),
    NONE("None"),
    WEB("Web"),
    PDF("PDF");

    companion object {
        fun getTypeFile(type: String): TypeFile = TypeFile.values()
            .firstOrNull { type == it.type } ?: NONE
    }
}