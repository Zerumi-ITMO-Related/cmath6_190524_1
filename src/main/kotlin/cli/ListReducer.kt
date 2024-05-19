package io.github.zerumi.cli

import kotlin.math.roundToInt

fun reduceList(values: List<Double>, startX: Double, endX: Double, h: Double): List<Double> {
    val n = ((endX - startX) / h).roundToInt()

    val elementsToSkip = values.size / n

    return values.filterIndexed { index, _ ->
        index % elementsToSkip == 0
    }
}
