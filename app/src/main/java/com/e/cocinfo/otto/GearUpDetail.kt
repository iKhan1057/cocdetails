package com.e.cocinfo.otto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GearUpDetail {
    @SerializedName("tag")
    @Expose
    private var tag: String? = null

    @SerializedName("value")
    @Expose
    private var value: String? = null

    fun getTag(): String? {
        return tag
    }

    fun setTag(tag: String?) {
        this.tag = tag
    }

    fun getValue(): String? {
        return value
    }

    fun setValue(value: String?) {
        this.value = value
    }
}