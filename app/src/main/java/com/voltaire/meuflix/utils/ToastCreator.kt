package com.voltaire.meuflix.utils

import android.content.Context
import android.widget.Toast

public fun toastCreator(context : Context, message: String?) {
    Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT).show()
}