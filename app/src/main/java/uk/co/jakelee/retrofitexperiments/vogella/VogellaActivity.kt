package uk.co.jakelee.retrofitexperiments.vogella

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uk.co.jakelee.retrofitexperiments.R
import uk.co.jakelee.retrofitexperiments.vogella.stackoverflow.*


class VogellaActivity : AppCompatActivity(), View.OnClickListener {

    override fun onStart() {
        super.onStart()

        //val controller = GerrittController()
        //controller.start()

        //val controller = VogellaController()
        //controller.start()
    }

    private val stackoverflowAPI: StackOverflowAPI? = null
    private var token: String? = null

    private var authenticateButton: Button? = null

    private var questionsSpinner: Spinner? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vogella)

        questionsSpinner = findViewById<View>(R.id.questions_spinner) as Spinner
        questionsSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val question = parent.adapter.getItem(position) as Question
                question.questionId?.let { stackoverflowAPI!!.getAnswersForQuestion(it).enqueue(answersCallback) }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        val questions = FakeDataProvider.questions
        val arrayAdapter = ArrayAdapter(this@VogellaActivity, android.R.layout.simple_spinner_dropdown_item, questions)
        questionsSpinner!!.adapter = arrayAdapter

        authenticateButton = findViewById<View>(R.id.authenticate_button) as Button

        recyclerView = findViewById<View>(R.id.list) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this@VogellaActivity)
        val answers = FakeDataProvider.answers
        val adapter = RecyclerViewAdapter(answers)
        recyclerView!!.setAdapter(adapter)

    }

    override fun onResume() {
        super.onResume()
        if (token != null) {
            authenticateButton!!.isEnabled = false
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            android.R.id.text1 -> if (token != null) {
                // TODO
            } else {
                Toast.makeText(this, "You need to authenticate first", Toast.LENGTH_LONG).show()
            }
            R.id.authenticate_button -> {
                // TODO
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            token = data!!.getStringExtra("token")
        }
    }

    var questionsCallback: Callback<ListWrapper<Question>> = object : Callback<ListWrapper<Question>> {
        override fun onResponse(call: Call<ListWrapper<Question>>, response: Response<ListWrapper<Question>>) {
            if (response.isSuccessful()) {
                val questions = response.body()
                val arrayAdapter = ArrayAdapter<Question>(
                    this@VogellaActivity,
                    android.R.layout.simple_spinner_dropdown_item,
                    questions!!.items!!
                )
                questionsSpinner!!.setAdapter(arrayAdapter)
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message())
            }
        }

        override fun onFailure(call: Call<ListWrapper<Question>>, t: Throwable) {
            t.printStackTrace()
        }
    }

    var answersCallback: Callback<ListWrapper<Answer>> = object : Callback<ListWrapper<Answer>> {
        override fun onResponse(call: Call<ListWrapper<Answer>>, response: Response<ListWrapper<Answer>>) {
            if (response.isSuccessful) {
                val data = arrayListOf<Answer>()
                data.addAll(response.body()!!.items!!)
                recyclerView!!.setAdapter(RecyclerViewAdapter(data))
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message())
            }
        }

        override fun onFailure(call: Call<ListWrapper<Answer>>, t: Throwable) {
            t.printStackTrace()
        }
    }

    var upvoteCallback: Callback<ResponseBody> = object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful()) {
                Toast.makeText(this@VogellaActivity, "Upvote successful", Toast.LENGTH_LONG).show()
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message())
                Toast.makeText(this@VogellaActivity, "You already upvoted this answer", Toast.LENGTH_LONG).show()
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }
    }
}
