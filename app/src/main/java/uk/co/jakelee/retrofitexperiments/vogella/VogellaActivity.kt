package uk.co.jakelee.retrofitexperiments.vogella

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import uk.co.jakelee.retrofitexperiments.R

class VogellaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vogella)
    }

    override fun onStart() {
        super.onStart()
        val controller = Controller()
        controller.start()
    }
}
