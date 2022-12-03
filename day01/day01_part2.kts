#!/usr/bin/env kotlin

import java.io.File
import java.util.Scanner

/*
 * Part 2 of day 1: https://adventofcode.com/2022/day/1
 */

val file = File("input.txt")
val scanner = Scanner(file.inputStream())

val topCalories = mutableListOf(0, 0, 0)

var currentElfCalories = 0

while (scanner.hasNext()) {
    val currentCalories = scanner.nextLine()

    if (currentCalories.isBlank()) {
        updateTopCalories()
    } else {
        currentElfCalories += currentCalories.toInt()
    }
}

updateTopCalories()

println("Max calories found: ${topCalories.sum()}")

fun updateTopCalories() {
    var i = 0
    var candidatePosition = -1
    while (i < topCalories.size && currentElfCalories > topCalories[i]) {
        var lowerCalorie = topCalories[i]
        topCalories[i] = currentElfCalories
        if (candidatePosition >= 0) {
            topCalories[candidatePosition] = lowerCalorie
        }
        candidatePosition++
        i++
    }

    currentElfCalories = 0
}