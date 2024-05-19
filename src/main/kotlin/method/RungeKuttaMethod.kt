package io.github.zerumi.method

import io.github.zerumi.equations.SimpleDifferentialEquation
import kotlin.math.roundToInt

class RungeKuttaMethod(private val equation: SimpleDifferentialEquation) : SimpleDifferentialEquationSolvingMethod {
    companion object {
        const val METHOD_ERROR = 4
    }

    override val methodError: Int
        get() = METHOD_ERROR

    override fun solve(x0: Double, xN: Double, h: Double, y0: Double): List<Double> {
        val n = ((xN - x0) / h).roundToInt()
        val result = mutableListOf(y0)
        var prevY = y0

        for (i in 0..<n) {
            val xi = x0 + i * h
            val k1 = h * equation.calculate(xi, prevY)
            val k2 = h * equation.calculate(xi + h / 2, prevY + k1 / 2)
            val k3 = h * equation.calculate(xi + h / 2, prevY + k2 / 2)
            val k4 = h * equation.calculate(xi + h, prevY + k3)
            prevY += 1.0 / 6.0 * (k1 + 2 * k2 + 2 * k3 + k4)
            result.add(prevY)
        }

        return result
    }
}
