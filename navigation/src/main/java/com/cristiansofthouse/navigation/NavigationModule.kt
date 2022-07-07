package com.cristiansofthouse.navigation

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationModule {

    @Binds
    @Reusable
    abstract fun provideNavigation(navigation: NavigationImpl): Navigation
}
