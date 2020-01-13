package com.example.recyclerviewimpl.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.RecyclerApp
import com.example.recyclerviewimpl.di.AppComponent

class RecyclerActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as RecyclerApp).appComponent
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
