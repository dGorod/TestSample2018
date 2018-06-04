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

        @ColumnInfo
        var name: String,

        @ColumnInfo
        var avatarUrl: String,

        @ColumnInfo
        var htmlUrl: String
) {
    object Field {
        const val id = "id"
    }
}