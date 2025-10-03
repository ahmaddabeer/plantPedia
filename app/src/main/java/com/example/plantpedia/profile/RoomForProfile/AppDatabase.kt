import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantpedia.Room.PlantDao

@Database(entities = [ ProfileEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}
