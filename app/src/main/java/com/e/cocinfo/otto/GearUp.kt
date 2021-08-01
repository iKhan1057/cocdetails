package com.e.cocinfo.otto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GearUp {

    @SerializedName("name")
    @Expose
    private var name: String = ""

    @SerializedName("gear_up_details")
    @Expose
    private var gearUpDetails: List<GearUpDetail> = ArrayList()

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getGearUpDetails(): List<GearUpDetail> {
        return gearUpDetails
    }

    fun setGearUpDetails(gearUpDetails: List<GearUpDetail>) {
        this.gearUpDetails = gearUpDetails
    }
}