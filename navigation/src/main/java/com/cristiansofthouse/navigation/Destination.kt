package com.cristiansofthouse.navigation

sealed class Destination {

    object AboutMe : Destination()
    object Imc : Destination()
    object WhyImSick : Destination()
    object WhatToDoIfIGetSick : Destination()
    object HowToTakeCareOfMe : Destination()
    object MythsAboutMe : Destination()
    object AnyDoubt : Destination()
}
