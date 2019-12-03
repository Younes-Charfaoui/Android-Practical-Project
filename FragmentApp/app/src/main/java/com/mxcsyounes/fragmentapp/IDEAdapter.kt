package com.mxcsyounes.fragmentapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_content.view.*

class IDEAdapter(
    private val parentActivity: IDEListActivity,
    private val values: List<IDEContent.IDEItem>,
    private val isTablet: Boolean) : RecyclerView.Adapter<IDEAdapter.IDEViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { view ->
            val item = view.tag as IDEContent.IDEItem
            if (isTablet) {
                val fragment = IDEDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(IDEDetailFragment.ARG_ITEM_ID, item.id)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.ide_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(view.context, IDEDetailActivity::class.java).apply {
                    putExtra(IDEDetailFragment.ARG_ITEM_ID, item.id)
                }
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IDEViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return IDEViewHolder(view)
    }

    override fun onBindViewHolder(holderIDE: IDEViewHolder, position: Int) {
        val item = values[position]
        holderIDE.idView.text = item.id
        holderIDE.contentView.text = item.name

        with(holderIDE.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class IDEViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}