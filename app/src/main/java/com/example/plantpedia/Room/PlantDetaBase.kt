package com.example.plantpedia.Room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Plant::class], version = 1)


abstract class PlantDetaBase: RoomDatabase(){

    abstract fun plantDao(): PlantDao
}
