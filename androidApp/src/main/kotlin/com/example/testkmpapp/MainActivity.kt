package com.example.testkmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.testkmpapp.presentation.news.NewsPresenter
import com.example.testkmpapp.presentation.news.Presenter
import com.example.testkmpapp.presentation.news.View
import com.example.testkmpapp.service.SameService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), View {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Temporary learning call: prints coroutine context/thread info to Logcat/console.
        SameService().printCoroutineInfo()

        setContent {
            App()
        }
    }

    override val presenter: Presenter? by lazy { NewsPresenter() }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
