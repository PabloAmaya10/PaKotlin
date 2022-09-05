package com.pa.pakotlin.data.model

import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("id") var id: Int,
    @SerializedName("weight") var weight: Int,

)
