package com.mxcsyounes.pw1

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    lateinit var mapCounter: MutableMap<String, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            Log.d("HiHI", "rah dkhal")
            mapCounter = mutableMapOf(
                "onCreate()" to savedInstanceState.getInt("onCreate()"),
                "onRestart()" to savedInstanceState.getInt("onRestart()"),
                "onStart()" to savedInstanceState.getInt("onStart()"),
                "onResume()" to savedInstanceState.getInt("onResume()"),
                "onStop()" to savedInstanceState.getInt("onStop()"),
                "onDestroy()" to savedInstanceState.getInt("onDestroy()"),
                "onPause()" to savedInstanceState.getInt("onPause()")
            )
        } else {
            mapCounter = mutableMapOf(
                "onCreate()" to 0,
                "onCreate()" to 0,
                "onRestart()" to 0,
                "onStart()" to 0,
                "onResume()" to 0,
                "onStop()" to 0,
                "onDestroy()" to 0,
                "onPause()" to 0
            )
        }
        mapCounter["onCreate()"] = mapCounter["onCreate()"]!!.plus(1)

        Log.d("MyApp Second", "onCreate() The counter is : ${mapCounter["onCreate()"]}")

        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }

        onCreateText.text = "onCreate() The counter is : ${mapCounter["onCreate()"]}"
        onPauseText.text = "onPause() The counter is : ${mapCounter["onPause()"]}"
    }

    override fun onRestart() {
        super.onRestart()
        mapCounter["onRestart()"] = mapCounter["onRestart()"]!!.plus(1)
        Log.d("MyApp Second", "onRestart() The counter is : ${mapCounter["onRestart()"]}")

    }

    override fun onStart() {
        super.onStart()
        mapCounter["onStart()"] = mapCounter["onStart()"]!!.plus(1)
        Log.d("MyApp Second", "onStart() The counter is : ${mapCounter["onStart()"]}")
        onStartText.text = "onStart() The counter is : ${mapCounter["onStart()"]}"
    }

    override fun onResume() {
        super.onResume()
        mapCounter["onResume()"] = mapCounter["onResume()"]!!.plus(1)
        Log.d("MyApp Second", "onResume() The counter is : ${mapCounter["onResume()"]}")
        onResumeText.text = "onResume() The counter is : ${mapCounter["onResume()"]}"
    }

    override fun onStop() {
        super.onStop()
        mapCounter["onStop()"] = mapCounter["onStop()"]!!.plus(1)
        Log.d("MyApp Second", "onStop() The counter is : ${mapCounter["onStop()"]}")
    }

    override fun onDestroy() {
        super.onDestroy()
        mapCounter["onDestroy()"] = mapCounter["onDestroy()"]!!.plus(1)
        Log.d("MyApp Second", "onDestroy() The counter is : ${mapCounter["onDestroy()"]}")
    }

    override fun onPause() {
        super.onPause()
        mapCounter["onPause()"] = mapCounter["onPause()"]!!.plus(1)
        Log.d("MyApp Second", "onPause() The counter is : ${mapCounter["onPause()"]}")
        onPauseText.text = "onPause() The counter is : ${mapCounter["onPause()"]}"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapCounter.forEach { (t, u) ->
            outState.putInt(t, u)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mapCounter = mutableMapOf(
            "onCreate()" to savedInstanceState.getInt("onCreate()"),
            "onRestart()" to savedInstanceState.getInt("onRestart()"),
            "onStart()" to savedInstanceState.getInt("onStart()"),
            "onResume()" to savedInstanceState.getInt("onResume()"),
            "onStop()" to savedInstanceState.getInt("onStop()"),
            "onDestroy()" to savedInstanceState.getInt("onDestroy()"),
            "onPause()" to savedInstanceState.getInt("onPause()")
        )
    }


}
