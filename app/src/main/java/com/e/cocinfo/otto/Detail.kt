package com.e.cocinfo.otto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Detail {
    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("value")
    @Expose
    private var value: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getValue(): String? {
        return value
    }

    fun setValue(value: String?) {
        this.value = value
    }

}