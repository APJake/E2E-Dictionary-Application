package com.apjake.etoedictionary.feature_dictionary.data.remote.dto


import com.apjake.etoedictionary.feature_dictionary.domain.model.Definition
import com.google.gson.annotations.SerializedName

data class DefinitionDto(
    @SerializedName("antonyms")
    val antonyms: List<String>?,

    @SerializedName("definition")
    val definition: String?,

    @SerializedName("synonyms")
    val synonyms: List<String>?,

    @SerializedName("example")
    val example: String?,
){
    fun toDefinition(): Definition{
        return Definition(
            antonyms = antonyms.orEmpty(),
            definition = definition.orEmpty(),
            synonyms = synonyms.orEmpty(),
            example= example.orEmpty()
        )
    }
}