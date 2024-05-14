package com.example.employeedirectory.model.response


import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("biography")
    val biography: String?,
    @SerializedName("email_address")
    val emailAddress: String?,
    @SerializedName("employee_type")
    val employeeType: String?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("photo_url_large")
    val photoUrlLarge: String?,
    @SerializedName("photo_url_small")
    val photoUrlSmall: String?,
    @SerializedName("team")
    val team: String?,
    @SerializedName("uuid")
    val uuid: String?
)