package com.cristiansofthouse.navigation

import android.content.Context
import android.content.Intent
import com.cristiansofthouse.profile.ProfileActivity
import com.cristiansofthouse.prostatest.ProstaTestActivity
import com.cristiansofthouse.tabutest.TabuTestActivity
import javax.inject.Inject

internal class NavigationImpl @Inject constructor() : Navigation {

    override fun goToProstatest(context: Context): Intent {
        // should be replaced by intent going to prostates activity
        return Intent(context, ProstaTestActivity::class.java)
    }

    override fun goToTabuTest(context: Context): Intent {
        return Intent(context, TabuTestActivity::class.java)
    }

    override fun goToProfile(context: Context): Intent {
        return Intent(context, ProfileActivity::class.java)
    }
}
