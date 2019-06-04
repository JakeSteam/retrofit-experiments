package uk.co.jakelee.retrofitexperiments.vogella.gerritt

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.jakelee.retrofitexperiments.vogella.gerritt.Change

interface GerritAPI {

    @GET("changes/")
    fun loadChanges(@Query("q") status: String): Call<List<Change>>
}