package com.cristiansofthouse.testhistory.di

import com.cristiansofthouse.testhistory.controller.TestHistoryController
import com.cristiansofthouse.testhistory.controller.TestHistoryControllerImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TestHistoryModule {

    @Binds
    @Reusable
    abstract fun provideTestHistoryController(testHistoryControllerImpl: TestHistoryControllerImpl): TestHistoryController
}
