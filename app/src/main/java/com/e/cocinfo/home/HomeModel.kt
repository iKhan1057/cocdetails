package com.e.cocinfo.home

import java.util.*

class HomeModel {
    enum class STATE {
        CLOSED, OPENED
    }

    private var isChild = false
    private var name = ""
    private var bag = ""
    private var id = ""
    private var path = ""
    private var category = ""
    private var level = 1
    private var levelbg = 0

    var openstate = STATE.CLOSED

    private var models = ArrayList<HomeModel>()

    fun HomeModel() {}

    fun getPath(): String {
        return path
    }

    fun setPath(path: String) {
        this.path = path
    }

    fun isChild(): Boolean {
        return isChild
    }

    fun setChild(child: Boolean) {
        isChild = child
    }

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

    fun getModels(): ArrayList<HomeModel> {
        return models
    }

    fun setModels(models: ArrayList<HomeModel>) {
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
}