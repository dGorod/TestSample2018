package ua.dgorod.sample.data.db.entity

import androidx.room.Embedded

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
data class RepoInfoEntity(

        @Embedded
        var repo: RepoEntity,

        @Embedded
        var user: UserEntity
)