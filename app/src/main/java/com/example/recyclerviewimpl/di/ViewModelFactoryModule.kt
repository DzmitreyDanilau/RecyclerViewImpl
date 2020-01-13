package com.example.recyclerviewimpl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerviewimpl.presentation.RecyclerViewModel
import com.example.recyclerviewimpl.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RecyclerViewModel::class)
    fun provideAccountViewModel(recyclerViewModel: RecyclerViewModel): ViewModel
}