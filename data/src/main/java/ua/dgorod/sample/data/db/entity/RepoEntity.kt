package ua.dgorod.sample.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ua.dgorod.sample.data.db.Tables
import java.util.*

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
@Entity(tableName = Tables.repositories,
        foreignKeys = [
            ForeignKey(
                    entity = UserEntity::class,
                    parentColumns = [UserEntity.Field.id],
                    childColumns = [RepoEntity.Field.userId],
                    onDelete = ForeignKey.CASCADE)
        ])
data class RepoEntity(
        @PrimaryKey
        @ColumnInfo(name = Field.id)
        var id: Long,

        @ColumnInfo(name = Field.userId, index = true)
        var userId: Long,

        @ColumnInfo(name = Field.name)
        var name: String,

        @ColumnInfo(name = Field.fullName)
        var fullName: String,

        @ColumnInfo(name = Field.desc)
        var desc: String,

        @ColumnInfo(name = Field.htmlUrl)
        var htmlUrl: String,

        @ColumnInfo(name = Field.fork)
        var fork: Boolean,

        @ColumnInfo(name = Field.language)
        var language: String,

        @ColumnInfo(name = Field.stars, index = true)
        var stars: Int,

        @ColumnInfo(name = Field.createdAt)
        var createdAt: Date

) {
    object Field {
        private const val prefix = "repository_"
        const val id = "${prefix}id"
        const val userId = "${prefix}userId"
        const val name = "${prefix}name"
        const val fullName = "${prefix}fullName"
        const val desc = "${prefix}desc"
        const val htmlUrl = "${prefix}htmlUrl"
        const val fork = "${prefix}fork"
        const val language = "${prefix}language"
        const val stars = "${prefix}stars"
        const val createdAt = "${prefix}createdAt"
    }
}