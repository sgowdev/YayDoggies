package com.cnb.android.myapplication.data

import com.google.gson.annotations.SerializedName

data class RandomImageResponse(
    @field:SerializedName("message")
    val imageUrl: String,

    @field:SerializedName("status")
    val status: String
)
