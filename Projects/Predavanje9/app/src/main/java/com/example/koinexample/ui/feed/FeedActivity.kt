package com.example.koinexample.ui.feed

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koinexample.R

fun startFeedActivity(from: Context) = from.startActivity(Intent(from, FeedActivity::class.java))

class FeedActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_feed)
  }
}
