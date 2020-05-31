package com.goofy.goober.domain.di

import com.goofy.goober.domain.usecase.GetPosts
import org.koin.dsl.module

val domainModule = module {
    factory { GetPosts(get()) }
}
