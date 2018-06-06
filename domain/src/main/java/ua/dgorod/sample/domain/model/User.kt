package ua.dgorod.sample.domain.model

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
data class User(
        val id: Long,
        val name: String,
        val avatarUrl: String?,
        val htmlUrl: String
)