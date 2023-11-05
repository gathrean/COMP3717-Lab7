package com.example.lab7gathreandelacruz

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab7gathreandelacruz.ui.theme.Lab7GathreanDelaCruzTheme

// Character data class for the list of characters
data class Character(val name: String, val imageId: Int)

/**
 * Gathrean Dela Cruz, A01167248
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab7GathreanDelaCruzTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }

            val characterNames = stringArrayResource(id = R.array.characters)

            val characterList = listOf(
                Character(characterNames[0], R.drawable.batbron),
                Character(characterNames[1], R.drawable.beebron),
                Character(characterNames[2], R.drawable.cheesedrake),
                Character(characterNames[3], R.drawable.fortnitebron),
                Character(characterNames[4], R.drawable.frogbron),
                Character(characterNames[5], R.drawable.piedrake),
                Character(characterNames[6], R.drawable.sillykittybron),
                Character(characterNames[7], R.drawable.spongebron),
                Character(characterNames[8], R.drawable.unicornbron),
            )
            MainContent(characterList)
        }
    }

    @Composable
    fun MainContent(characterList: List<Character>) {
        val chunkedList = characterList.chunked(3) // Split the character list into chunks of 3

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(chunkedList.size) { rowIndex ->
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    items(chunkedList[rowIndex]) { character ->
                        CharacterCard(character)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }


    @Composable
    fun CharacterCard(character: Character) {
        var isExpanded = remember { mutableStateOf(false) }

        Card(
            modifier = Modifier
                .padding(8.dp)
//                .width(200.dp)
                .clickable {
                    isExpanded = isExpanded
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Image(
                painter = painterResource(id = character.imageId),
                contentDescription = character.name,
                modifier = Modifier
//                    .height(200.dp)
//                    .width(200.dp)
                    .clip(shape = RectangleShape)
            )
        }
    }

}