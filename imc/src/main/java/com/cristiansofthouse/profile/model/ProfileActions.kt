package com.cristiansofthouse.profile.model

sealed class ProfileActions {
    class ShowResult(val result: String) : ProfileActions()
    class ShowError(val message: String?) : ProfileActions()
}
