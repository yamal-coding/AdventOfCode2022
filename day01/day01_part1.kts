#!/usr/bin/env kotlin

import java.io.File
import java.util.Scanner

/*
 * Part 1 of day 1: https://adventofcode.com/2022/day/1
 */

val file = File("input.txt")
val scanner = Scanner(file.inputStream())

var maxCalories = 0;
var currentElfCalories = 0;

while (scanner.hasNext()) {
    val currentCalories = scanner.nextLine();

    if (currentCalories.isBlank()) {
        if (currentElfCalories > maxCalories) {
            maxCalories = currentElfCalories;
        }
        currentElfCalories = 0;
    } else {
        currentElfCalories += currentCalories.toInt();
    }
}

println("Max calories found: $maxCalories");
