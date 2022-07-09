package com.cristiansofthouse.profile

import io.reactivex.rxjava3.core.Single

interface ProfileController {
    fun resultIMC(weight: String, size: String): Single<String>
}
