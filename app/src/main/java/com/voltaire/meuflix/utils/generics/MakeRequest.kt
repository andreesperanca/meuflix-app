package com.voltaire.meuflix.utils.generics

import com.voltaire.meuflix.utils.REQUISICAO_NAO_SUCEDIDA
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> makeRequest(
    call: Call<T>,
    onSuccess: (requests: T?) -> Unit,
    onFailure: (error: String?) -> Unit
) {
    call.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onSuccess(response.body())
            } else {
                onFailure(REQUISICAO_NAO_SUCEDIDA)
            }
        }
        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure(t.message)
        }
    })
}
