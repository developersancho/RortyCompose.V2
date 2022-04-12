package com.developersancho.rortycompose.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.developersancho.framework.extension.toast
import com.developersancho.rortycompose.R
import com.developersancho.rortycompose.app.component.SegmentText
import com.developersancho.rortycompose.app.component.SegmentedControl
import com.developersancho.rortycompose.app.theme.JetRortyColors
import com.developersancho.rortycompose.app.theme.JetRortyTheme
import com.developersancho.rortycompose.provider.LanguageProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @Inject
    lateinit var languageProvider: LanguageProvider

    private var backPressed = 0L

    private val finish: () -> Unit = {
        if (backPressed + 3000 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            toast(getString(R.string.app_exit_label))
        }
        backPressed = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            languageProvider.setLocale(languageProvider.getLanguageCode(), LocalContext.current)
            MainRoot(finish = finish)
        }
    }
}

@Composable
fun SegmentedDemo() {
    Column(Modifier.padding(16.dp), verticalArrangement = spacedBy(16.dp)) {
        Text("SEGMENTS", style = MaterialTheme.typography.caption)

        val twoSegments = remember { listOf("Characters", "Favorites") }
        var selectedTwoSegment by remember { mutableStateOf(twoSegments.first()) }
        SegmentedControl(
            twoSegments,
            selectedTwoSegment,
            onSegmentSelected = { selectedTwoSegment = it },
            // modifier = Modifier.background(JetRortyColors, JetRortyShapes.medium)
        ) {
            SegmentText(it)
        }

        val threeSegments = remember { listOf("Foo", "Bar", "Some very long string") }
        var selectedThreeSegment by remember { mutableStateOf(threeSegments.first()) }
        SegmentedControl(
            threeSegments,
            selectedThreeSegment,
            onSegmentSelected = { selectedThreeSegment = it }
        ) {
            SegmentText(it)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetRortyTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = JetRortyColors.background
        ) {
            SegmentedDemo()
        }
    }
}