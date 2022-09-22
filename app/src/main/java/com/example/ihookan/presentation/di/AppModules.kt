package com.example.ihookan.presentation.di

import androidx.room.Room
import com.example.ihookan.data.localDB.BasketDB
import com.example.ihookan.data.localDB.OrderDB
import com.example.ihookan.data.localDB.ProductsDB
import com.example.ihookan.data.repository.dataSource.ProductsApiDataSource
import com.example.ihookan.data.repository.dataSource.ProductsDataSource
import com.example.ihookan.data.repository.dataSourceIMPL.ProductsApiDataSourceIMPL
import com.example.ihookan.data.repository.dataSourceIMPL.ProductsDataSourceIMPL
import com.example.ihookan.data.repository.repository.BasketRepository
import com.example.ihookan.data.repository.repository.OrderApiRepository
import com.example.ihookan.data.repository.repository.OrderRepository
import com.example.ihookan.data.repository.repository.ProductsRepository
import com.example.ihookan.domain.repository.BasketCall
import com.example.ihookan.domain.repository.OrderApiCall
import com.example.ihookan.domain.repository.OrderCall
import com.example.ihookan.domain.repository.ProductsCall
import com.example.ihookan.domain.useCase.BasketUseCase
import com.example.ihookan.domain.useCase.OrderApiUseCase
import com.example.ihookan.domain.useCase.OrderUseCase
import com.example.ihookan.domain.useCase.ProductsUseCase
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.OrderApiViewModel
import com.example.ihookan.presentation.ViewModel.OrderViewModel
import com.example.ihookan.presentation.ViewModel.ProductsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val moduleProducts = module {

    single {
        Room.databaseBuilder(androidContext(), ProductsDB::class.java,
        "ProductsDB").build()
    }

    single { get<ProductsDB>().productsDao }

    single<ProductsDataSource> {
        ProductsDataSourceIMPL(
            get()
        )
    }

    single<ProductsApiDataSource> {
        ProductsApiDataSourceIMPL(
            get()
        )
    }

    single<ProductsCall> {ProductsRepository(
        get(),get()
    )}

    single {ProductsUseCase(get())}

    viewModel { ProductsViewModel(get()) }
}

val moduleBasket = module {

    single {
        Room.databaseBuilder(androidContext(), BasketDB::class.java,
            "BasketDB").build()
    }

    single { get<BasketDB>().basketDao }


    single<BasketCall> {BasketRepository(
        get()
    )}

    single {BasketUseCase(get())}

    viewModel { BasketViewModel(get()) }
}

val moduleOrder = module {

    single {
        Room.databaseBuilder(androidContext(), OrderDB::class.java,
            "OrderDB").build()
    }

    single { get<OrderDB>().orderDao }


    single<OrderCall> {OrderRepository(
        get()
    )}

    single {OrderUseCase(get())}

    viewModel { OrderViewModel(get()) }
}

val moduleOrderApi = module {


    single<OrderApiCall> {OrderApiRepository(
    )}

    single {OrderApiUseCase(get())}

    viewModel { OrderApiViewModel(get()) }
}