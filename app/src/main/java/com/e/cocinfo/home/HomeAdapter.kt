package com.e.cocinfo.home

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import java.util.*

class HomeAdapter(private val mActivity: Activity, private val mItemCallback: ItemCallback) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var homeModels: MutableList<HomeModel> = ArrayList()

    open interface ItemCallback {
        fun clck(homeModel: HomeModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mActivity).inflate(R.layout.adapter_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = homeModels[position]
        holder.textView.text = model.getName()
        holder.parent.setBackgroundColor(model.getLevelbg())
        holder.itemView.setTag(R.string.MODEL, model)
        holder.itemView.setTag(R.string.position, position)
        val layoutParams = holder.rlContent.layoutParams as RelativeLayout.LayoutParams
        layoutParams.setMargins(
            convertDpToPixel(15f, mActivity).toInt() * model.getLevel(),
            layoutParams.topMargin,
            layoutParams.rightMargin,
            layoutParams.bottomMargin
        )
        when (model.openstate) {
            HomeModel.STATE.CLOSED -> holder.imgArrow.rotation = 0f
            HomeModel.STATE.OPENED -> holder.imgArrow.rotation = 180f
        }
        if (model.getModels().isEmpty()) {
            holder.imgArrow.visibility = View.INVISIBLE
        } else {
            holder.imgArrow.visibility = View.VISIBLE
        }
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            try {
                mItemCallback.clck(model)
                val position = v.getTag(R.string.position) as Int
                val rootModel = v.getTag(R.string.MODEL) as HomeModel
                if (rootModel.getModels().isEmpty()) {
                    return@OnClickListener
                }
                when (rootModel.openstate) {
                    HomeModel.STATE.CLOSED -> {
                        homeModels.addAll(position + 1, rootModel.getModels())
                        notifyItemRangeInserted(position + 1, rootModel.getModels().size)
                        notifyItemRangeChanged(
                            position + rootModel.getModels().size,
                            homeModels.size - (position + rootModel.getModels()
                                .size)
                        )
                        notifyItemRangeChanged(position, homeModels.size - position)
                        rootModel.openstate = HomeModel.STATE.OPENED
                    }
                    HomeModel.STATE.OPENED -> {
                        val start = position + 1
                        var end = homeModels.size
                        var i = start
                        while (i < homeModels.size) {
                            val model1 = homeModels[i]
                            if (model1.getLevel() <= rootModel.getLevel()) {
                                end = i
                                break
                            } else {
                                if (model1.openstate === HomeModel.STATE.OPENED) {
                                    model1.openstate = HomeModel.STATE.CLOSED
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
                        rootModel.openstate = HomeModel.STATE.CLOSED
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
        var rlContent: RelativeLayout
        var parent: RelativeLayout
        var textView: TextView
        var imgArrow: ImageView

        init {
            textView = itemView.findViewById(R.id.tvName)
            imgArrow = itemView.findViewById(R.id.imgArrow)
            rlContent = itemView.findViewById(R.id.rlContent)
            parent = itemView.findViewById(R.id.parent)
        }
    }

    fun setData(list: MutableList<HomeModel>) {
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
}