package com.e.cocinfo.otto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UpgradeRequirement {
    @SerializedName("level")
    @Expose
    private var level: String = ""

    @SerializedName("req")
    @Expose
    private var req: String = ""

    fun getLevel(): String {
        return level
    }

    fun setLevel(level: String) {
        this.level = level
    }

    fun getReq(): String {
        return req
    }

    fun setReq(req: String) {
        this.req = req
    }
}