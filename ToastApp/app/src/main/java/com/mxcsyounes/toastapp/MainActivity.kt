package com.mxcsyounes.toastapp

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toast = Toast(this)
        toast.setGravity(Gravity.CENTER, 0, 0)
        val view = layoutInflater.inflate(R.layout.custom_toast, null, false)
        toast.view = view

        simpleToastButton.setOnClickListener {
            Toast.makeText(this, "This is a simple Toast", Toast.LENGTH_SHORT).show()
        }

        customToastButton.setOnClickListener {
            toast.show()
        }
    }
}
