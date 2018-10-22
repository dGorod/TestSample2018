package ua.dgorod.sample.data.db.entity

import androidx.room.Embedded

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
data class RepoInfoEntity(

        @Embedded
        val repo: RepoEntity,

        @Embedded
        val user: UserEntity
)