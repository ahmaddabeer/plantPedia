import androidx.room.*

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(profile: ProfileEntity)

    @Query("SELECT * FROM user_profile LIMIT 1")
    suspend fun getProfile(): ProfileEntity?

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)
}
