package com.apjake.etoedictionary.feature_dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LicenseDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("url")
    val url: String?
)