package com.apjake.etoedictionary.feature_dictionary.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apjake.etoedictionary.feature_dictionary.domain.model.WordInfo
import java.util.*

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier, elevation = 5.dp, shape = RoundedCornerShape(10.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = wordInfo.word,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Outlined.Favorite , contentDescription = "Favourite")
                }
            }
            Text(text = wordInfo.phonetic, fontWeight = FontWeight.Light)
            Spacer(modifier = Modifier.height(16.dp))
            if(wordInfo.origin.isNotBlank())  Text(text = wordInfo.origin)

            wordInfo.meanings.forEach { meaning ->
                Text(text = meaning.partOfSpeech.replaceFirstChar { it.uppercase() }, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))
                meaning.definitions.forEachIndexed { i, definition ->
                    Text(text = "${i + 1}. ${definition.definition}")
                    Spacer(modifier = Modifier.height(8.dp))
                    if(definition.example.isNotBlank()) Text(text = "Example: ${definition.example}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}