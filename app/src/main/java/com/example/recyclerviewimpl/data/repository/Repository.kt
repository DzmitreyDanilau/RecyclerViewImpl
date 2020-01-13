package com.example.recyclerviewimpl.data.repository

import com.example.recyclerviewimpl.data.models.Item
import io.reactivex.Single

interface Repository {
    fun fetchData(): Single<List<Item>>
}