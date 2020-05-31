package com.goofy.goober.vyasa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.collectAsState
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.layout.fillMaxSize
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.goofy.goober.ui.PostRow
import com.goofy.goober.vyasa.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val postList = mainViewModel.posts.collectAsState().value
                    AdapterList(data = postList?.posts ?: emptyList()) {
                        PostRow(post = it)
                    }
                }
            }
        }
    }
}
