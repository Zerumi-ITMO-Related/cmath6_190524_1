package io.github.zerumi.method

import io.github.zerumi.equations.SimpleDifferentialEquation
import kotlin.math.roundToInt

class AdamsMethod(
    private val equation: SimpleDifferentialEquation,
    private val helpingMethod: SimpleDifferentialEquationSolvingMethod
) :
    SimpleDifferentialEquationSolvingMethod {

    companion object {
        const val X_Y_VALUES = 4
    }

    override fun solve(x0: Double, xN: Double, h: Double, y0: Double): List<Double> {
        val n = ((xN - x0) / h).roundToInt()
        val result = helpingMethod.solve(x0, x0 + (X_Y_VALUES - 1) * h, h, y0).toMutableList()

        val x = ArrayDeque(generateSequence(x0) { it + h }.take(X_Y_VALUES).toList())
        val y = ArrayDeque(result)


        for (i in 3..<n) {
            val f = x.mapIndexed { index, value -> equation.calculate(value, y[index]) }

            val fIndex = X_Y_VALUES - 1

            val nextY =
                y[fIndex] + h / 24 * (55 * f[fIndex] - 59 * f[fIndex - 1] + 37 * f[fIndex - 2] - 9 * f[fIndex - 3])

            x.addLast(x.last() + h)
            y.addLast(nextY)

            x.removeFirst()
            y.removeFirst()

            result.add(nextY)
        }

        return result
    }
}