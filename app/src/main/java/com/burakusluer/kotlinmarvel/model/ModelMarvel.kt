package com.burakusluer.kotlinmarvel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//https://www.simplifiedcoding.net/demos/marvel/
@Entity(tableName = "heroes")
data class ModelMarvel(
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "realname")
    @SerializedName("realname")
    var realName: String,
    @ColumnInfo(name = "team")
    @SerializedName("team")
    var team: String,
    @ColumnInfo(name = "firstappearance")
    @SerializedName("firstappearance")
    var firstAppearance: String,
    @ColumnInfo(name = "createdby")
    @SerializedName("createdby")
    var createdBy: String,
    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    var publisher: String,
    @ColumnInfo(name = "url")
    @SerializedName("imageurl")
    var url: String,
    @ColumnInfo(name = "bio")
    @SerializedName("bio")
    var bio: String
) {
    @ColumnInfo(name = "pk")
    @PrimaryKey(autoGenerate = true)
    var uuId: Int = 0
}