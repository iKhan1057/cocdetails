package com.e.cocinfo.townhall

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.e.cocinfo.R
import java.util.*

class ThBasicDetailsActivity : AppCompatActivity() {

    private val thStorages: MutableList<ThStorage> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_th_basic_details)
        initviews()
    }

    private fun initviews() {
        val th = intent.getStringExtra("TH_LEVEL")
        val path = intent.getStringExtra("PATH")

        val thbasic_txt_title = findViewById<TextView>(R.id.thbasic_txt_title)
        (getString(R.string.thbasic_th_level) + th).also { thbasic_txt_title.text = it }
        thbasic_txt_title.setBackgroundColor(getColor(R.color.level5))
        (findViewById<View>(R.id.thbasic_txt_max_building) as TextView).text =
            intent.getStringExtra("MAX_BUILDING")
        (findViewById<View>(R.id.thbasic_txt_max_traps) as TextView).text =
            intent.getStringExtra("MAX_TRAPS")

        val position = th.toString().toInt()
        val thStorage = TownHallListParser.parseStorageData(this, path.toString(), thStorages)[position - 1]
        (findViewById<View>(R.id.thbasic_txt_storage_gold) as TextView).text =
            thStorage.getGold()
        (findViewById<View>(R.id.thbasic_txt_storage_elixir) as TextView).text =
            thStorage.getElixir()
        (findViewById<View>(R.id.thbasic_txt_storage_dark_elixir) as TextView).text =
            thStorage.getDark()
        if (path.toString().contains("builder")) (findViewById<View>(R.id.thdetails_lin_dark) as LinearLayout).visibility =
            View.GONE else (findViewById<View>(R.id.thdetails_lin_dark) as LinearLayout).visibility =
            View.VISIBLE
        findViewById<View>(R.id.thbasic_rel_resourse_builds).setOnClickListener {
            val intent = Intent(this@ThBasicDetailsActivity, ThDetailsListActivity::class.java)
            intent.putExtra("TITLE", "Resourse Buildings")
            intent.putExtra("CATEGORY", "resourse_building")
            intent.putExtra("TH", th)
            intent.putExtra("PATH", path)
            startActivity(intent)
        }
        findViewById<View>(R.id.thbasic_rel_army_builds).setOnClickListener {
            val intent = Intent(this@ThBasicDetailsActivity, ThDetailsListActivity::class.java)
            intent.putExtra("TITLE", "Army Buildings")
            intent.putExtra("CATEGORY", "army_buildings")
            intent.putExtra("TH", th)
            intent.putExtra("PATH", path)
            startActivity(intent)
        }
        findViewById<View>(R.id.thbasic_rel_def_builds).setOnClickListener {
            val intent = Intent(this@ThBasicDetailsActivity, ThDetailsListActivity::class.java)
            intent.putExtra("TITLE", "Defensive Buildings")
            intent.putExtra("CATEGORY", "defensive_building")
            intent.putExtra("TH", th)
            intent.putExtra("PATH", path)
            startActivity(intent)
        }
        findViewById<View>(R.id.thbasic_rel_traps_builds).setOnClickListener {
            val intent = Intent(this@ThBasicDetailsActivity, ThDetailsListActivity::class.java)
            intent.putExtra("TITLE", "Traps")
            intent.putExtra("CATEGORY", "trap")
            intent.putExtra("TH", th)
            intent.putExtra("PATH", path)
            startActivity(intent)
        }
    }
}