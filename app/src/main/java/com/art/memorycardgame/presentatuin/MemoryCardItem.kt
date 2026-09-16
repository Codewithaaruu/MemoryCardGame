package com.art.memorycardgame.presentatuin

import com.art.memorycardgame.domain.MemoryCard

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MemoryCardItem(
    card: MemoryCard,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .background(
                color = if (card.isFlipped || card.isMatched) {
                    Color.White
                } else {
                    Color(0xFF6750A4)
                },
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(
                enabled = !card.isFlipped && !card.isMatched,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {

        AnimatedContent(
            targetState = card.isFlipped || card.isMatched,
            label = "card_content"
        ) { isVisible ->

            if (isVisible) {

                Text(
                    text = card.value,
                    fontSize = 36.sp
                )

            } else {

                Text(
                    text = "?",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}