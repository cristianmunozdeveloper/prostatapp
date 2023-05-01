package com.cristiansofthouse.navigation

import android.content.Context
import android.content.Intent

interface Navigation {

    fun goTo(context: Context, goTo: Destination): Intent
}
