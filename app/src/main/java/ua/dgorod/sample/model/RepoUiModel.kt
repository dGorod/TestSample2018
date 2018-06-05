package ua.dgorod.sample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
@Parcelize
data class RepoUiModel(
        val id: Long,
        val name: String,
        val fullName: String,
        val desc: String,
        val owner: UserUiModel,
        val htmlUrl: String,
        val isFork: Boolean,
        val language: String
) : Parcelable