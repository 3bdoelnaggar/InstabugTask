package com.elnaggar.instabugtask.ui.main

import org.junit.Assert.*

import org.junit.Test

/**
 * this is a trivial tests just to demonstrate the basic knowledge
 * in the naming of the function we follow [Given - When - Then] style
 */
class ParserKtTest {


    @Test
    fun validInput_rightResult() {

        val testInput = "test test"
        val result = com.elnaggar.instabugtask.ui.main.parseInput(testInput)
        //then
        assertEquals(result, listOf(WordCount("test", "2")))
    }

    @Test
    fun emptyInput_emptyResult() {
        //arrange
        val testInput = ""
        //act
        val result = com.elnaggar.instabugtask.ui.main.parseInput(testInput)
        //assert
        assertEquals(result, emptyList<WordCount>())
    }
}