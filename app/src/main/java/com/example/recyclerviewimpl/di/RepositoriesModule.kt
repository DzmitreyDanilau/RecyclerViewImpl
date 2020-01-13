package com.example.recyclerviewimpl.di

import com.example.recyclerviewimpl.data.repository.Repository
import com.example.recyclerviewimpl.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {
    @Binds
    fun provideAccountRepository(repositoryImpl: RepositoryImpl): Repository
}
