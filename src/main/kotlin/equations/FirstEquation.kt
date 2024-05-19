package io.github.zerumi.equations

// y' = y + (1 + x) * y^2
object FirstEquation : SimpleDifferentialEquation {
    override fun calculate(x: Double, y: Double): Double = y + (1 + x) * y * y
}
