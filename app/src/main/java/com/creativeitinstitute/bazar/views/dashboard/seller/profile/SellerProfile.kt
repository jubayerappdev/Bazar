package com.creativeitinstitute.bazar.views.dashboard.seller.profile

data class SellerProfile(
    var name:String="",
    var email: String="",
    var password:String="",
    var userType:String="",
    var userID:String="",
    var userImage:String?=null,
    var shopName:String?=null
)

fun SellerProfile.toMap(): Map<String,Any?>{
    return mapOf(
        "name" to name,
        "email" to email,
        "password" to password,
        "userType" to userType,
        "userID" to userID,
        "UserImage" to userImage,
        "shopName" to shopName
    )

}
