package com.apjake.etoedictionary.feature_dictionary.domain.usecase

import com.apjake.etoedictionary.core.util.Resource
import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo
import com.apjake.etoedictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>>{
        if(word.isBlank()) return flow {  }
        return repository.getWordInfo(word)
    }
}