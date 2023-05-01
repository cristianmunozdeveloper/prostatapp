package com.cristiansofthouse.profile.module

import com.cristiansofthouse.profile.ProfileController
import com.cristiansofthouse.profile.ProfileControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileModule {

    @Binds
    abstract fun bindProfileController(
        profileControllerImpl: ProfileControllerImpl
    ): ProfileController
}
