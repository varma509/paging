package com.eutor.core

class Cache private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: Cache? = null

        @Synchronized
        fun getInstance(): Cache = INSTANCE ?: Cache().also { INSTANCE = it }
    }

}