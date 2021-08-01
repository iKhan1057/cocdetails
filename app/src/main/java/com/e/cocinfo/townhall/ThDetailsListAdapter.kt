package com.e.cocinfo.townhall

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import com.e.cocinfo.townhall.thdetailslistmodel.Resource

class ThDetailsListAdapter(private val mActivity: Activity, mResourseBuildings: List<Resource>) :
    RecyclerView.Adapter<ThDetailsListAdapter.ViewHolder>() {
    private var mResourseBuildings: List<Resource>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mActivity).inflate(R.layout.adapter_thdetails_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_col_one.setText(mResourseBuildings!![position].getName())
        holder.txt_col_two.setText(mResourseBuildings!![position].getMaxNo())
        holder.txt_col_three.setText(mResourseBuildings!![position].getMaxUpdate())
        holder.txt_col_two.setTextColor(mActivity.resources.getColor(R.color.level5))
        holder.txt_col_three.setTextColor(mActivity.resources.getColor(R.color.level5))
        holder.txt_col_two.setBackgroundColor(mActivity.resources.getColor(R.color.white))
        holder.txt_col_three.setBackgroundColor(mActivity.resources.getColor(R.color.white))
    }

    override fun getItemCount(): Int {
        return if (mResourseBuildings != null) mResourseBuildings!!.size else 0
    }

    fun setData(resources: List<Resource>) {
        mResourseBuildings = resources
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_col_one: TextView
        var txt_col_two: TextView
        var txt_col_three: TextView

        init {
            txt_col_one = itemView.findViewById(R.id.txt_col_one)
            txt_col_two = itemView.findViewById(R.id.txt_col_two)
            txt_col_three = itemView.findViewById(R.id.txt_col_three)
        }
    }

    init {
        this.mResourseBuildings = mResourseBuildings
    }
}