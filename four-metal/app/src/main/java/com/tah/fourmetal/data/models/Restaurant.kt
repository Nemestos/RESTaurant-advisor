package com.tah.fourmetal.data.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Restaurant(
    var id: Int,
    var name: String?,
    var description: String?,
    var grade: Float,
    var image_url:String?,
    var localization: String?,
    var phone_number: String?,
    var website: String?,
    var hours: String?
) : Parcelable