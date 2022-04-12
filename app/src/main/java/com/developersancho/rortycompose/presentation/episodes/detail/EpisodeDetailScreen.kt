package com.developersancho.rortycompose.presentation.episodes.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extension.cast
import com.developersancho.rortycompose.R
import com.developersancho.rortycompose.app.theme.JetRortyColors
import com.developersancho.rortycompose.app.theme.JetRortyTheme
import com.developersancho.rortycompose.app.widget.EmptyView
import com.developersancho.rortycompose.app.widget.ErrorView
import com.developersancho.rortycompose.app.widget.JRToolbarWithNavIcon
import com.developersancho.rortycompose.app.widget.LoadingView
import com.developersancho.rortycompose.presentation.episodes.detail.view.EpisodeDetailContent
import com.developersancho.rortycompose.provider.NavigationProvider
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EpisodeDetailScreen(
    id: Int = 0,
    viewModel: EpisodeDetailViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val uiState by viewModel.uiState.collectAsState()

    EpisodeDetailBody(
        pressOnBack = { navigator.navigateUp() }
    ) {
        when (uiState) {
            is BaseViewState.Data -> EpisodeDetailContent(
                data = uiState.cast<BaseViewState.Data<EpisodeDetailState>>().value,
                navigator = navigator
            )
            is BaseViewState.Empty -> EmptyView()
            is BaseViewState.Error -> ErrorView(
                e = uiState.cast<BaseViewState.Error>().throwable,
                action = {
                    viewModel.onTriggerEvent(EpisodeDetailEvent.LoadDetail(id))
                }
            )
            is BaseViewState.Loading -> LoadingView()
        }
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(EpisodeDetailEvent.LoadDetail(id))
    })
}

@Composable
private fun EpisodeDetailBody(
    pressOnBack: () -> Unit = {},
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            JRToolbarWithNavIcon(
                R.string.toolbar_episode_detail_title,
                pressOnBack = pressOnBack
            )
        },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun EpisodeDetailScreenPreview() {
    JetRortyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = JetRortyColors.background
        ) {
            EpisodeDetailBody {
            }
        }
    }
}