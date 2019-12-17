package com.example.colorapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_more_detail.*

class MoreDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_detail)

        moreInfoButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW , Uri.parse("https://www.google.com")))
        }
    }
}
