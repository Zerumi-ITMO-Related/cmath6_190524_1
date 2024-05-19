package io.github.zerumi.equations

// y' = 2xy / (x^2 - y^2)
object ThirdEquation : SimpleDifferentialEquation {
    override fun calculate(x: Double, y: Double): Double = 2 * x * y / (x * x + y * y)
}
