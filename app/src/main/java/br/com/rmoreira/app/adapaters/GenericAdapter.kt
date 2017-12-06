package br.com.rmoreira.app.adapaters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rmoreira.app.R
import br.com.rmoreira.app.adapaters.holders.GenericViewHolder
import br.com.rmoreira.app.adapaters.holders.LoadingViewHolder
import br.com.rmoreira.app.adapaters.holders.UserDataViewHolder
import br.com.rmoreira.app.contracts.OnItemClickListener
import br.com.rmoreira.sdk.pojo.UserData

/**
 * Created by robsonmoreira on 03/12/17.
 */
class GenericAdapter(private var mItens: MutableList<Any>, private var mListener: OnItemClickListener) : RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder? {
        val view: View
        if (viewType == GenericType.USER_DATA.ordinal) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.user_data_item, parent, false)
            return UserDataViewHolder(view, mListener)
        } else if (viewType == GenericType.LOADING.ordinal) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.row_loading, parent, false)
            return LoadingViewHolder(view)
        }
        return null
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = mItens[position]
        holder.onBindViewHolder(item)
    }

    override fun getItemViewType(position: Int): Int {
        val item = mItens[position]
        return if (item is UserData)
            GenericType.USER_DATA.ordinal
        else if (item.toString() == GenericType.LOADING.getAbbreviation())
            GenericType.LOADING.ordinal
        else
            -1
    }

    override fun getItemCount(): Int {
        return mItens.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    fun updateItens(itens: MutableList<Any>, loading: Boolean) {
        mItens = itens
        if (loading) {
            if (this.mItens.size > 0)
                this.mItens.add(GenericType.LOADING.getAbbreviation())
        }
        notifyDataSetChanged()
    }

}