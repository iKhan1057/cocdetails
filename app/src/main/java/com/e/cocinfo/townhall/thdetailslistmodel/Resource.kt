
package com.e.cocinfo.townhall.thdetailslistmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Resource {
    @SerializedName("name")
    @Expose
    private var name: String =""

    @SerializedName("max_no")
    @Expose
    private var maxNo: String = ""

    @SerializedName("max_update")
    @Expose
    private var maxUpdate: String = ""

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getMaxNo(): String{
        return maxNo
    }

    fun setMaxNo(maxNo: String) {
        this.maxNo = maxNo
    }

    fun getMaxUpdate(): String {
        return maxUpdate
    }

    fun setMaxUpdate(maxUpdate: String) {
        this.maxUpdate = maxUpdate
    }

}
