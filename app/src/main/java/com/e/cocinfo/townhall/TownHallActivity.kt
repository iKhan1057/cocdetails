package com.e.cocinfo.townhall

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import com.e.cocinfo.townhall.thdetailslistmodel.TownHallListModel
import java.util.*

class TownHallActivity : AppCompatActivity(), TowmHallListAdapter.ItemCallback {
    lateinit var townhall_list: RecyclerView
    lateinit var path: String
    var townHallListModels: MutableList<TownHallListModel> = ArrayList<TownHallListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_hall)
        initviews()
    }

    private fun initviews() {
        val category = intent.getStringExtra("CAT")
        path = intent.getStringExtra("PATH").toString()
        val colorbg = intent.getStringExtra("BAG")
        val id = intent.getStringExtra("ID").toString()

        townhall_list = findViewById(R.id.townhall_list)
        val towmHallListAdapter = TowmHallListAdapter(this, this, colorbg.toString(), id)
        townhall_list.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        townhall_list.setAdapter(towmHallListAdapter)
        townHallListModels = TownHallListParser.parseData(
            this, townHallListModels,
            path, category.toString(), id.toString()
        )
        towmHallListAdapter.setData(townHallListModels)
        val townhall_txt_title = findViewById<TextView>(R.id.townhall_txt_title)
        townhall_txt_title.text = id.replace("_", " ")
        townhall_txt_title.setBackgroundColor(Color.parseColor(colorbg))
    }

    override fun click(homeModel: TownHallListModel) {
        val intent = Intent(this, ThBasicDetailsActivity::class.java)
        intent.putExtra("MAX_BUILDING", homeModel.getMax_def_build())
        intent.putExtra("MAX_TRAPS", homeModel.getMax_def_traps())
        intent.putExtra("TH_LEVEL", homeModel.getItem_level())
        intent.putExtra("PATH", path)
        startActivity(intent)
    }
}