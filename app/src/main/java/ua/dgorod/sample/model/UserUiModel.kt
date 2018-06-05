package ua.dgorod.sample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by dgorodnytskyi on 6/5/18.
 */
@Parcelize
data class UserUiModel(
        val id: Long,
        val name: String,
        val avatarUrl: String,
        val htmlUrl: String
) : Parcelable