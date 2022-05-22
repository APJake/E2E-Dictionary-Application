package com.apjake.etoedictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.apjake.etoedictionary.feature_dictionary.presentation.WordInfoItem
import com.apjake.etoedictionary.feature_dictionary.presentation.event.UiEvent
import com.apjake.etoedictionary.feature_dictionary.presentation.viewmodel.WordInfoViewModel
import com.apjake.etoedictionary.ui.theme.EtoEDictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: WordInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel = hiltViewModel()
            val state = viewModel.state.value
            val darkModeState = viewModel.isDarkMode.value
            val scaffoldState = rememberScaffoldState()

            val isDarkMode = darkModeState?: isSystemInDarkTheme()

            EtoEDictionaryTheme(
                darkTheme = isDarkMode
            ) {
                LaunchedEffect(key1 = true){
                    viewModel.eventFlow.collectLatest { event ->
                        when(event){
                            is UiEvent.ShowSnackBar ->{
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            item {

                                AppHeader(isDarkMode)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp)
                                ) {
                                    OutlinedTextField(
                                        value = viewModel.searchQuery.value,
                                        onValueChange = viewModel::onSearch,
                                        modifier = Modifier
                                            .weight(1f)
                                            .background(MaterialTheme.colors.background),
                                        shape = RoundedCornerShape(30.dp),
                                        placeholder = {
                                            Text(text = "Search...")
                                        },
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            items(state.wordInfoItems.size) { i ->
                                val wordInfo = state.wordInfoItems[i]
                                Spacer(modifier = Modifier.height(8.dp))
                                WordInfoItem(
                                    wordInfo = wordInfo,
                                    modifier = Modifier
                                        .padding(16.dp),
                                )
                            }

                        }
                        if(state.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

            }
        }
    }

    @Composable
    fun AppHeader(isDarkMode: Boolean){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                viewModel.toggleDarkMode(!isDarkMode)
            }) {
                Icon(imageVector = if(isDarkMode)Icons.Filled.LightMode else Icons.Filled.DarkMode, contentDescription = "Change Mode")
            }
            Text(text = "Jakeinary", fontWeight = FontWeight.Bold,  )
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.List, contentDescription = "List")
            }
        }
    }

}
