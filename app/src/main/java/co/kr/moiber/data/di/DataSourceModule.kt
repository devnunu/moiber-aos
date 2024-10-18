package co.kr.moiber.data.di

import co.kr.moiber.data.community.datasource.MemoryCommunityDataSource
import co.kr.moiber.data.community.datasource.MemoryCommunityDataSourceImpl
import co.kr.moiber.data.community.datasource.RemoteCommunityDataSource
import co.kr.moiber.data.community.datasource.RemoteCommunityDataSourceImpl
import co.kr.moiber.data.weather.datasource.WeatherDataSource
import co.kr.moiber.data.weather.datasource.WeatherDataSourceImpl
import dagger.Binds
import dagger.Module
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
    abstract fun bindRemoteCommunityDataSource(dataSourceImpl: RemoteCommunityDataSourceImpl): RemoteCommunityDataSource

    @Singleton
    @Binds
    abstract fun bindMemoryCommunityDataSource(dataSourceImpl: MemoryCommunityDataSourceImpl): MemoryCommunityDataSource
}

