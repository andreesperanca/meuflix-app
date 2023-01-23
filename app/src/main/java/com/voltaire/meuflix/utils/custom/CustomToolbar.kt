package com.voltaire.meuflix.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.voltaire.meuflix.R
import com.voltaire.meuflix.databinding.CustomToolbarBinding

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = CustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar,0,0)
        typedArray.recycle()
    }

    fun userPhotoProfileClick(action: () -> Unit) { binding.cvUserPhoto.setOnClickListener { action() } }
    fun favoritesClick(action: () -> Unit) { binding.btnFavorites.setOnClickListener { action() } }
    fun searchIconClick(action: () -> Unit) { binding.btnSearch.setOnClickListener { action() } }


}