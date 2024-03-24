package com.example.composeandroidplayground.selectable.model

import java.util.UUID

data class Selectable(
    val text: String,
    val id: String = UUID.randomUUID().toString()
)