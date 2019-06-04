package uk.co.jakelee.retrofitexperiments.medium

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import uk.co.jakelee.retrofitexperiments.MainActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import uk.co.jakelee.retrofitexperiments.medium.model.RetroPhoto
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.jakelee.retrofitexperiments.R
import uk.co.jakelee.retrofitexperiments.medium.network.RetrofitClientInstance



class MediumActivity : AppCompatActivity() {
    private var adapter: CustomAdapter? = null
    private var recyclerView: RecyclerView? = null
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medium)

        progressDialog = ProgressDialog(this@MediumActivity)
        progressDialog.setMessage("Loading....")
        progressDialog.show()

        /*Create handle for the RetrofitInstance interface*/
        val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
        val call = service.allPhotos
        call.enqueue(object : Callback<List<RetroPhoto>> {
            override fun onResponse(call: Call<List<RetroPhoto>>, response: Response<List<RetroPhoto>>) {
                progressDialog.dismiss()
                generateDataList(response.body()!!)
            }

            override fun onFailure(call: Call<List<RetroPhoto>>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(this@MediumActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private fun generateDataList(photoList: List<RetroPhoto>) {
        recyclerView = findViewById(R.id.customRecyclerView)
        adapter = CustomAdapter(this, photoList)
        val layoutManager = LinearLayoutManager(this@MediumActivity)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
    }
}
