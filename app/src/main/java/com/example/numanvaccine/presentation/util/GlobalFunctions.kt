package com.example.numanvaccine.presentation.util

fun million(n : Int): String {
    return String.format("%.2fM", n/ 1000000.0)
}