package com.e.cocinfo.townhall

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import com.e.cocinfo.townhall.thdetailslistmodel.TownHallListModel
import java.util.*

class TowmHallListAdapter(
    private val mActivity: Activity,
    private val mItemCallback: ItemCallback,
    private val colorbg: String,
    private val item: String
) :
    RecyclerView.Adapter<TowmHallListAdapter.ViewHolder>() {
    private var homeModels: List<TownHallListModel> = ArrayList<TownHallListModel>()

    interface ItemCallback {
        fun click(homeModel: TownHallListModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mActivity).inflate(R.layout.adapter_th_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: TownHallListModel = homeModels[position]
        holder.lin_parent.setBackgroundColor(Color.parseColor(colorbg))
        holder.level.setText(model.getItem_level())
        holder.itemView.setTag(R.string.MODEL, model)
        holder.itemView.setTag(R.string.position, position)
        holder.buildTime.setText(model.getBuild_time())
        holder.buildCost.setText(model.getBuild_cost())
        holder.itemView.setOnClickListener { mItemCallback.click(model) }
    }

    override fun getItemCount(): Int {
        return if (homeModels != null) homeModels.size else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var level: TextView
        var buildTime: TextView
        var buildCost: TextView
        var lin_level: LinearLayout
        var lin_parent: RelativeLayout

        init {
            lin_level = itemView.findViewById(R.id.lin_level)
            lin_parent = itemView.findViewById(R.id.lin_parent)
            level = itemView.findViewById(R.id.level)
            buildTime = itemView.findViewById(R.id.buildTime)
            buildCost = itemView.findViewById(R.id.buildCost)
        }
    }

    fun setData(list: List<TownHallListModel>) {
        homeModels = list
        notifyDataSetChanged()
    }
}