package com.mxcsyounes.pw2_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_text.*

class TextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        returnButton.setOnClickListener {
            val text = editText.text.toString()
            val intentBack = Intent()
            intentBack.putExtra("text" , text)
            setResult(Activity.RESULT_OK , intentBack)
            finish()
        }

    }
}
