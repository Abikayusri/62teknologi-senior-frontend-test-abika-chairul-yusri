package abika.sinau.core.di

import abika.sinau.core.data.repository.RepositoryImpl
import abika.sinau.core.data.source.remote.DataSource
import abika.sinau.core.domain.repository.Repository
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
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(dataSource: DataSource): Repository {
        return RepositoryImpl(dataSource)
    }
}