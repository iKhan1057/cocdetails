
package com.e.cocinfo.townhall.thdetailslistmodel;

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResourseBuilding {
    @SerializedName("th")
    @Expose
    private var th: String = ""

    @SerializedName("resources")
    @Expose
    private var resources: List<Resource> = ArrayList()

    fun getTh(): String {
        return th
    }

    fun setTh(th: String) {
        this.th = th
    }

    fun getResources(): List<Resource> {
        return resources
    }

    fun setResources(resources: List<Resource>) {
        this.resources = resources
    }


}
