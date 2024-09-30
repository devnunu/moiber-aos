package co.kr.moiber.data.di

import co.kr.moiber.data.community.repository.CommunityRepository
import co.kr.moiber.data.community.repository.CommunityRepositoryImpl
import co.kr.moiber.data.weather.datasource.WeatherDataSource
import co.kr.moiber.data.weather.datasource.WeatherDataSourceImpl
import co.kr.moiber.data.weather.repository.WeatherRepository
import co.kr.moiber.data.weather.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Singleton
    @Binds
    abstract fun bindCommunityRepository(repositoryImpl: CommunityRepositoryImpl): CommunityRepository
}