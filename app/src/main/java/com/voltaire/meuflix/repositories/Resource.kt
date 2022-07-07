package com.voltaire.meuflix.repositories

class Resource<T> (
    var data: T? = null,
    var error: String? = null
)