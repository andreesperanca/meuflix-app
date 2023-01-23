package com.voltaire.meuflix.utils

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.toastCreator(message: String) = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
