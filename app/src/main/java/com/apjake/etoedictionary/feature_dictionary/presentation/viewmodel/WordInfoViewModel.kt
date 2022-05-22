package com.apjake.etoedictionary.feature_dictionary.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apjake.etoedictionary.core.util.AppConstants
import com.apjake.etoedictionary.core.util.AppConstants.DELAY_TIME_FOR_NEXT_SEARCH
import com.apjake.etoedictionary.core.util.Resource
import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo
import com.apjake.etoedictionary.feature_dictionary.domain.usecase.GetWordInfoUseCase
import com.apjake.etoedictionary.feature_dictionary.presentation.event.UiEvent
import com.apjake.etoedictionary.feature_dictionary.presentation.state.WordInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _isDarkMode = mutableStateOf<Boolean?>(null)
    val isDarkMode: State<Boolean?> = _isDarkMode

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun toggleDarkMode(isDark: Boolean){
        _isDarkMode.value = isDark
    }

    fun onSearch(query: String){
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DELAY_TIME_FOR_NEXT_SEARCH)
            getWordInfoUseCase(word = query)
                .onEach { result ->
                    when(result){
                        is Resource.Success ->{
                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error ->{
                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UiEvent.ShowSnackBar(
                                result.message?: "Unknown error"
                            ))
                        }
                        is Resource.Loading ->{
                            _state.value = state.value.copy(
                                wordInfoItems = result.data?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

}