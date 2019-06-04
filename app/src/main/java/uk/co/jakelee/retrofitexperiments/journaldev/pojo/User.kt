package uk.co.jakelee.retrofitexperiments.journaldev.pojo

import com.google.gson.annotations.SerializedName


class User(
    @field:SerializedName("name")
    var name: String, @field:SerializedName("job")
    var job: String
) {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("createdAt")
    var createdAt: String? = null


}