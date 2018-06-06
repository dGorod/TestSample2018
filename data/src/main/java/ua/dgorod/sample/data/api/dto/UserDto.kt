package ua.dgorod.sample.data.api.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by dgorodnytskyi on 6/4/18.
 */
class UserDto {

    @SerializedName("id")
    val id: Long = -1L

    @SerializedName("login")
    val name: String = "n/a"

    @SerializedName("avatar_url")
    val avatarUrl: String? = null

    @SerializedName("html_url")
    val htmlUrl: String = "n/a"
}