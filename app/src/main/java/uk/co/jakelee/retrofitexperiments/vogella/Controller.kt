package uk.co.jakelee.retrofitexperiments.vogella

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Controller : Callback<List<Change>> {

    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val gerritAPI = retrofit.create(GerritAPI::class.java)

        val call = gerritAPI.loadChanges("status:open")
        call.enqueue(this)

    }

    override fun onResponse(call: Call<List<Change>>, response: Response<List<Change>>) {
        if (response.isSuccessful) {
            val changesList = response.body()
            changesList.forEach { change -> println(change.subject) }
        } else {
            System.out.println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<List<Change>>, t: Throwable) {
        t.printStackTrace()
    }

    companion object {
        internal val BASE_URL = "https://git.eclipse.org/r/"
    }
}