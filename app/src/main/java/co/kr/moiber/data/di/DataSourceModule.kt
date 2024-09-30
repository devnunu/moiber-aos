package co.kr.moiber.data.di

import co.kr.moiber.data.community.datasource.CommunityDataSource
import co.kr.moiber.data.community.datasource.CommunityDataSourceImpl
import co.kr.moiber.data.weather.datasource.WeatherDataSource
import co.kr.moiber.data.weather.datasource.WeatherDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindWeatherDataSource(dataSourceImpl: WeatherDataSourceImpl): WeatherDataSource

    @Singleton
    @Binds
    abstract fun bindCommunityDataSource(dataSourceImpl: CommunityDataSourceImpl): CommunityDataSource
}

