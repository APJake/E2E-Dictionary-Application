package com.apjake.etoedictionary.feature_dictionary.domain.repository

import com.apjake.etoedictionary.core.util.Resource
import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}