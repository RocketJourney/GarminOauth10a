package com.example.garminoauth10a

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpenWebView.setOnClickListener {
            startActivity(Intent(this@MainActivity, WebViewOAuth::class.java))
        }
    }
}
