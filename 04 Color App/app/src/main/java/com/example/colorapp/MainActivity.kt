package com.example.colorapp

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val random = Random(123)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewLeftOne.setBackgroundColor(
            Color.argb(
                0xFF,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        )
        viewLeftTwo.setBackgroundColor(
            Color.argb(
                0xFF,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        )
        viewLeftThree.setBackgroundColor(
            Color.argb(
                0xFF,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        )
        viewRightOne.setBackgroundColor(
            Color.argb(
                0xFF,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        )
        viewRightTwo.setBackgroundColor(
            Color.argb(
                0xFF,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        )

        colorSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                viewLeftOne.setBackgroundColor(
                    Color.argb(
                        0xFF,
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)
                    )
                )
                viewLeftTwo.setBackgroundColor(
                    Color.argb(
                        0xFF,
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)
                    )
                )
                viewLeftThree.setBackgroundColor(
                    Color.argb(
                        0xFF,
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)
                    )
                )
                viewRightOne.setBackgroundColor(
                    Color.argb(
                        0xFF,
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)
                    )
                )
                viewRightTwo.setBackgroundColor(
                    Color.argb(
                        0xFF,
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)
                    )
                )
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.moreId -> {
                AlertDialog.Builder(this)
                    .setTitle("More Information")
                    .setPositiveButton(
                        "See more detail"
                    ) { _, _ ->
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com")
                            )
                        )
                    }
                    .setNegativeButton("Not now", null)
                    .setMessage("Hello there, wanna see more detail?")
                    .setCancelable(true)
                    .show()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
