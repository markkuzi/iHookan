package com.example.ihookan.presentation.di

import androidx.room.Room
import com.example.ihookan.data.localDB.BasketDB
import com.example.ihookan.data.localDB.OrderDB
import com.example.ihookan.data.localDB.ProductsDB
import com.example.ihookan.data.repository.dataSource.ProductsApiDataSource
import com.example.ihookan.data.repository.dataSource.ProductsDataSource
import com.example.ihookan.data.repository.dataSource.orders.OrdersApiDataSource
import com.example.ihookan.data.repository.dataSource.orders.OrdersDataSource
import com.example.ihookan.data.repository.dataSourceIMPL.ProductsApiDataSourceIMPL
import com.example.ihookan.data.repository.dataSourceIMPL.ProductsDataSourceIMPL
import com.example.ihookan.data.repository.dataSourceIMPL.orders.OrdersApiDataSourceIMPL
import com.example.ihookan.data.repository.dataSourceIMPL.orders.OrdersDataSourceIMPL
import com.example.ihookan.data.repository.repository.*
import com.example.ihookan.domain.repository.BasketCall
import com.example.ihookan.domain.repository.OrdersApiCall
import com.example.ihookan.domain.repository.OrdersCall
import com.example.ihookan.domain.repository.ProductsCall
import com.example.ihookan.domain.useCase.BasketUseCase
import com.example.ihookan.domain.useCase.OrdersApiUseCase
import com.example.ihookan.domain.useCase.OrdersUseCase
import com.example.ihookan.domain.useCase.ProductsUseCase
import com.example.ihookan.presentation.ViewModel.BasketViewModel
import com.example.ihookan.presentation.ViewModel.OrdersApiViewModel
import com.example.ihookan.presentation.ViewModel.OrdersViewModel
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

    single<OrdersDataSource> {
        OrdersDataSourceIMPL(
            get()
        )
    }

    single<OrdersApiDataSource> {
        OrdersApiDataSourceIMPL(
            get()
        )
    }

    single<OrdersCall> {
        OrdersRepository(
        get(), get()
    )
    }

    single {OrdersUseCase(get())}

    viewModel { OrdersViewModel(get()) }
}

val moduleOrderApi = module {


    single<OrdersApiCall> {OrdersApiRepository(
    )}

    single {OrdersApiUseCase(get())}

    viewModel { OrdersApiViewModel(get()) }
}