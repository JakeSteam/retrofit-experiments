package uk.co.jakelee.retrofitexperiments.vogella.vogella

import retrofit2.Call
import retrofit2.http.GET

interface VogellaAPI {

    @GET("article.rss")
    fun loadRSSFeed(): Call<RSSFeed>
}