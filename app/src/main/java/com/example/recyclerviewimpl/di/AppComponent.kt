package com.example.recyclerviewimpl.di

import android.content.Context
import com.example.recyclerviewimpl.presentation.RecyclerActivity
import com.example.recyclerviewimpl.presentation.RecyclerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        RepositoriesModule::class,
        InteractorsModule::class
    ]
)
interface AppComponent {

    fun inject(activity: RecyclerActivity)
    fun inject(fragment: RecyclerFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}