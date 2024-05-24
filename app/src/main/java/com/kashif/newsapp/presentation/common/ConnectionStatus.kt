package com.kashif.newsapp.presentation.common

/**
 * Created by Mohammad Kashif Ansari on 24,May,2024
 */
sealed class ConnectionStatus {
    object Available:ConnectionStatus()
    object Unavailable:ConnectionStatus()
}