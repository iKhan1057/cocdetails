package com.e.cocinfo.townhall.thdetailslistmodel;

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class THDetailsModel {
    @SerializedName("resourse_building")
    @Expose
    private var resourseBuilding: List<ResourseBuilding> = ArrayList()

    fun getResourseBuilding(): List<ResourseBuilding> {
        return resourseBuilding
    }

    fun setResourseBuilding(resourseBuilding: List<ResourseBuilding>) {
        this.resourseBuilding = resourseBuilding
    }

}
