package com.cristiansofthouse.profile

class ProfileControllerImpl : ProfileController {
    override fun resultIMC(weight: String, size: String): String {
        val weightKg = weight.toFloat()
        val sizeMts = size.toFloat() / 100
        val result = weightKg / (sizeMts * sizeMts)
        return result.toString()
    }
}
