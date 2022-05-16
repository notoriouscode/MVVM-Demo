package com.example.mvvm_architecture.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "quote")
data class Result(

    @PrimaryKey(autoGenerate = true)
    val quoteId:Int,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,

    // for migration
    //val isActive: Int,
)