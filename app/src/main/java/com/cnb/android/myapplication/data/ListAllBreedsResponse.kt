package com.cnb.android.myapplication.data

import com.google.gson.annotations.SerializedName

data class ListAllBreedsResponse(
    @field:SerializedName("message")
    val message: Map<String, List<String>>,

    @field:SerializedName("status")
    val status: String
)
