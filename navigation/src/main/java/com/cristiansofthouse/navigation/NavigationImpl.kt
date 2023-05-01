package com.cristiansofthouse.navigation

import android.content.Context
import android.content.Intent
import com.cristiansofthouse.aboutme.AboutMeActivity
import com.cristiansofthouse.howtotakecareofme.HowToTakeCareOfMeActivity
import com.cristiansofthouse.information.InformationActivity
import com.cristiansofthouse.navigation.Destination.AboutMe
import com.cristiansofthouse.navigation.Destination.AnyDoubt
import com.cristiansofthouse.navigation.Destination.HowToTakeCareOfMe
import com.cristiansofthouse.navigation.Destination.Imc
import com.cristiansofthouse.navigation.Destination.MythsAboutMe
import com.cristiansofthouse.navigation.Destination.WhatToDoIfIGetSick
import com.cristiansofthouse.profile.ProfileActivity
import com.cristiansofthouse.tabutest.MythsAboutMeActivity
import com.cristiansofthouse.whattodoifigetsick.WhatToDoIfIGetSickActivity
import com.cristiansofthouse.whyiamsick.WhyImSickActivity
import javax.inject.Inject

internal class NavigationImpl @Inject constructor() : Navigation {

    override fun goTo(context: Context, goTo: Destination): Intent {
        return when (goTo) {
            is AnyDoubt -> Intent(context, InformationActivity::class.java)
            is HowToTakeCareOfMe -> Intent(context, HowToTakeCareOfMeActivity::class.java)
            is Imc -> Intent(context, ProfileActivity::class.java)
            is AboutMe -> Intent(context, AboutMeActivity::class.java)
            is MythsAboutMe -> Intent(context, MythsAboutMeActivity::class.java)
            is WhatToDoIfIGetSick -> Intent(context, WhatToDoIfIGetSickActivity::class.java)
            else -> Intent(context, WhyImSickActivity::class.java)
        }
    }
}
