package com.voltaire.meuflix.utils

inline fun <T> apiCall(action: () -> Resource<T>): Resource<T> {
    return try {
        action()
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Connection error")
    }
}