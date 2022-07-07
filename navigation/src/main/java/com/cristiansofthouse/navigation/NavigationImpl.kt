package com.cristiansofthouse.navigation

import android.content.Context
import android.content.Intent
import com.cristiansofthouse.tabutest.TabuTestActivity
import javax.inject.Inject

internal class NavigationImpl @Inject constructor() : Navigation {

    override fun goToProstatest(context: Context): Intent {
        // should be replaced by intent going to prostates activity
        return Intent()
    }

    override fun goToTabuTest(context: Context): Intent {
        return Intent(context, TabuTestActivity::class.java)
    }
}
