package com.goofy.goober.domain.di

import com.goofy.goober.domain.Categories
import com.goofy.goober.domain.Posts
import org.koin.dsl.module

val domainModule = module {
    factory { Posts(get()) }
    factory { Categories(get()) }
}
