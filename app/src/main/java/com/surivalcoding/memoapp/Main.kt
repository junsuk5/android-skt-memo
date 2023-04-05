package com.surivalcoding.memoapp

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

fun main() {
    csvReader().open("sample.csv") {
        val items = readAllAsSequence().toList()
            .map { Student(
                name = it[0],
                score = it[1].trim().toInt(),
            ) }

        println(items.size)
    }
}

data class Student(
    val name: String,
    val score: Int,
)