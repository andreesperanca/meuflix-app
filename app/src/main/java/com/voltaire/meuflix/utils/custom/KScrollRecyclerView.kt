package com.voltaire.meuflix.utils.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.Interpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlin.math.abs

class KScrollRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val SPEED = 10

    private var mInterpolator = UniformSpeedInterpolator()
    private var mSpeedDx = 0
    private var mSpeedDy = 0
    private var mCurrentSpeed = SPEED
    private var isInfinite = false
    private var mReverse = false
    private var mIsOpenAuto = false
    private var mCanTouch = true
    private var mPointTouch = false
    private var mReady = false
    private var mInflate = false
    private var isStopAutoScroll = false

    private var mCurrentPosition = 0


    fun startAutoScroll() {
        isStopAutoScroll = false
        OpenAutoScroll(mCurrentSpeed, false)
    }

    fun OpenAutoScroll(speed: Int, reverse: Boolean) {
        mReverse = reverse
        mCurrentSpeed = speed
        mIsOpenAuto = true
        notifyLayoutManager()
        startScroll()
    }

    fun setCanTouch(b: Boolean) {
        mCanTouch = b
    }

    fun canTouch(): Boolean = mCanTouch

    fun setLoopEnabled(isInfinite: Boolean) {
        this.isInfinite = isInfinite
        if (adapter != null) {
            adapter!!.notifyDataSetChanged()
            startScroll()
        }
    }

    fun setIsInfinite(isInfinite: Boolean) {
        this.isInfinite = isInfinite
    }

    fun setReverse(reverse: Boolean) {
        mReverse = reverse
        notifyLayoutManager()
        startScroll()
    }

    fun pauseAutoScroll(isStopAutoScroll: Boolean) {
        this.isStopAutoScroll = isStopAutoScroll
    }

    fun getReverse(): Boolean = mReverse

    fun startScroll() {
        if (!mIsOpenAuto) return

        if (scrollState == SCROLL_STATE_SETTLING) return

        if (mInflate && mReady) {
            mSpeedDx = 0
            mSpeedDy = 0
            smoothScroll()
        }
    }

    private fun smoothScroll() {
        if (!isStopAutoScroll) {
            val absSpeed = abs(mCurrentSpeed)
            val d = if (mReverse) -absSpeed else absSpeed
            smoothScrollBy(d, d, mInterpolator)
        }
    }

    private fun notifyLayoutManager() {
        val layoutManager = layoutManager

        if (layoutManager is LinearLayoutManager) {
            val linearLayoutManager = layoutManager as LinearLayoutManager?
            if (linearLayoutManager != null) {
                linearLayoutManager.reverseLayout = mReverse
            }
        } else {
            val staggeredGridLayoutManager = layoutManager as StaggeredGridLayoutManager?
            if (staggeredGridLayoutManager != null) {
                staggeredGridLayoutManager.reverseLayout = mReverse
            }
        }
    }

    override fun swapAdapter(adapter: Adapter<*>?, removeAndRecycleExistingViews: Boolean) {
        super.swapAdapter(adapter, removeAndRecycleExistingViews)
        mReady = true
    }

    override fun setAdapter(adapter: Adapter<ViewHolder>?) {
        super.setAdapter(generateAdapter(adapter = adapter!!))
        mReady = true
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        if (mCanTouch) {
            when (e!!.action) {
                MotionEvent.ACTION_DOWN -> mPointTouch = true
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> if (mIsOpenAuto) {
                    return true
                }
            }
            return super.onInterceptTouchEvent(e)
        } else {
            return false
        }
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        if (mCanTouch) {
            when (e!!.action) {
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> if (mIsOpenAuto) {
                    mPointTouch = false
                    smoothScroll()
                    return true
                }
            }
            return super.onTouchEvent(e)
        } else return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        startScroll()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mInflate = true
    }

    override fun smoothScrollToPosition(position: Int) {
        super.smoothScrollToPosition(position)
    }

    override fun onScrolled(dx: Int, dy: Int) {
        if (mPointTouch) {
            mSpeedDx = 0
            mSpeedDy = 0
        }
        val vertical: Boolean

        if (dx == 0) {//VERTICAL SCROLLING
            mSpeedDy += dy
            vertical = true
        } else {//HORIZONTAL SCROLLING
            mSpeedDx += dx
            vertical = false
        }

        if (vertical) {
            if (abs(mSpeedDy) >= abs(mCurrentSpeed)) {
                mSpeedDy = 0
                smoothScroll()
            }
        } else {
            if (abs(mSpeedDx) >= abs(mCurrentSpeed)) {
                mSpeedDx = 0
                smoothScroll()
            }
        }
    }

    private fun generateAdapter(adapter: Adapter<ViewHolder>): NestingRecyclerViewAdapter<*>? {
        return NestingRecyclerViewAdapter<ViewHolder>(this, adapter)
    }


    private class UniformSpeedInterpolator : Interpolator {
        override fun getInterpolation(input: Float): Float = input
    }

    private inner class NestingRecyclerViewAdapter<VH : RecyclerView.ViewHolder>
        (
        private val recyclerView: KScrollRecyclerView,
        private val adapter: RecyclerView.Adapter<VH>
    ) : RecyclerView.Adapter<VH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            return adapter.onCreateViewHolder(parent, viewType)
        }

        override fun registerAdapterDataObserver(observer: AdapterDataObserver) {
            super.registerAdapterDataObserver(observer)
            adapter.registerAdapterDataObserver(observer)
        }

        override fun unregisterAdapterDataObserver(observer: AdapterDataObserver) {
            super.unregisterAdapterDataObserver(observer)
            adapter.unregisterAdapterDataObserver(observer)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            adapter.onBindViewHolder(holder, generatePosition(position))
        }

        override fun setHasStableIds(hasStableIds: Boolean) {
            super.setHasStableIds(hasStableIds)
            adapter.setHasStableIds(hasStableIds)
        }

        override fun getItemCount(): Int {
            //If it is an infinite scroll mode, set an unlimited number of items
            return if (loopEnable) Int.MAX_VALUE else adapter.itemCount
        }

        override fun getItemViewType(position: Int): Int {
            return adapter.getItemViewType(generatePosition(position))
        }

        override fun getItemId(position: Int): Long {
            return adapter.getItemId(generatePosition(position))
        }

        /**
         * Returns the corresponding position according to the current scroll mode
         */
        private fun generatePosition(position: Int): Int {
            return if (loopEnable) {
                getActualPosition(position)
            } else {
                position
            }
        }

        /**
         * Returns the actual position of the item
         *
         * @param position The position after starting to scroll will grow indefinitely
         * @return Item actual location
         */
        fun getActualPosition(position: Int): Int {
            val itemCount = adapter.itemCount
            return if (position >= itemCount) position % itemCount else position
        }

        private val loopEnable: Boolean
            private get() = recyclerView.isInfinite
        val reverse: Boolean
            get() = recyclerView.mReverse
    }

}