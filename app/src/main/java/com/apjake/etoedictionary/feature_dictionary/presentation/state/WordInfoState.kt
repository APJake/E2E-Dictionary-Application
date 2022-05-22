package com.apjake.etoedictionary.feature_dictionary.presentation.state

import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading:  Boolean = false
)
