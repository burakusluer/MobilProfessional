package com.burakusluer.kotlinmarvel.model

data class ModelMarvel(
    var name:String,
    var realName:String,
    var team:String,
    var firstAppearance:String,
    var createdBy:String,
    var publisher:String,
    var imageUrl:String,
    var bio:String
) {
    var uuId:Int=0
}