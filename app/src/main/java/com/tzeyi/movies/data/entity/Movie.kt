package com.tzeyi.movies.data.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Movie : RealmObject {
    @PrimaryKey var imdbId: String = ""
    var title: String = ""
    var year: String = ""
    var type: String = ""
    var poster: String = ""

    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(title, title.filter { !it.isWhitespace() })
        return matchingCombinations.any { it.contains(query, ignoreCase = true) }
    }
}
