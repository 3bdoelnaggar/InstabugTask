package com.elnaggar.instabugtask.ui.main

import java.util.regex.Matcher
import java.util.regex.Pattern

fun parseInput(input: String): List<WordCount> {
    val inputWithoutSpace = input.split(" ")
    val groupBy = inputWithoutSpace.groupBy { it }
    val result = groupBy.values.mapNotNull {
        val pattern: Pattern = Pattern.compile("[^a-zA]")
        val matcher: Matcher = pattern.matcher(it[0])
        if (matcher.find() || it[0].isBlank()) {
            null
        } else {
            WordCount(it[0], it.size.toString())
        }
    }
    return result
}