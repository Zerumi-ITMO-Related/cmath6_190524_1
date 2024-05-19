package io.github.zerumi.method

interface SimpleDifferentialEquationSolvingMethod {
    fun solve(x0: Double, xN: Double, h: Double, y0: Double): List<Double>
}
