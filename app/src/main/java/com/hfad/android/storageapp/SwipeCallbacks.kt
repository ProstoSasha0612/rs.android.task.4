package com.hfad.android.storageapp

import com.hfad.android.storageapp.model.Human

interface SwipeCallbacks {
    fun leftSwipe(human: Human?)
    fun rightSwipe(human: Human?)
}