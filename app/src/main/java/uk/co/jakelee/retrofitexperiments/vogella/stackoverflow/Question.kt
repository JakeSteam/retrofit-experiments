package uk.co.jakelee.retrofitexperiments.vogella.stackoverflow

import com.google.gson.annotations.SerializedName

class Question {
    var title: String? = null
    var body: String? = null

    @SerializedName("question_id")
    var questionId: String? = null

    override fun toString(): String {
        return title ?: ""
    }
}