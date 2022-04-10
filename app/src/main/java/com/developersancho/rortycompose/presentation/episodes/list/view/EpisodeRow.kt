package com.developersancho.rortycompose.presentation.episodes.list.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.rortycompose.app.theme.*
import com.developersancho.rortycompose.data.model.dto.episode.EpisodeDto
import com.developersancho.rortycompose.presentation.episodes.list.EpisodesEvent
import com.developersancho.rortycompose.presentation.episodes.list.EpisodesViewModel

@Composable
fun EpisodeRow(
    viewModel: EpisodesViewModel = hiltViewModel(),
    dto: EpisodeDto,
    onDetailClick: () -> Unit = {}
) {
    Card(
        onClick = onDetailClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            ),
        elevation = 8.dp
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 8.dp, bottom = 4.dp)
        ) {
            Text(
                text = dto.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                style = JetRortyTypography.subtitle1
            )
            Text(
                text = dto.episode.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                style = JetRortyTypography.subtitle1
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = dto.airDate.toString(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 2.dp),
                    style = JetRortyTypography.subtitle1
                )

                FavoriteButton(viewModel, dto)
            }
        }
    }
}

@Composable
private fun FavoriteButton(
    viewModel: EpisodesViewModel = hiltViewModel(),
    dto: EpisodeDto
) {
    var isFavorite by rememberSaveable(dto) { mutableStateOf(dto.isFavorite) }

    IconButton(onClick = {
        isFavorite = !isFavorite
        dto.isFavorite = isFavorite
        viewModel.onTriggerEvent(EpisodesEvent.AddOrRemoveFavorite(dto))
    }) {
        val tintColor = if (isFavorite) Red else Gray500
        Icon(
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = null,
            tint = tintColor
        )
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
fun EpisodeRowPreview() {
    JetRortyTheme {
        Surface(color = JetRortyColors.background) {
            EpisodeRow(dto = EpisodeDto.init())
        }
    }
}