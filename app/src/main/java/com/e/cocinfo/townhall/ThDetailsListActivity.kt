package com.e.cocinfo.townhall

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import com.e.cocinfo.townhall.thdetailslistmodel.Resource
import com.e.cocinfo.townhall.thdetailslistmodel.THDetailsModel
import java.util.*

class ThDetailsListActivity : AppCompatActivity() {
    var thDetailsModel: THDetailsModel = THDetailsModel()
    var resourceList: List<Resource> = ArrayList<Resource>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_th_details_list)
        initviews()
    }

    private fun initviews() {
        val th = intent.getStringExtra("TH")
        val category = intent.getStringExtra("CATEGORY")
        val path = intent.getStringExtra("PATH")
        (getString(R.string.thbasic_th_level) + th).also { (findViewById<View>(R.id.thdetaillist_txt_title) as TextView).text = it }
        (findViewById<View>(R.id.thdetaillist_txt_type) as TextView).text =
            intent.getStringExtra("TITLE")
        val thdetaillist_list = findViewById<RecyclerView>(R.id.thdetaillist_list)
        val thDetailsListAdapter = ThDetailsListAdapter(this, resourceList)
        thdetaillist_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        thdetaillist_list.adapter = thDetailsListAdapter
        val position = th!!.toInt()
        thDetailsModel = TownHallListParser.parseResData(
            this, thDetailsModel, path!!,
            category!!
        )
        thDetailsListAdapter.setData(
            thDetailsModel.getResourseBuilding().get(position - 1).getResources()
        )
    }
}