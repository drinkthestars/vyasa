package com.goofy.goober.data.di

import com.goofy.goober.data.repository.RemoteRepository
import com.goofy.goober.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {
    factory<Repository> { RemoteRepository(get()) }
}
