package com.gifgroen.pokejournal.di.components

import com.gifgroen.pokejournal.di.modules.ViewModelModule
import com.gifgroen.pokejournal.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}