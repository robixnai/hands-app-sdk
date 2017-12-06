package br.com.rmoreira.app.adapaters.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.rmoreira.app.contracts.OnItemClickListener

/**
 * Created by robsonmoreira on 03/12/17.
 */
open class GenericViewHolder(private val mRootView: View, private val mListener: OnItemClickListener? = null) : RecyclerView.ViewHolder(mRootView), View.OnClickListener {

    private var mItem: Any? = null

    open fun onBindViewHolder(item: Any) {
        mItem = item
        mRootView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (mListener != null) {
            mListener.onItemClick(view, mItem!!)
        }
    }

}