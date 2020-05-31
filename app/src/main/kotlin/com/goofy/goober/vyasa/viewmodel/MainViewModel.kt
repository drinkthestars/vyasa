package com.goofy.goober.vyasa.viewmodel

import androidx.lifecycle.ViewModel
import com.goofy.goober.domain.model.Posts
import com.goofy.goober.domain.usecase.GetPosts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}

class MainViewModel(
    private val getPosts: GetPosts
) : ViewModel() {

    val posts: Flow<Posts> = flow {
        emit(getPosts())
    }
}
