package com.e.cocinfo.detailedlist

import com.e.cocinfo.parser.AssetParser
import org.json.JSONObject

object DetailedListParser {
    fun parseData(
        activity: DetailedListActivity,
        detailedListModels: MutableList<DetailedListModel>,
        path: String,
        category: String,
        id: String
    ): MutableList<DetailedListModel> {
        try {
            val json: String = AssetParser.loadJSONFromAssets(activity, "", path)
            if (null != json && json.trim { it <= ' ' }.length > 0) {
                val mainObject = JSONObject(json)
                val jsonArray = mainObject.optJSONArray(category)
                if (jsonArray != null && jsonArray.length() > 0) {
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.optJSONObject(i)
                        val detailedListModel = DetailedListModel()
                        if (category == "army" || category == "siege_machines") {
                            if (path.contains("builder")) {
                                if (path.contains("elixirtroops")) {
                                    detailedListModel.setBuild_cost(jsonObject.optString("research_cost"))
                                    detailedListModel.setBuild_time(jsonObject.optString("research_time"))
                                    detailedListModel.setTh_level(jsonObject.optString("lab"))
                                } else {
                                    detailedListModel.setCamp_count(jsonObject.optString("no_of_camps"))
                                    detailedListModel.setBuild_cost(jsonObject.optString("build_cost"))
                                    detailedListModel.setBuild_time(jsonObject.optString("build_time"))
                                }
                                if (id == "builder_barracks") {
                                    detailedListModel.setBuild_cost(jsonObject.optString("build_cost"))
                                    detailedListModel.setBuild_time(jsonObject.optString("build_time"))
                                    detailedListModel.setTh_level(jsonObject.optString("th"))
                                    detailedListModel.setTroop_unlocked(jsonObject.optString("troop_unlocked"))
                                }
                            } else {
                                if (id == "army_camp" || id == "barracks" || id == "dark_barracks" || id == "dark_spell_factory" || id == "laboratory" || id == "spell_factory" || id == "workshop") {
                                    detailedListModel.setBuild_cost(jsonObject.optString("build_cost"))
                                    detailedListModel.setBuild_time(jsonObject.optString("build_time"))
                                    detailedListModel.setTh_level(jsonObject.optString("th"))
                                } else {
                                    detailedListModel.setBuild_cost(jsonObject.optString("research_cost"))
                                    detailedListModel.setBuild_time(jsonObject.optString("research_time"))
                                    detailedListModel.setTh_level(jsonObject.optString("lab"))
                                }
                            }
                        } else if (category == "heros") {
                            detailedListModel.setBuild_cost(jsonObject.optString("training_cost"))
                            detailedListModel.setBuild_time(jsonObject.optString("upgrade_time"))
                            detailedListModel.setTh_level(jsonObject.optString("th"))
                        } else if (category == "pets") {
                            detailedListModel.setBuild_cost(jsonObject.optString("upgrade_cost"))
                            detailedListModel.setBuild_time(jsonObject.optString("upgrade_time"))
                        } else if (category == "spells") {
                            detailedListModel.setBuild_cost(jsonObject.optString("research_cost"))
                            detailedListModel.setBuild_time(jsonObject.optString("research_time"))
                            detailedListModel.setTh_level(jsonObject.optString("lab_level_required"))
                        } else if (category == "defence") {
                            detailedListModel.setBuild_cost(jsonObject.optString("build_cost"))
                            detailedListModel.setBuild_time(jsonObject.optString("build_time"))
                            detailedListModel.setTh_level(jsonObject.optString("th"))
                        } else if (category == "resource") {
                            detailedListModel.setBuild_cost(jsonObject.optString("build_cost"))
                            detailedListModel.setBuild_time(jsonObject.optString("build_time"))
                            detailedListModel.setTh_level(jsonObject.optString("th"))
                        } else if (category == "army_building") {
                            detailedListModel.setBuild_cost(jsonObject.optString("build_cost"))
                            detailedListModel.setBuild_time(jsonObject.optString("build_time"))
                            detailedListModel.setTh_level(jsonObject.optString("th"))
                            detailedListModel.setTroop_unlocked(jsonObject.optString("troop_unlocked"))
                        }
                        detailedListModel.setId(jsonObject.optString(""))
                        detailedListModel.setItem_level(jsonObject.optString("level"))
                        detailedListModel.setLevel(1)
                        detailedListModel.setBag(jsonObject.optString("bag"))
                        if (id == "walls") {
                            detailedListModel.setCostGold(jsonObject.optString("cost_gold"))
                            detailedListModel.setCumulativeCostGold(jsonObject.optString("cumulative_cost(gold)"))
                            detailedListModel.setCostElixir(jsonObject.optString("cost_elixir"))
                            detailedListModel.setCumulativeCostElixir(jsonObject.optString("cumulative_cost(elixir)"))
                            detailedListModel.setCostWallRing(jsonObject.optString("cost_wall(ring)"))
                        }
                        detailedListModels.add(detailedListModel)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return detailedListModels
    }
}