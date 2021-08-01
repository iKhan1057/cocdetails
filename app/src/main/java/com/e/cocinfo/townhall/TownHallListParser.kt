package com.e.cocinfo.townhall

import com.e.cocinfo.parser.AssetParser
import com.e.cocinfo.townhall.thdetailslistmodel.Resource
import com.e.cocinfo.townhall.thdetailslistmodel.ResourseBuilding
import com.e.cocinfo.townhall.thdetailslistmodel.THDetailsModel
import com.e.cocinfo.townhall.thdetailslistmodel.TownHallListModel
import org.json.JSONObject
import java.util.*

object TownHallListParser {
    //th level
    fun parseData(
        activity: TownHallActivity,
        townHallListModelList: MutableList<TownHallListModel>,
        path: String,
        category: String,
        id: String
    ): MutableList<TownHallListModel> {
        try {
            val json: String = AssetParser.loadJSONFromAssets(activity, "", "$path/levels")
            if (null != json && json.trim { it <= ' ' }.length > 0) {
                val mainObject = JSONObject(json)
                val jsonArray = mainObject.optJSONArray("thlevels")
                if (jsonArray != null && jsonArray.length() > 0) {
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.optJSONObject(i)
                        val townHallListModel = TownHallListModel()
                        townHallListModel.setBuild_cost(jsonObject.optString("build_cost"))
                        townHallListModel.setBuild_time(jsonObject.optString("build_time"))
                        townHallListModel.setMax_def_build(jsonObject.optString("max_def_build"))
                        townHallListModel.setMax_def_traps(jsonObject.optString("max_def_traps"))
                        townHallListModel.setId(jsonObject.optString(""))
                        townHallListModel.setItem_level(jsonObject.optString("level"))
                        townHallListModel.setLevel(1)
                        townHallListModel.setBag(jsonObject.optString("bag"))
                        townHallListModelList.add(townHallListModel)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return townHallListModelList
    }

    //storage capacity
    fun parseStorageData(
        thBasicDetailsActivity: ThBasicDetailsActivity,
        path: String,
        thStorages: MutableList<ThStorage>
    ): MutableList<ThStorage> {
        try {
            val json: String = AssetParser.loadJSONFromAssets(
                thBasicDetailsActivity, "",
                "$path/storage"
            )
            if (null != json && json.trim { it <= ' ' }.length > 0) {
                val mainObject = JSONObject(json)
                val jsonArray = mainObject.optJSONArray("thstorage")
                if (jsonArray != null && jsonArray.length() > 0) {
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.optJSONObject(i)
                        val thStorage = ThStorage()
                        thStorage.setLevel(jsonObject.optString("level"))
                        thStorage.setGold(jsonObject.optString("gold"))
                        thStorage.setElixir(jsonObject.optString("elixir"))
                        thStorage.setDark(jsonObject.optString("dark"))
                        thStorages.add(thStorage)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return thStorages
    }


    //resource building
    fun parseResData(
        activity: ThDetailsListActivity,
        thDetailsModels: THDetailsModel,
        path: String,
        category: String
    ): THDetailsModel {
        try {
            val json: String = AssetParser.loadJSONFromAssets(activity, "", "$path/$category")
            if (null != json && json.trim { it <= ' ' }.length > 0) {
                val mainObject = JSONObject(json)
                val jsonArray = mainObject.optJSONArray(category)
                val resourseBuilding: MutableList<ResourseBuilding> = ArrayList<ResourseBuilding>()
                if (jsonArray != null && jsonArray.length() > 0) {
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.optJSONObject(i)
                        val resourseBuilding1 = ResourseBuilding()
                        resourseBuilding1.setTh(jsonObject.optString("th"))
                        val jsonArrayres = jsonObject.optJSONArray("resources")
                        val resourceList: MutableList<Resource> = ArrayList<Resource>()
                        if (jsonArrayres != null && jsonArrayres.length() > 0) {
                            for (j in 0 until jsonArrayres.length()) {
                                val jsonObject1 = jsonArrayres.optJSONObject(j)
                                val resource = Resource()
                                resource.setName(jsonObject1.optString("name"))
                                resource.setMaxNo(jsonObject1.optString("max_no"))
                                resource.setMaxUpdate(jsonObject1.optString("max_update"))
                                resourceList.add(resource)
                            }
                        }
                        resourseBuilding1.setResources(resourceList)
                        resourseBuilding.add(resourseBuilding1)
                    }
                }
                thDetailsModels.setResourseBuilding(resourseBuilding)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return thDetailsModels
    }


}