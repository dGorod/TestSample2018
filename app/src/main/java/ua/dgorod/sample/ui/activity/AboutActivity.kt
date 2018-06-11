package ua.dgorod.sample.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.dgorod.sample.R

/**
 * Created by dgorodnytskyi on 6/11/18.
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}