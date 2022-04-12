package com.developersancho.rortycompose.presentation.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.developersancho.rortycompose.R
import com.developersancho.rortycompose.app.theme.JetRortyTheme
import com.developersancho.rortycompose.app.widget.JRToolbar
import com.developersancho.rortycompose.presentation.settings.view.SettingsContent
import com.developersancho.rortycompose.provider.NavigationProvider

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
    navigator: NavigationProvider
) {
    val checkedState = remember { mutableStateOf(viewModel.isNightMode()) }

    SettingsBody(modifier) {
        SettingsContent(viewModel, checkedState, navigator)
    }
}

@Composable
private fun SettingsBody(
    modifier: Modifier = Modifier,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { JRToolbar(R.string.toolbar_settings_title) },
        content = { pageContent.invoke(it) }
    )
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
fun SettingsScreenPreview() {
    JetRortyTheme {
        Surface {
            SettingsBody {
            }
        }
    }
}