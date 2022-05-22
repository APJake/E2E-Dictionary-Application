package com.apjake.etoedictionary.feature_dictionary.data.remote.dto


import com.apjake.etoedictionary.feature_dictionary.domain.model.Meaning
import com.google.gson.annotations.SerializedName

data class MeaningDto(
    @SerializedName("antonyms")
    val antonyms: List<String>?,

    @SerializedName("definitions")
    val definitions: List<DefinitionDto>?,

    @SerializedName("partOfSpeech")
    val partOfSpeech: String?,

    @SerializedName("synonyms")
    val synonyms: List<String>?
){
    fun toMeaning() = Meaning(
        definitions = definitions.orEmpty().map { it.toDefinition() },
        partOfSpeech = partOfSpeech.orEmpty()
    )
}