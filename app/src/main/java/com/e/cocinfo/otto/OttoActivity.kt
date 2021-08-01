package com.e.cocinfo.otto

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.e.cocinfo.R

class OttoActivity : AppCompatActivity() {

    lateinit var inflater: LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otto)
        initviews()
    }

    private fun initviews() {
        val category = intent.getStringExtra("CAT")
        val path = intent.getStringExtra("PATH")
        val colorbg = intent.getStringExtra("BAG")
        val id = intent.getStringExtra("ID")

        (findViewById<View>(R.id.otto_txt_title) as TextView).text = "O.T.T.O HUT"

        val otto_lin_parent = findViewById<LinearLayout>(R.id.otto_lin_parent)
        val colors = intArrayOf(getColor(R.color.level3), Color.parseColor(colorbg))
        //create a new gradient color
        //create a new gradient color
        val gd = GradientDrawable(
            GradientDrawable.Orientation.BL_TR, colors
        )
        gd.cornerRadius = 0f
        otto_lin_parent.background = gd

        val data = OttoParser.parseData(this, path.toString())

        inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val otto_lin_details = findViewById<LinearLayout>(R.id.otto_lin_details)
        val details = data.getDetails()
        for (i in details.indices) {
            val rowView = inflater.inflate(R.layout.adapter_otto_details, null, false)
            val otto_details_name = rowView.findViewById<TextView>(R.id.otto_details_name)
            otto_details_name.text = details[i].getName()
            val otto_details_value = rowView.findViewById<TextView>(R.id.otto_details_value)
            otto_details_value.text = details[i].getValue()
            val img_space = rowView.findViewById<ImageView>(R.id.img_space)
            if (i == details.size - 1) img_space.visibility = View.GONE
            // Add the new row before the add field button.
            otto_lin_details.addView(rowView, i)
        }


        val lin_uprade_req = findViewById<LinearLayout>(R.id.lin_uprade_req)
        val list = data.getUpgradeRequirements()
        for (i in list.indices) {
            val rowView = inflater.inflate(R.layout.adapter_otto_upgrade_req, null, false)
            // In order to get the view we have to use the new view with text_layout in it
            val otto_txt_level = rowView.findViewById<TextView>(R.id.otto_txt_level)
            otto_txt_level.text = "LEVEL" + list[i].getLevel()
            val otto_txt_req_lvl = rowView.findViewById<TextView>(R.id.otto_txt_req_lvl)
            if (list[i].getReq().contains(":")) {
                val detailsspl: List<String> = list[i].getReq().split(":")
                val builder = SpannableStringBuilder()
                val txt1 = detailsspl[0] + ":"
                val txtSpannable = SpannableString(txt1)
                val boldSpan = StyleSpan(Typeface.BOLD)
                txtSpannable.setSpan(boldSpan, 0, txt1.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                builder.append(txtSpannable)
                val txt2 = detailsspl[1]
                builder.append(txt2)
                otto_txt_req_lvl.text = builder
            } else otto_txt_req_lvl.text = list[i].getReq()
            val img_space = rowView.findViewById<ImageView>(R.id.img_space)
            if (i == list.size - 1) img_space.visibility = View.GONE
            // Add the new row before the add field button.
            lin_uprade_req.addView(rowView, i)
        }

        val lin_gearup_req = findViewById<LinearLayout>(R.id.lin_gearup_req)
        val gearUps = data.getGearUp()
        for (i in gearUps.indices) {
            val rowView_gearups = inflater.inflate(R.layout.adapter_otto_gearup_req, null, false)
            val otto_gearup_txt_name =
                rowView_gearups.findViewById<TextView>(R.id.otto_gearup_txt_name)
            otto_gearup_txt_name.text = gearUps[i].getName()
            val lin_gearups_req_details =
                rowView_gearups.findViewById<LinearLayout>(R.id.lin_gearup_req_details)
            addDetails(lin_gearups_req_details, gearUps[i])
            val img_space_parent = rowView_gearups.findViewById<ImageView>(R.id.img_space_parent)
            if (i == gearUps.size - 1) img_space_parent.visibility = View.GONE

            // Add the new row before the add field button.
            lin_gearup_req.addView(rowView_gearups, i)
        }
    }

    private fun addDetails(lin_gearups_req_details: LinearLayout, gearUp: GearUp) {
        val gearUpDetails = gearUp.getGearUpDetails()
        for (i in gearUpDetails.indices) {
            val rowView_gearups_details =
                inflater.inflate(R.layout.adapter_otto_gearup_req_details, null, false)
            val otto_gearup_details_txt_tag =
                rowView_gearups_details.findViewById<TextView>(R.id.otto_gearup_details_txt_tag)
            val otto_gearup_details_txt_value =
                rowView_gearups_details.findViewById<TextView>(R.id.otto_gearup_details_txt_value)
            val otto_gearup_details_img_space =
                rowView_gearups_details.findViewById<ImageView>(R.id.otto_gearup_details_img_space)
            otto_gearup_details_txt_tag.text = gearUpDetails[i].getTag()
            otto_gearup_details_txt_value.text = gearUpDetails[i].getValue()
            if (i == gearUpDetails.size - 1) otto_gearup_details_img_space.visibility = View.GONE
            lin_gearups_req_details.addView(rowView_gearups_details, i)
        }
    }
}