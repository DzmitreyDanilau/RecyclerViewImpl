package com.example.recyclerviewimpl.domain

import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.data.repository.Repository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class InteractorImpl @Inject constructor(private val repository: Repository) : Interactor {

    override fun fetchData(): Single<List<Item>> {
        return repository.fetchData()
    }
}