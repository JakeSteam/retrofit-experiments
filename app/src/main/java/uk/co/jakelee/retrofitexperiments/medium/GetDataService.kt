package uk.co.jakelee.retrofitexperiments.medium

import retrofit2.Call
import retrofit2.http.GET
import uk.co.jakelee.retrofitexperiments.medium.model.RetroPhoto


interface GetDataService {

    @get:GET("/photos")
    val allPhotos: Call<List<RetroPhoto>>
}