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

        @ColumnInfo(name = Field.userId)
        var userId: Long,

        @ColumnInfo
        var name: String,

        @ColumnInfo
        var fullName: String,

        @ColumnInfo
        var desc: String,

        @ColumnInfo
        var htmlUrl: String,

        @ColumnInfo
        var fork: Boolean,

        @ColumnInfo
        var language: String,

        @ColumnInfo(name = Field.stars, index = true)
        var stars: Int,

        @ColumnInfo
        var createdAt: Date

) {
    object Field {
        const val id = "id"
        const val userId = "userId"
        const val stars = "stars"
    }
}