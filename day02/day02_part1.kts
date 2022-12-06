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
val selfOptionByLetter = mapOf("X" to Option.ROCK, "Y" to Option.PAPER, "Z" to Option.SCISSORS)
val selfScoreByOption = mapOf(Option.ROCK to 1, Option.PAPER to 2, Option.SCISSORS to 3)
val scoreByResult = mapOf(Result.VICTORY to 6, Result.DRAW to 3, Result.LOOSE to 0)

fun playRound(oponentOption: Option, selfOption: Option): Result =
    when (oponentOption) {
        Option.ROCK ->
            when (selfOption) {
                Option.ROCK -> Result.DRAW
                Option.PAPER -> Result.VICTORY
                Option.SCISSORS -> Result.LOOSE
            }
        Option.PAPER ->
            when (selfOption) {
                Option.ROCK -> Result.LOOSE
                Option.PAPER -> Result.DRAW
                Option.SCISSORS -> Result.VICTORY
            }
        Option.SCISSORS ->
            when (selfOption) {
                Option.ROCK -> Result.VICTORY
                Option.PAPER -> Result.LOOSE
                Option.SCISSORS -> Result.DRAW
            }
    }

val file = File("input.txt")
val scanner = Scanner(file.inputStream())

var score = 0

while (scanner.hasNext()) {
    val round = scanner.nextLine().split(" ")
    val oponentOption = oponentOptionByLetter[round[0]]!!
    val selfOption = selfOptionByLetter[round[1]]!!

    val result = playRound(oponentOption, selfOption)

    score += scoreByResult[result]!! + selfScoreByOption[selfOption]!!
}

println("Total score is: $score")
