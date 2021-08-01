package com.e.cocinfo.detailedlist

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import java.util.*

class DetailedListAdapter(
    private val mActivity: Activity,
    private val mItemCallback: ItemCallback,
    private val colorbg: String,
    private val item: String,
    category: String,
    section: String
) :
    RecyclerView.Adapter<DetailedListAdapter.ViewHolder>() {
    private var cat = ""
    private var homeModels: MutableList<DetailedListModel> = ArrayList<DetailedListModel>()
    private var section = ""

    interface ItemCallback {
        fun clck(homeModel: DetailedListModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mActivity).inflate(R.layout.adapter_detailed_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: DetailedListModel = homeModels[position]
        //        holder.lin_parent.setBackgroundColor(Color.parseColor(colorbg));
        if (section == "BUILDER" && item == "army_camp") {
            holder.level.setText(model.getCamp_count())
            holder.txt_level_tag.text = "Count"
        } else holder.level.setText(model.getItem_level())
        if (item == "builder_barracks" || cat == "army_building" && model.getTroop_unlocked().length > 0
        ) {
            holder.lin_unlocked.visibility = View.VISIBLE
            holder.th_unlocked.setText(model.getTroop_unlocked())
        }
        holder.th_level.setText(model.getTh_level())
        holder.itemView.setTag(R.string.MODEL, model)
        holder.itemView.setTag(R.string.position, position)
        if (item == "walls") {
            holder.txt_tag_one_separator.visibility = View.GONE
            holder.buildTime.text = "N/A"
            holder.lin_parent.setBackgroundColor(Color.parseColor(model.getBag()))
            holder.lin_level.visibility = View.GONE
            val sppan = """
                LEVEL :  ${model.getItem_level().toString()}
                BUILD COST 
                GOLD :  ${model.getCostGold().toString()}
                Cummulative(GOLD) :  ${model.getCumulativeCostGold().toString()}
                ELIXIR :  ${model.getCostElixir().toString()}
                Cummulative(ELIXIR) :  ${model.getCumulativeCostElixir().toString()}
                WALL RING :  ${model.getCostWallRing()}
                """.trimIndent()
            holder.txt_tag_one.text = sppan
        } else if (cat == "heros") {
            holder.txt_tag_one.text = mActivity.getString(R.string.detail_tag_training_cost)
            holder.txt_tag_two.text = mActivity.getString(R.string.detail_tag_upgrading_time)
            holder.buildTime.setText(model.getBuild_time())
            holder.buildCost.setText(model.getBuild_cost())
        } else if (cat == "pets") {
            holder.lin_th.visibility = View.GONE
            holder.buildTime.setText(model.getBuild_time())
            holder.buildCost.setText(model.getBuild_cost())
        } else {
            holder.buildTime.setText(model.getBuild_time())
            holder.buildCost.setText(model.getBuild_cost())
        }
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            try {
                mItemCallback.clck(model)
                val position = v.getTag(R.string.position) as Int
                val rootModel: DetailedListModel = v.getTag(R.string.MODEL) as DetailedListModel
                if (rootModel.getModels().isEmpty()) {
                    return@OnClickListener
                }
                when (rootModel.openstate) {
                    DetailedListModel.STATE.CLOSED -> {
                        homeModels.addAll(position + 1, rootModel.getModels())
                        notifyItemRangeInserted(position + 1, rootModel.getModels().size)
                        notifyItemRangeChanged(
                            position + rootModel.getModels().size,
                            homeModels.size - (position + rootModel.getModels().size)
                        )
                        notifyItemRangeChanged(position, homeModels.size - position)
                        rootModel.openstate = DetailedListModel.STATE.OPENED
                    }
                    DetailedListModel.STATE.OPENED -> {
                        val start = position + 1
                        var end = homeModels.size
                        var i = start
                        while (i < homeModels.size) {
                            val model1: DetailedListModel = homeModels[i]
                            if (model1.getLevel() <= rootModel.getLevel()) {
                                end = i
                                break
                            } else {
                                if (model1.openstate === DetailedListModel.STATE.OPENED) {
                                    model1.openstate = DetailedListModel.STATE.CLOSED
                                }
                            }
                            i++
                        }
                        if (end != -1) {
                            homeModels.subList(start, end).clear()
                            notifyItemRangeRemoved(start, end - start)
                            notifyItemRangeChanged(start, end - start)
                            notifyItemRangeChanged(position, homeModels.size - position)
                        }
                        rootModel.openstate = DetailedListModel.STATE.CLOSED
                    }
                }
            } catch (e: Exception) {
            }
        })
    }

    override fun getItemCount(): Int {
        return if (homeModels != null) homeModels.size else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_level_tag: TextView
        var level: TextView
        var th_level: TextView
        var buildTime: TextView
        var buildCost: TextView
        var txt_tag_one: TextView
        var txt_tag_two: TextView
        var txt_tag_three: TextView
        var txt_tag_one_separator: TextView
        var th_unlocked: TextView
        var lin_parent: LinearLayout
        var lin_level: LinearLayout
        var lin_th: LinearLayout
        var lin_unlocked: LinearLayout

        init {
            th_unlocked = itemView.findViewById(R.id.th_unlocked)
            lin_unlocked = itemView.findViewById(R.id.lin_unlocked)
            txt_level_tag = itemView.findViewById(R.id.txt_level_tag)
            lin_th = itemView.findViewById(R.id.lin_th)
            txt_tag_one_separator = itemView.findViewById(R.id.txt_tag_one_separator)
            lin_level = itemView.findViewById(R.id.lin_level)
            lin_parent = itemView.findViewById(R.id.lin_parent)
            level = itemView.findViewById(R.id.level)
            txt_tag_one = itemView.findViewById(R.id.txt_tag_one)
            txt_tag_two = itemView.findViewById(R.id.txt_tag_two)
            txt_tag_three = itemView.findViewById(R.id.txt_tag_three)
            th_level = itemView.findViewById(R.id.th_level)
            buildTime = itemView.findViewById(R.id.buildTime)
            buildCost = itemView.findViewById(R.id.buildCost)
        }
    }

    fun setData(list: MutableList<DetailedListModel>) {
        homeModels = list
        notifyDataSetChanged()
    }

    companion object {
        fun convertDpToPixel(dp: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        /**
         * This method converts device specific pixels to density independent pixels.
         *
         * @param px      A value in px (pixels) unit. Which we need to convert into db
         * @param context Context to get resources and device specific display metrics
         * @return A float value to represent dp equivalent to px value
         */
        fun convertPixelsToDp(px: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    init {
        cat = category
        this.section = section
    }
}