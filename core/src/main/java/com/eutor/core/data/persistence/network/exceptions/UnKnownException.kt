package com.eutor.core.data.persistence.network.exceptions

import java.lang.Exception

class UnKnownException : Exception() {

    override val message: String
        get() = "Some Unknown Error Occurred"
}