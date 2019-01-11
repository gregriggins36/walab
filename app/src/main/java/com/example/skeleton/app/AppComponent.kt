package com.example.skeleton.app

import com.example.skeleton.repository.RepositoryModule
import com.example.skeleton.repository.SharedPreferencesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AppModule::class,
            RepositoryModule::class
        ]
)
interface AppComponent {
    fun inject(app: App)

    val app: App

    val sharedPreferencesRepository: SharedPreferencesRepository
}
