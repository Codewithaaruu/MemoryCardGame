package com.art.memorycardgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.art.memorycardgame.presentatuin.GameScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity for the Memory Card Game.
 * This activity serves as the entry point for the application and sets up the Compose UI.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in [onSaveInstanceState]. **Note: Otherwise it is null.**
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameScreen()
        }
    }
}
