package com.example.numanvaccine.presentation.utils

import okhttp3.OkHttpClient

object OkHttpProvider {

    val instance: OkHttpClient = OkHttpClient.Builder().build()
}