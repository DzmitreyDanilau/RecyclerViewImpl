package com.example.recyclerviewimpl.data.repository

import com.example.recyclerviewimpl.data.models.Item
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val itemList: MutableList<Item> = mutableListOf(
        Item(
            0, "https://upload.wikimedia.org/wikipedia/commons/5/55/Apple_orchard_in_Tasmania.jpg",
            "Item #1", 122541.55
        ),
        Item(
            1, "https://upload.wikimedia.org/wikipedia/commons/2/29/Beetroot_jm26647.jpg",
            "Item #2", 0.00
        ),
        Item(
            1,
            "https://upload.wikimedia.org/wikipedia/commons/5/51/A_scene_of_Coriander_leaves.JPG",
            "Item #3",
            -999898.908888
        ),
        Item(
            0,
            "https://upload.wikimedia.org/wikipedia/commons/e/e4/Branch_and_fruit_of_the_Maluma_avocado_cultivar.jpg",
            "Item #4",
            -555.434324234
        ),
        Item(
            1, "https://upload.wikimedia.org/wikipedia/commons/0/03/Grape_Plant_and_grapes9.jpg",
            "Item #5", 777.87778787878
        ),
        Item(
            1,
            "https://upload.wikimedia.org/wikipedia/commons/2/22/Apfelsinenbaum--Orange_tree.jpg",
            "Item #6",
            12.3455976678
        )
    )

    override fun fetchData(): Single<List<Item>> {
        itemList.shuffle()
        return Single.just(itemList)
    }
}