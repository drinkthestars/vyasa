package com.goofy.goober.vyasa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Providers
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import com.goofy.goober.domain.flow.Action
import com.goofy.goober.vyasa.ambients.AmbientBackPressedDispatcher
import com.goofy.goober.vyasa.ambients.backButtonHandler
import com.goofy.goober.vyasa.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // TODO: Remove ViewModel
    private val viewModel: MainViewModel by viewModel()

    private val uiEventPerformer = object : UiEventPerformer {
        override fun performAction(action: Action.UiAction) = viewModel.consume(action)
        override fun exit() = finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Providers(AmbientBackPressedDispatcher provides this) {
                        backButtonHandler(enabled = true) {
                            viewModel.consume(Action.UiAction.GoBack)
                        }

                        App(
                            state = viewModel.state.collectAsState().value,
                            uiEventPerformer = uiEventPerformer
                        )
                    }
                }
            }
        }
    }
}
