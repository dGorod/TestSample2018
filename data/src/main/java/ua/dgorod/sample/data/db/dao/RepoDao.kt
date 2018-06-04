package ua.dgorod.sample.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import io.reactivex.Maybe
import ua.dgorod.sample.data.db.Tables
import ua.dgorod.sample.data.db.entity.RepoEntity
import ua.dgorod.sample.data.db.entity.RepoInfoEntity
import ua.dgorod.sample.data.db.entity.UserEntity
import ua.dgorod.sample.domain.Const

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
@Dao
interface RepoDao {

    @Query("SELECT * FROM ${Tables.repositories} LIMIT")
    fun getAll(page: Int): DataSource.Factory<Int, RepoEntity>

    @Query("SELECT * FROM ${Tables.repositories} WHERE ${RepoEntity.Field.id} = :id")
    fun get(id: Long): Maybe<RepoEntity>

    @Query("""
        SELECT repo.*, user.*
        FROM ${Tables.repositories}
        INNER JOIN ${Tables.users} ON repo.${RepoEntity.Field.userId} = user.${UserEntity.Field.id}
        ORDER BY ${RepoEntity.Field.stars}
        LIMIT ${Const.DEFAULT_PAGE_SIZE} OFFSET :page""")
    fun getAllWithUsers(page: Int): DataSource.Factory<Int, RepoInfoEntity>

    @Query("""
        SELECT repo.*, user.*
        FROM ${Tables.repositories}
        INNER JOIN ${Tables.users} ON repo.${RepoEntity.Field.userId} = user.${UserEntity.Field.id}
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