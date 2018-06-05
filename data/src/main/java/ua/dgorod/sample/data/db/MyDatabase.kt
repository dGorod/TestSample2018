package ua.dgorod.sample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.dgorod.sample.data.db.converter.DateConverter
import ua.dgorod.sample.data.db.dao.RepoDao
import ua.dgorod.sample.data.db.dao.UserDao
import ua.dgorod.sample.data.db.entity.RepoEntity
import ua.dgorod.sample.data.db.entity.UserEntity

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
@Database(entities = [RepoEntity::class, UserEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MyDatabase: RoomDatabase() {

    companion object {

        private const val dbName = "Default.db"

        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, dbName)
                        .build()
                        .also { instance = it }
            }
        }
    }

    abstract fun repositories(): RepoDao
    abstract fun users(): UserDao
}

object Tables {
    const val repositories = "repositories"
    const val users = "users"
}