package com.e.cocinfo.detailedlist

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import java.util.*

class DetailedListActivity : AppCompatActivity(), DetailedListAdapter.ItemCallback {
    lateinit var detailed_list: RecyclerView
    var detailedListModels: MutableList<DetailedListModel> = ArrayList<DetailedListModel>()
    lateinit var detailed_txt_title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_list)
        initviews()
    }

    private fun initviews() {
        val category = intent.getStringExtra("CAT")
        val path = intent.getStringExtra("PATH")
        val colorbg = intent.getStringExtra("BAG")
        val id = intent.getStringExtra("ID")
        val details_lin_parent = findViewById<LinearLayout>(R.id.details_lin_parent)
        val colors = intArrayOf(getColor(R.color.level3), Color.parseColor(colorbg))

        //create a new gradient color
        val gd = GradientDrawable(
            GradientDrawable.Orientation.BL_TR, colors
        )
        gd.cornerRadius = 0f
        details_lin_parent.background = gd
        detailed_list = findViewById(R.id.detailed_list)
        detailed_txt_title = findViewById(R.id.detailed_txt_title)
        detailed_txt_title.setText(id!!.replace("_", " "))
        var section = ""
        section = if (path!!.contains("builder")) {
            "BUILDER"
        } else {
            "HOME"
        }
        val mDetailedListAdapter = DetailedListAdapter(
            this, this,
            colorbg.toString(), id, category.toString(), section
        )
        detailed_list.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        detailed_list.setAdapter(mDetailedListAdapter)
        detailedListModels =
            DetailedListParser.parseData(this, detailedListModels, path, category.toString(), id)
        mDetailedListAdapter.setData(detailedListModels)
    }

    override fun clck(homeModel: DetailedListModel) {}
}