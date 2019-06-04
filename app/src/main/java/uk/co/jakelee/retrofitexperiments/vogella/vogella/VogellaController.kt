package uk.co.jakelee.retrofitexperiments.vogella.vogella

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


class VogellaController : Callback<RSSFeed> {

    fun start() {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create()).build()
        val vogellaAPI = retrofit.create(VogellaAPI::class.java)
        val call = vogellaAPI.loadRSSFeed()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<RSSFeed>, response: Response<RSSFeed>) {
        if (response.isSuccessful) {
            val rss = response.body()
            println("Channel title: " + rss?.channelTitle!!)
            rss.articleList!!.forEach { article -> println("Title: " + article.title + " Link: " + article.link) }
        } else {
            System.out.println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<RSSFeed>, t: Throwable) {
        t.printStackTrace()
    }

    companion object {
        internal val BASE_URL = "http://vogella.com/"
    }
}