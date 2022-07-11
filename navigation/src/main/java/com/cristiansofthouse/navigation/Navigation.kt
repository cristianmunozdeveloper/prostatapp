package com.cristiansofthouse.navigation

import android.content.Context
import android.content.Intent

interface Navigation {

    fun goToProstatest(context: Context): Intent
    fun goToTabuTest(context: Context): Intent
    fun goToProfile(context: Context): Intent
    fun goToInformation(context: Context): Intent

}
