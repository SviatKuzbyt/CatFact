package ua.sviatkuzbyt.catfact.core

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.sviatkuzbyt.catfact.data.managers.FactManager
import ua.sviatkuzbyt.catfact.data.managers.ImageManager
import ua.sviatkuzbyt.catfact.data.managers.LanguageManager
import ua.sviatkuzbyt.catfact.other.NetworkMonitor
import ua.sviatkuzbyt.catfact.other.NetworkMonitorImpl
import ua.sviatkuzbyt.catfact.ui.screen.MainViewModel

val appModule = module {

    single<Converter.Factory> { GsonConverterFactory.create() }

    single(named("factApi")) {
        Retrofit.Builder()
            .baseUrl("https://meowfacts.herokuapp.com/")
            .addConverterFactory(get())
            .build()
    }

    single(named("imageApi")) {
        Retrofit.Builder()
            .baseUrl("https://cataas.com/")
            .addConverterFactory(get())
            .build()
    }

    viewModelOf(::MainViewModel)

    factory {
        get<Retrofit>(named("factApi")).create(FactManager::class.java)
    }

    factory {
        get<Retrofit>(named("imageApi")).create(ImageManager::class.java)
    }

    factoryOf(::LanguageManager)
    factory<NetworkMonitor> { NetworkMonitorImpl(get()) }
}