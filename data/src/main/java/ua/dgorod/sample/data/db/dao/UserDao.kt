package ua.dgorod.sample.data.db.dao

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import ua.dgorod.sample.data.db.Tables
import ua.dgorod.sample.data.db.entity.UserEntity

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM ${Tables.users}")
    fun getAll(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM ${Tables.users} WHERE ${UserEntity.Field.id} = :id")
    fun get(id: Long): Maybe<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: UserEntity)

    @Query("DELETE FROM ${Tables.users}")
    fun deleteAll()

    @Query("DELETE FROM ${Tables.users} WHERE ${UserEntity.Field.id} = :id")
    fun delete(id: Long)
}