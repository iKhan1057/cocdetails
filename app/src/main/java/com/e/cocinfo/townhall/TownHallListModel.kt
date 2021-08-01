package com.e.cocinfo.townhall.thdetailslistmodel

import java.util.*

class TownHallListModel {
    enum class STATE {
        CLOSED, OPENED
    }

    private var name = ""
    private var bag = ""
    private var id = ""
    private var category = ""
    private var level = 1
    private var levelbg = 0
    private var item_level = ""
    private var build_cost = ""
    private var build_time = ""
    private var th_level = ""
    private var costGold = ""
    private var cumulativeCostGold = ""
    private var costElixir = ""
    private var cumulativeCostElixir = ""
    private var costWallRing = ""
    private var max_def_build = ""
    private var max_def_traps = ""


    fun getBuild_cost(): String {
        return build_cost
    }

    fun setBuild_cost(build_cost: String) {
        this.build_cost = build_cost
    }

    fun getBuild_time(): String {
        return build_time
    }

    fun setBuild_time(build_time: String) {
        this.build_time = build_time
    }

    fun getItem_level(): String {
        return item_level
    }

    fun getTh_level(): String {
        return th_level
    }

    fun setTh_level(th_level: String) {
        this.th_level = th_level
    }

    fun setItem_level(item_level: String) {
        this.item_level = item_level
    }

    var openstate = STATE.CLOSED

    private var models = ArrayList<TownHallListModel>()

    fun TownHallListModel() {}

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getBag(): String {
        return bag
    }

    fun setBag(bag: String) {
        this.bag = bag
    }

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getLevel(): Int {
        return level
    }

    fun setLevel(level: Int) {
        this.level = level
    }

    fun getState(): STATE {
        return openstate
    }

    fun setState(state: STATE) {
        this.openstate = state
    }

    fun getModels(): ArrayList<TownHallListModel> {
        return models
    }

    fun setModels(models: ArrayList<TownHallListModel>) {
        this.models = models
    }

    fun getLevelbg(): Int {
        return levelbg
    }

    fun setLevelbg(levelbg: Int) {
        this.levelbg = levelbg
    }

    fun getCategory(): String {
        return category
    }

    fun setCategory(category: String) {
        this.category = category
    }

    fun getCostGold(): String {
        return costGold
    }

    fun setCostGold(costGold: String) {
        this.costGold = costGold
    }

    fun getCumulativeCostGold(): String {
        return cumulativeCostGold
    }

    fun setCumulativeCostGold(cumulativeCostGold: String) {
        this.cumulativeCostGold = cumulativeCostGold
    }

    fun getCostElixir(): String {
        return costElixir
    }

    fun setCostElixir(costElixir: String) {
        this.costElixir = costElixir
    }

    fun getCumulativeCostElixir(): String {
        return cumulativeCostElixir
    }

    fun setCumulativeCostElixir(cumulativeCostElixir: String) {
        this.cumulativeCostElixir = cumulativeCostElixir
    }

    fun getCostWallRing(): String {
        return costWallRing
    }

    fun setCostWallRing(costWallRing: String) {
        this.costWallRing = costWallRing
    }

    fun getMax_def_build(): String {
        return max_def_build
    }

    fun setMax_def_build(max_def_build: String) {
        this.max_def_build = max_def_build
    }

    fun getMax_def_traps(): String {
        return max_def_traps
    }

    fun setMax_def_traps(max_def_traps: String) {
        this.max_def_traps = max_def_traps
    }
}