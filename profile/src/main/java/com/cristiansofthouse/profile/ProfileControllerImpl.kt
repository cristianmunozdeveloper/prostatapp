package com.cristiansofthouse.profile

import com.cristiansofthouse.profile.constants.EMPTY_SLOTS
import com.cristiansofthouse.profile.constants.NEED_NUMBERS
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProfileControllerImpl @Inject constructor() : ProfileController {
    override fun resultIMC(weight: String, size: String): Single<String> {
        return Single.create { emitter ->
            if (weight.isNotBlank() && size.isNotBlank()) {
                try {
                    val weightKg = weight.toFloat()
                    val sizeMts = size.toFloat() / 100
                    val result = weightKg / (sizeMts * sizeMts)
                    emitter.onSuccess(String.format("%.1f", result))
                } catch (e: Exception) {
                    emitter.onError(Throwable(NEED_NUMBERS))
                }
            } else emitter.onError(Throwable(EMPTY_SLOTS))
        }
    }
}
