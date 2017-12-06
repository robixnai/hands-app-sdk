package br.com.rmoreira.app.adapaters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by robsonmoreira on 03/12/17.
 */
abstract class EndlessRecyclerOnScrollListener protected constructor(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var mPreviousTotal = 0
    private var mLoading = true
    private val mvisibleThreshold = 5
    private var mFirstVisibleItem: Int = 0
    private var mVisibleItemCount: Int = 0
    private var mTotalItemCount: Int = 0
    private var mCurrentPage = 1

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        mVisibleItemCount = recyclerView!!.childCount
        mTotalItemCount = mLinearLayoutManager.itemCount
        mFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (mLoading) {
            if (mTotalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = mTotalItemCount
            }
        }
        if (!mLoading && mTotalItemCount - mVisibleItemCount <= mFirstVisibleItem + mvisibleThreshold) {
            mCurrentPage++
            onLoadMore(mCurrentPage)
            mLoading = true
        }
    }

    abstract fun onLoadMore(current_page: Int)

}