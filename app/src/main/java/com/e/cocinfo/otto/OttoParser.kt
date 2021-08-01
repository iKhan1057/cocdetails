package com.e.cocinfo.otto

import com.e.cocinfo.parser.AssetParser
import org.json.JSONObject
import java.util.*

object OttoParser {

    fun parseData(activity: OttoActivity, path: String): OttoModel {
        val ottoModel = OttoModel()
        try {
            val json: String = AssetParser.loadJSONFromAssets(activity, "", path)
            if (json != null && json.length > 0) {
                val jsonObject = JSONObject(json)
                val jsonArrayDetails = jsonObject.optJSONArray("details")
                val detailsList: MutableList<Detail> = ArrayList()
                if (jsonArrayDetails != null && jsonArrayDetails.length() > 0) {
                    for (i in 0 until jsonArrayDetails.length()) {
                        val jsonObject1 = jsonArrayDetails.optJSONObject(i)
                        val details = Detail()
                        details.setName(jsonObject1.optString("name"))
                        details.setValue(jsonObject1.optString("value"))
                        detailsList.add(details)
                    }
                    ottoModel.setDetails(detailsList)
                }
                val jsonArray = jsonObject.optJSONArray("upgrade_requirements")
                if (jsonArray != null && jsonArray.length() > 0) {
                    val upgradeRequirements: MutableList<UpgradeRequirement> = ArrayList()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject1 = jsonArray.optJSONObject(i)
                        val upgradeRequirement = UpgradeRequirement()
                        upgradeRequirement.setLevel(jsonObject1.optString("level"))
                        upgradeRequirement.setReq(jsonObject1.optString("req"))
                        upgradeRequirements.add(upgradeRequirement)
                    }
                    ottoModel.setUpgradeRequirements(upgradeRequirements)
                }
                val jsonArraygearupreq = jsonObject.optJSONArray("gear_up")
                if (jsonArraygearupreq != null && jsonArraygearupreq.length() > 0) {
                    val gearUps: MutableList<GearUp> = ArrayList()
                    for (i in 0 until jsonArraygearupreq.length()) {
                        val jsonObject1 = jsonArraygearupreq.optJSONObject(i)
                        val gearUp = GearUp()
                        gearUp.setName(jsonObject1.optString("name"))
                        val jsonArray1details = jsonObject1.optJSONArray("gear_up_details")
                        if (jsonArray1details != null && jsonArray1details.length() > 0) {
                            val gearUpDetails: MutableList<GearUpDetail> = ArrayList()
                            for (j in 0 until jsonArray1details.length()) {
                                val jsonObject11 = jsonArray1details.optJSONObject(j)
                                val gearUpDetail = GearUpDetail()
                                gearUpDetail.setTag(jsonObject11.optString("tag"))
                                gearUpDetail.setValue(jsonObject11.optString("value"))
                                gearUpDetails.add(gearUpDetail)
                            }
                            gearUp.setGearUpDetails(gearUpDetails)
                        }
                        gearUps.add(gearUp)
                    }
                    ottoModel.setGearUp(gearUps)
                }
            }
            return ottoModel
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ottoModel
    }
}