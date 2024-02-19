package com.github.edu.look.data.enums

enum class ClassType {
    HOMEWORK,
    COMMUNICATE,
    CLASS_MATERIAL;

    companion object {
        fun getClassType(type: String?): ClassType = ClassType.values()
            .firstOrNull { it.name == type } ?: CLASS_MATERIAL
    }
}