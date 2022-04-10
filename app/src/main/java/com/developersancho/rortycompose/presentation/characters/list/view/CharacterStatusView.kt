package com.developersancho.rortycompose.presentation.characters.list.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developersancho.rortycompose.app.theme.JetRortyTheme
import com.developersancho.rortycompose.app.theme.JetRortyTypography
import com.developersancho.rortycompose.data.model.dto.character.CharacterDto

@Composable
fun CharacterStatusView(character: CharacterDto) {
    Box {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CharacterStatusDotView(character = character)
            Text(
                text = character.status.value,
                maxLines = 1,
                overflow = TextOverflow.Visible,
                style = JetRortyTypography.body2
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun CharacterStatusViewPreview() {
    JetRortyTheme {
        CharacterStatusView(CharacterDto.init())
    }
}