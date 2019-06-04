package uk.co.jakelee.retrofitexperiments.vogella.stackoverflow

import com.google.gson.annotations.SerializedName


class Answer {

    @SerializedName("answer_id")
    var answerId: Int = 0

    @SerializedName("is_accepted")
    var accepted: Boolean = false

    var score: Int = 0

    override fun toString(): String {
        return answerId.toString() + " - Score: " + score + " - Accepted: " + if (accepted) "Yes" else "No"
    }
}