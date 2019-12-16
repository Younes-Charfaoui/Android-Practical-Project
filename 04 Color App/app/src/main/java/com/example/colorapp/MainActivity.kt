package com.example.colorapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val random  = Random(123)

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
}
