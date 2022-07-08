package com.cristiansofthouse.profile

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val controller: ProfileController
) : ViewModel()
