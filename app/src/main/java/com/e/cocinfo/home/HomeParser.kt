package com.e.cocinfo.home

import android.app.Activity
import com.e.cocinfo.R
import com.e.cocinfo.parser.AssetParser
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

object HomeParser {
    fun parseData(activity: Activity, homeModels: MutableList<HomeModel>): MutableList<HomeModel> {
        try {
            val json: String = AssetParser.loadJSONFromAssets(activity, "", "home")
            if (null != json && json.trim { it <= ' ' }.length > 0) {
                val mainObject = JSONObject(json)
                val arrayHome = mainObject.optJSONArray("home")
                if (arrayHome != null && arrayHome.length() > 0) {
                    for (i in 0 until arrayHome.length()) {
                        val jsonObject = arrayHome.optJSONObject(i)
                        val homeModel = HomeModel()
                        homeModel.setBag(jsonObject.optString("bag"))
                        homeModel.setPath(jsonObject.optString("id"))
                        homeModel.setLevel(1)
                        homeModel.setId(jsonObject.optString("id"))
                        homeModel.setLevelbg(activity.getColor(R.color.level1))
                        homeModel.setName(jsonObject.optString("name"))
                        homeModel.setCategory(jsonObject.optString("type"))
                        val jsonArrayCategory = jsonObject.optJSONArray("category")
                        if (jsonArrayCategory != null && jsonArrayCategory.length() > 0) {
                            homeModel.setModels(
                                parseCategory(
                                    homeModel.getId(),
                                    homeModel.getPath(),
                                    activity,
                                    jsonArrayCategory
                                )!!
                            )
                        } else homeModel.setChild(true)
                        homeModels.add(homeModel)
                    }
                }
            }
        } catch (e: Exception) {
        }
        return homeModels
    }

    private fun parseCategory(
        homeModelId: String?,
        id: String?,
        activity: Activity,
        jsonArrayCategory: JSONArray
    ): ArrayList<HomeModel>? {
        val homeModels = ArrayList<HomeModel>()
        for (j in 0 until jsonArrayCategory.length()) {
            val jsonObject = jsonArrayCategory.optJSONObject(j)
            val homeModel = HomeModel()
            homeModel.setBag(jsonObject.optString("bag"))
            homeModel.setPath(id + "/" + jsonObject.optString("id"))
            homeModel.setLevel(2)
            homeModel.setId(jsonObject.optString("id"))
            homeModel.setLevelbg(activity.getColor(R.color.level2))
            homeModel.setName(jsonObject.optString("name"))
            homeModel.setCategory(jsonObject.optString("type"))
            val jsonArraylist = jsonObject.optJSONArray("list")
            if (jsonArraylist != null && jsonArraylist.length() > 0) {
                homeModel.setModels(parseList(homeModel.getPath(), jsonArraylist, activity)!!)
            } else homeModel.setChild(true)
            homeModels.add(homeModel)
        }
        return homeModels
    }

    private fun parseList(
        id: String?,
        jsonArraylist: JSONArray,
        activity: Activity
    ): ArrayList<HomeModel>? {
        val homeModels = ArrayList<HomeModel>()
        for (j in 0 until jsonArraylist.length()) {
            val jsonObject = jsonArraylist.optJSONObject(j)
            val homeModel = HomeModel()
            homeModel.setBag(jsonObject.optString("bag"))
            homeModel.setPath(id + "/" + jsonObject.optString("id"))
            homeModel.setLevel(3)
            homeModel.setId(jsonObject.optString("id"))
            homeModel.setLevelbg(activity.getColor(R.color.level3))
            homeModel.setName(jsonObject.optString("name"))
            homeModel.setCategory(jsonObject.optString("type"))
            val jsonArraymembers = jsonObject.optJSONArray("members")
            if (jsonArraymembers != null && jsonArraymembers.length() > 0) {
                homeModel.setModels(parseMembers(homeModel.getPath(), jsonArraymembers, activity)!!)
            } else homeModel.setChild(true)
            homeModels.add(homeModel)
        }
        return homeModels
    }

    private fun parseMembers(
        path: String?,
        jsonArraymembers: JSONArray,
        activity: Activity
    ): ArrayList<HomeModel>? {
        val homeModels = ArrayList<HomeModel>()
        for (j in 0 until jsonArraymembers.length()) {
            val jsonObject = jsonArraymembers.optJSONObject(j)
            val homeModel = HomeModel()
            homeModel.setBag(jsonObject.optString("bag"))
            homeModel.setPath(path + "/" + jsonObject.optString("id"))
            homeModel.setLevel(4)
            homeModel.setId(jsonObject.optString("id"))
            homeModel.setLevelbg(activity.getColor(R.color.level4))
            homeModel.setName(jsonObject.optString("name"))
            homeModel.setCategory(jsonObject.optString("type"))
            val jsonArraydefensive_list = jsonObject.optJSONArray("types")
            if (jsonArraydefensive_list != null && jsonArraydefensive_list.length() > 0) {
                homeModel.setModels(
                    parseDefensiveList(
                        homeModel.getPath(),
                        jsonArraydefensive_list,
                        activity
                    )!!
                )
            } else homeModel.setChild(true)
            homeModels.add(homeModel)
        }
        return homeModels
    }

    private fun parseDefensiveList(
        id: String?,
        jsonArraydefensive_list: JSONArray,
        activity: Activity
    ): ArrayList<HomeModel>? {
        val homeModels = ArrayList<HomeModel>()
        for (j in 0 until jsonArraydefensive_list.length()) {
            val jsonObject = jsonArraydefensive_list.optJSONObject(j)
            val homeModel = HomeModel()
            homeModel.setBag(jsonObject.optString("bag"))
            homeModel.setPath(id + "/" + jsonObject.optString("id"))
            homeModel.setId(jsonObject.optString("id"))
            homeModel.setLevel(5)
            homeModel.setLevelbg(activity.getColor(R.color.level6))
            homeModel.setName(jsonObject.optString("name"))
            homeModel.setCategory(jsonObject.optString("type"))
            homeModel.setChild(true)
            homeModels.add(homeModel)
        }
        return homeModels
    }
}