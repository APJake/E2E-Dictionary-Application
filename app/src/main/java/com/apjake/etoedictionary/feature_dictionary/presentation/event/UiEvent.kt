package com.apjake.etoedictionary.feature_dictionary.presentation.event

sealed class UiEvent{
    data class ShowSnackBar(val message: String): UiEvent()
}
