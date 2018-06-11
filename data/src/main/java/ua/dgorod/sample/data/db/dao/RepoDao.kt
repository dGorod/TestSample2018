package ua.dgorod.sample.data.db.dao

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import ua.dgorod.sample.data.db.Tables
import ua.dgorod.sample.data.db.entity.RepoEntity
import ua.dgorod.sample.data.db.entity.RepoInfoEntity
import ua.dgorod.sample.data.db.entity.UserEntity

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
@Dao
interface RepoDao {

    @Query("""
        SELECT *
        FROM ${Tables.repositories}
        ORDER BY ${RepoEntity.Field.stars} DESC""")
    fun getAll(): Flowable<List<RepoEntity>>

    @Query("SELECT * FROM ${Tables.repositories} WHERE ${RepoEntity.Field.id} = :id")
    fun get(id: Long): Maybe<RepoEntity>

    @Query("""
        SELECT ${Tables.repositories}.*, ${Tables.users}.*
        FROM ${Tables.repositories}
        INNER JOIN ${Tables.users} ON ${RepoEntity.Field.userId} = ${UserEntity.Field.id}
        ORDER BY ${RepoEntity.Field.stars} DESC""")
    fun getAllWithUsers(): Flowable<List<RepoInfoEntity>>

    @Query("""
        SELECT ${Tables.repositories}.*, ${Tables.users}.*
        FROM ${Tables.repositories}
        INNER JOIN ${Tables.users} ON ${RepoEntity.Field.userId} = ${UserEntity.Field.id}
        WHERE ${RepoEntity.Field.id} = :id""")
    fun getWithUser(id: Long): Maybe<RepoInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<RepoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RepoEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(repo: RepoEntity)

    @Query("DELETE FROM ${Tables.repositories}")
    fun deleteAll()

    @Query("DELETE FROM ${Tables.repositories} WHERE ${RepoEntity.Field.id} = :id")
    fun delete(id: Long)
}