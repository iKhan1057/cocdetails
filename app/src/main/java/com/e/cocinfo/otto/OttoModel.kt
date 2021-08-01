package com.e.cocinfo.otto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OttoModel {
    @SerializedName("details")
    @Expose
    private var details: List<Detail> = ArrayList()

    @SerializedName("upgrade_requirements")
    @Expose
    private var upgradeRequirements: List<UpgradeRequirement> = ArrayList()

    @SerializedName("gear_up")
    @Expose
    private var gearUp: List<GearUp> = ArrayList()

    fun getDetails(): List<Detail> {
        return details
    }

    fun setDetails(details: List<Detail>) {
        this.details = details
    }

    fun getUpgradeRequirements(): List<UpgradeRequirement> {
        return upgradeRequirements
    }

    fun setUpgradeRequirements(upgradeRequirements: List<UpgradeRequirement>) {
        this.upgradeRequirements = upgradeRequirements
    }

    fun getGearUp(): List<GearUp> {
        return gearUp
    }

    fun setGearUp(gearUp: List<GearUp>) {
        this.gearUp = gearUp
    }
}