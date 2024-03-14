package ru.kraz.collectionapi.app

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.kraz.collectionapi.data.dummu.DummyService
import ru.kraz.collectionapi.data.dummu.ProductsRepositoryImpl
import ru.kraz.collectionapi.data.json.PostsRepositoryImpl
import ru.kraz.collectionapi.data.json.PostsService
import ru.kraz.collectionapi.data.mars.MarsRepositoryImpl
import ru.kraz.collectionapi.data.mars.MarsService
import ru.kraz.collectionapi.domain.common.StringErrorProvider
import ru.kraz.collectionapi.domain.dummy.FetchProductsUseCase
import ru.kraz.collectionapi.domain.dummy.ProductsRepository
import ru.kraz.collectionapi.domain.json.FetchPostsUseCase
import ru.kraz.collectionapi.domain.json.PostsRepository
import ru.kraz.collectionapi.domain.mars.FetchImagesUseCase
import ru.kraz.collectionapi.domain.mars.MarsRepository
import ru.kraz.collectionapi.presentation.common.BaseStringErrorProvider
import ru.kraz.collectionapi.presentation.dummy.ProductsViewModel
import ru.kraz.collectionapi.presentation.dummy.ToProductUiMapper
import ru.kraz.collectionapi.presentation.json.PostsViewModel
import ru.kraz.collectionapi.presentation.json.ToPostUiMapper
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

    viewModel<PostsViewModel> {
        PostsViewModel(get(), get(), get())
    }

    viewModel<ProductsViewModel> {
        ProductsViewModel(get(), get(), get())
    }

    factory<FetchImagesUseCase> {
        FetchImagesUseCase(get())
    }

    factory<FetchPostsUseCase> {
        FetchPostsUseCase(get())
    }

    factory<FetchProductsUseCase> {
        FetchProductsUseCase(get())
    }

    factory<MarsRepository> {
        MarsRepositoryImpl(get())
    }

    factory<PostsRepository> {
        PostsRepositoryImpl(get())
    }

    factory<ProductsRepository> {
        ProductsRepositoryImpl(get())
    }

    factory<ToImageUiMapper> {
        ToImageUiMapper()
    }

    factory<ToPostUiMapper> {
        ToPostUiMapper()
    }

    factory<ToProductUiMapper> {
        ToProductUiMapper()
    }

    single<StringErrorProvider> {
        BaseStringErrorProvider()
    }

    single<MarsService> {
        Retrofit.Builder()
            .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MarsService::class.java)
    }

    single<PostsService> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            .build()
            .create(PostsService::class.java)
    }

    single<DummyService> {
        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            .build()
            .create(DummyService::class.java)
    }
}