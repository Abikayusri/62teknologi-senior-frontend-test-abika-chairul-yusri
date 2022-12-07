package abika.sinau.core.di

import abika.sinau.core.data.source.remote.DataSource
import abika.sinau.core.data.source.remote.DataSourceImpl
import abika.sinau.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): DataSource {
        return DataSourceImpl(apiService)
    }
}