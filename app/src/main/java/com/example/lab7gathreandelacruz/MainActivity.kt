package com.example.lab7gathreandelacruz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
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
                // A surface container using the desired background color
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF3949AB)
                ) {
                    // String array of character names
                    val characterNames = stringArrayResource(id = R.array.characters)

                    // List of characters
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

                    val shuffledCharacterList = remember { mutableStateOf(characterList) }

                    MainContent(shuffledCharacterList)
                }
            }
        }
    }

    @Composable
    fun MainContent(characterListState: MutableState<List<Character>>) {
        // MutableState for characterList
        // val characterListState = remember { mutableStateOf(characterList) }

        // Main content wrapped in a column so that the shuffle button is always at the top
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Shuffle button
            Button(
                onClick = {
                    // Shuffle the characterList and update characterListState
                    val shuffledList = characterListState.value.shuffled()
                    characterListState.value = shuffledList
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Shuffle", fontSize = 24.sp)
            }

            // Calculate the number of rows needed based on the number of characters
            val numRows = (characterListState.value.size + 2) / 3

            // LazyColumn to display the list of characters
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                items(numRows) { rowIndex ->
                    // LazyRow to display the row of characters
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        items(3) { columnIndex ->
                            val characterIndex = rowIndex * 3 + columnIndex
                            if (characterIndex < characterListState.value.size) {
                                CharacterCard(characterListState.value[characterIndex])
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CharacterCard(character: Character) {
        // Create a MutableState variable for isExpanded
        val isExpanded = remember { mutableStateOf(false) }

        // Displays the character card
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    // Toggle the isExpanded value
                    isExpanded.value = !isExpanded.value
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            // Column to display the character card
            Column(
                modifier = Modifier
                    .size(if (isExpanded.value) 300.dp else 200.dp)
                    .clip(shape = RectangleShape)
            ) {
                // Displays the name of the character if not expanded
                if (!isExpanded.value) {
                    Text(
                        character.name,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }

                // Displays the image of the character
                Image(
                    painter = painterResource(id = character.imageId),
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth() // Fill the available width
                        .fillMaxHeight() // Fill the available height
                )
            }
        }
    }

}