package ua.dgorod.sample.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ua.dgorod.sample.data.db.Tables

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
@Entity(tableName = Tables.users)
data class UserEntity(
        @PrimaryKey
        @ColumnInfo(name = Field.id)
        var id: Long,

        @ColumnInfo(name = Field.name)
        var name: String,

        @ColumnInfo(name = Field.avatarUrl)
        var avatarUrl: String?,

        @ColumnInfo(name = Field.htmlUrl)
        var htmlUrl: String
) {
    object Field {
        private const val prefix = "user_"
        const val id = "id"
        const val name = "${prefix}name"
        const val avatarUrl = "${prefix}avatarUrl"
        const val htmlUrl = "${prefix}htmlUrl"
    }
}