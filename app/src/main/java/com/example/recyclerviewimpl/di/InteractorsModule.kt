package com.example.recyclerviewimpl.di

import com.example.recyclerviewimpl.domain.Interactor
import com.example.recyclerviewimpl.domain.InteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorsModule {

    @Binds
    fun provideIntercator(interactorImpl: InteractorImpl): Interactor
}
