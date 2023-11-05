package com.example.lab7gathreandelacruz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
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
}

@Composable
fun MainContent(characterList: List<Character>) {

    val characterStateList = remember {
        mutableStateListOf<Character>()
    }

    Text(text = "Hello World!")
}