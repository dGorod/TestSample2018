package ua.dgorod.sample.data.api.dto

import com.google.gson.annotations.SerializedName
import java.util.*

class RepoDto {

    @SerializedName("id")
    val id: Long = -1L

    @SerializedName("name")
    val name: String = "n/a"

    @SerializedName("full_name")
    val fullName: String = "n/a"

    @SerializedName("description")
    val desc: String = ""

    @SerializedName("owner")
    val owner: UserDto = UserDto()

    @SerializedName("html_url")
    val htmlUrl: String = "n/a"

    @SerializedName("fork")
    val isFork: Boolean = false

    @SerializedName("language")
    val language: String = ""

    @SerializedName("stargazers_count")
    val stars: Int = 0

    @SerializedName("created_at")
    val createdAt: Date = Date(0)
}