package com.example.recyclerviewimpl.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.databinding.DataBindingUtil
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.RecyclerApp
import com.example.recyclerviewimpl.databinding.ActivityRecyclerBinding
import com.example.recyclerviewimpl.di.AppComponent

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerBinding
    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as RecyclerApp).appComponent
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler)
    }
}
