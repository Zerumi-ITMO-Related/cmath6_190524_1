package io.github.zerumi.correction

import io.github.zerumi.method.SimpleDifferentialEquationSolvingMethod
import kotlin.math.abs
import kotlin.math.pow

class RungeRule(private val method: SimpleDifferentialEquationSolvingMethod, accuracy: Int) :
    SimpleDifferentialEquationSolvingMethod {

    private val epsilon = 10.0.pow(-accuracy)

    override val methodError: Int
        get() = method.methodError

    override fun solve(x0: Double, xN: Double, h: Double, y0: Double): List<Double> {
        var result : List<Double>
        var step = h

        do {
            result = method.solve(x0, xN, step, y0)
            val halvedResult = method.solve(x0, xN, step / 2, y0)

            val yN = result.last()
            val yNHalved = halvedResult.last()

            val difference = abs(yN - yNHalved) / (2.0.pow(methodError) - 1)

            step /= 2
        } while (difference > epsilon)

        return result
    }
}
