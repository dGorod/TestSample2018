package ua.dgorod.sample.data.api.dto

import com.google.gson.annotations.SerializedName

class ApiDto<T> {

    @SerializedName("total_count")
    val total: Int = 0

    @SerializedName("incomplete_results")
    val incomplete: Boolean = true

    @SerializedName("items")
    val items: List<T> = emptyList()
}