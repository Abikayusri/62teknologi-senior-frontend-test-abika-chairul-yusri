package abika.sinau.core.di

import abika.sinau.core.domain.repository.Repository
import abika.sinau.core.domain.usecase.UseCase
import abika.sinau.core.domain.usecase.UseCaseImpl
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
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCase {
        return UseCaseImpl(repository)
    }
}