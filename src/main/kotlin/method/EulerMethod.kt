package io.github.zerumi.method

import io.github.zerumi.equations.SimpleDifferentialEquation
import kotlin.math.roundToInt

class EulerMethod(private val equation: SimpleDifferentialEquation) : SimpleDifferentialEquationSolvingMethod {
    override fun solve(x0: Double, xN: Double, h: Double, y0: Double): List<Double> {
        val n = ((xN - x0) / h).roundToInt()
        val result = mutableListOf(y0)
        var prevY = y0

        for (i in 0..<n) {
            prevY += h * equation.calculate(x0 + i * h, prevY)
            result.add(prevY)
        }

        return result
    }
}
