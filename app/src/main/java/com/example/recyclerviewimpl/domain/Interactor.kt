package com.example.recyclerviewimpl.domain

import com.example.recyclerviewimpl.data.models.Item
import io.reactivex.Single

interface Interactor {
    fun fetchData(): Single<List<Item>>
}