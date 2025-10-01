package com.example.plantpedia.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao

interface PlantDao {

    @Insert
    suspend fun insertPlant(plant: Plant)


    @Update
    suspend fun updatePlant(plant: Plant)

    @Delete

    suspend fun deletePlant(plant: Plant)

    @Query("SELECT * FROM PLANT")
    fun getplant(): LiveData<List<Plant>>

    @Query("SELECT * FROM plant WHERE scientificName = :name LIMIT 1")
    suspend fun getPlantByName(name: String): Plant?



}