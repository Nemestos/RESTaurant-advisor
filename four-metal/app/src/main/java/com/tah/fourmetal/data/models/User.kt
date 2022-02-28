package com.tah.fourmetal.data.models

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


data class User(
    var id: Int,
    var username: String = "",
    var firstname: String = "",
    var name: String = "",
    var age: Int = 0

)


