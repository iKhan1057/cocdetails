package com.e.cocinfo.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.cocinfo.R
import com.e.cocinfo.detailedlist.DetailedListActivity
import com.e.cocinfo.otto.OttoActivity
import com.e.cocinfo.townhall.TownHallActivity
import java.util.*

class HomeActivity : AppCompatActivity(), HomeAdapter.ItemCallback {
    var homeModels: MutableList<HomeModel> = ArrayList()

    lateinit var rvList: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initviews()
    }

    private fun initviews() {
        rvList = findViewById(R.id.list)
        val homeAdapter = HomeAdapter(this, this)
        rvList.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        rvList.setAdapter(homeAdapter)
        homeModels = HomeParser.parseData(this, homeModels)
        homeAdapter.setData(homeModels)
    }

    override fun clck(id: HomeModel) {
        if (id.isChild()) {
            if (id.getCategory().equals("super_troops")) {
                Toast.makeText(this, "No details for: " + id.getName(), Toast.LENGTH_SHORT).show()
            } else if (id.getId().equals("th")) {
//                Toast.makeText(this, id.getId(), Toast.LENGTH_SHORT).show();
                val intent = Intent(this, TownHallActivity::class.java)
                intent.putExtra("PATH", id.getPath())
                intent.putExtra("BAG", id.getBag())
                intent.putExtra("CAT", id.getCategory())
                intent.putExtra("ID", id.getId())
                startActivity(intent)
            } else if (id.getId().equals("otto")) {
//                Toast.makeText(this, id.getId(), Toast.LENGTH_SHORT).show();
                val intent = Intent(this, OttoActivity::class.java)
                intent.putExtra("PATH", id.getPath())
                intent.putExtra("BAG", id.getBag())
                intent.putExtra("CAT", id.getCategory())
                intent.putExtra("ID", id.getId())
                startActivity(intent)
            } else {
//                Toast.makeText(this, id.getId(), Toast.LENGTH_SHORT).show();
                val intent = Intent(this, DetailedListActivity::class.java)
                intent.putExtra("PATH", id.getPath())
                intent.putExtra("BAG", id.getBag())
                intent.putExtra("CAT", id.getCategory())
                intent.putExtra("ID", id.getId())
                startActivity(intent)
            }
        }
    }
}
