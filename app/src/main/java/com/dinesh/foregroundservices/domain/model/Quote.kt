package com.dinesh.foregroundservices.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey
    val id: Int,
    val quote: String,
    val author: String,
    val workType : String = "",
    val time : Long = System.currentTimeMillis()
)