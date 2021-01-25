package com.goofy.goober.vyasa.di

import com.goofy.goober.vyasa.controller.Interactor
import com.goofy.goober.vyasa.viewmodel.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory {
        Interactor(
            categories = get(),
            posts = get()
        )
    }
    factory { MainViewModel(interactor = get()) }
}
