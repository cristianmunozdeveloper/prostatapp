package com.cristiansofthouse.navigation

import android.content.Context
import android.content.Intent
import javax.inject.Inject

internal class NavigationImpl @Inject constructor() : Navigation {

    override fun goToProstatest(context: Context): Intent {
        // should be replaced by intent going to prostates activity
        return Intent()
    }
}
