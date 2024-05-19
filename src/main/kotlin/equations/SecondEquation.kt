package io.github.zerumi.equations

// y' = 0.6 - y / 5
object SecondEquation : SimpleDifferentialEquation {
    override fun calculate(x: Double, y: Double): Double = 0.6 - y / 5
}
