package com.art.memorycardgame.presentatuin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Memory Card Game",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Moves: ${state.moves}",
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Pairs: ${state.matchedPairs}/6",
            modifier = Modifier.padding(top = 4.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 24.dp),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                items = state.cards,
                key = { it.id }
            ) { card ->

                MemoryCardItem(
                    card = card,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    onClick = {
                        viewModel.onIntent(
                            GameContract.Intent.OnCardClicked(
                                card.id
                            )
                        )
                    }
                )
            }
        }

        if (state.isGameCompleted) {

            Text(
                text = "🎉 You completed the game!",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
        }

        Button(
            onClick = {
                viewModel.onIntent(
                    GameContract.Intent.RestartGame
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Restart Game")
        }
    }
}