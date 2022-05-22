package com.apjake.etoedictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apjake.etoedictionary.feature_dictionary.domain.model.Meaning
import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val origin: String,
    val meanings: List<Meaning>,
    val sourceUrls: List<String>,
    @PrimaryKey val id: Int? = null
)
{
    fun toWordInfo()=WordInfo(
        word = word,
        phonetic = phonetic,
        origin = origin,
        meanings = meanings,
        sourceUrls = sourceUrls
    )
}