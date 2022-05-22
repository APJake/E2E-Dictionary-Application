package com.apjake.etoedictionary.feature_dictionary.data.remote.dto


import com.apjake.etoedictionary.feature_dictionary.data.local.entity.WordInfoEntity
import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo
import com.google.gson.annotations.SerializedName

data class WordInfoDto(
    @SerializedName("license")
    val license: LicenseDto?,

    @SerializedName("meanings")
    val meanings: List<MeaningDto>?,

    @SerializedName("phonetic")
    val phonetic: String?,

    @SerializedName("phonetics")
    val phonetics: List<PhoneticDto>?,

    @SerializedName("sourceUrls")
    val sourceUrls: List<String>?,

    @SerializedName("word")
    val word: String?,

    @SerializedName("origin")
    val origin: String?,
){
    fun toWordInfoEntity() = WordInfoEntity(
        meanings = meanings.orEmpty().map { it.toMeaning() },
        phonetic = phonetic.orEmpty(),
        sourceUrls = sourceUrls.orEmpty(),
        word = word.orEmpty(),
        origin = origin.orEmpty(),
    )
}