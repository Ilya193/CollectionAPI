package ru.kraz.collectionapi.app

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.kraz.collectionapi.data.mars.MarsRepositoryImpl
import ru.kraz.collectionapi.data.mars.MarsService
import ru.kraz.collectionapi.domain.FetchImagesUseCase
import ru.kraz.collectionapi.domain.MarsRepository
import ru.kraz.collectionapi.domain.ResourceProvider
import ru.kraz.collectionapi.presentation.mars.MarsViewModel
import ru.kraz.collectionapi.presentation.mars.ToImageUiMapper

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val appModule = module {
    viewModel<MarsViewModel> {
        MarsViewModel(get(), get(), get())
    }

    factory<FetchImagesUseCase> {
        FetchImagesUseCase(get())
    }

    factory<MarsRepository> {
        MarsRepositoryImpl(get())
    }

    factory<ToImageUiMapper> {
        ToImageUiMapper()
    }

    factory<ResourceProvider> {
        ResourceProvider.Base()
    }

    single<MarsService> {
        Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MarsService::class.java)
    }
}