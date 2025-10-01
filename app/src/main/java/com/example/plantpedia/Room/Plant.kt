package com.example.plantpedia.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")

data class Plant(

    @PrimaryKey(autoGenerate = true)
    val id :Long,
    val scientificName: String,
    val imgUrl: String,



)
