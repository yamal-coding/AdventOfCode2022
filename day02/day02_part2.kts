#!/usr/bin/env kotlin

import java.io.File
import java.util.Scanner

/*
 * Part 1 of day 2: https://adventofcode.com/2022/day/2
 */

enum class Option {
    ROCK,
    PAPER,
    SCISSORS,
}

enum class Result {
    VICTORY,
    LOOSE,
    DRAW,
}

val oponentOptionByLetter = mapOf("A" to Option.ROCK, "B" to Option.PAPER, "C" to Option.SCISSORS)
val expectedResultByLetter = mapOf("X" to Result.LOOSE, "Y" to Result.DRAW, "Z" to Result.VICTORY)
val selfScoreByOption = mapOf(Option.ROCK to 1, Option.PAPER to 2, Option.SCISSORS to 3)
val scoreByResult = mapOf(Result.VICTORY to 6, Result.DRAW to 3, Result.LOOSE to 0)

fun calculateSelfOption(oponentOption: Option, result: Result): Option =
    when (oponentOption) {
        Option.ROCK -> {
            when (result) {
                Result.VICTORY -> Option.PAPER
                Result.LOOSE -> Option.SCISSORS
                Result.DRAW -> Option.ROCK
            }
        }
        Option.PAPER -> {
            when (result) {
                Result.VICTORY -> Option.SCISSORS
                Result.LOOSE -> Option.ROCK
                Result.DRAW -> Option.PAPER
            }
        }
        Option.SCISSORS -> {
            when (result) {
                Result.VICTORY -> Option.ROCK
                Result.LOOSE -> Option.PAPER
                Result.DRAW -> Option.SCISSORS
            }
        }
    }

val file = File("input.txt")
val scanner = Scanner(file.inputStream())

var score = 0

while (scanner.hasNext()) {
    val round = scanner.nextLine().split(" ")
    val oponentOption = oponentOptionByLetter[round[0]]!!
    val expectedResult = expectedResultByLetter[round[1]]!!

    val selfOption = calculateSelfOption(oponentOption, expectedResult)

    score += scoreByResult[expectedResult]!! + selfScoreByOption[selfOption]!!
}

println("Total score is: $score")