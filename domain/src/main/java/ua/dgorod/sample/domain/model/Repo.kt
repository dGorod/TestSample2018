package ua.dgorod.sample.domain.model

import java.util.*

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
data class Repo(
        val id: Long,
        val name: String,
        val fullName: String,
        val desc: String,
        val owner: User,
        val htmlUrl: String,
        val isFork: Boolean,
        val language: String,
        val createdAt: Date = Date(0)
)