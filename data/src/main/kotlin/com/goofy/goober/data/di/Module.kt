package com.goofy.goober.data.di

import com.goofy.goober.data.DataRepository
import com.goofy.goober.domain.Repository
import org.koin.dsl.module

val dataModule = module {
    factory<Repository> { DataRepository(get()) }
}
