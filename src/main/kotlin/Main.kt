package io.github.zerumi

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.choice
import com.github.ajalt.clikt.parameters.types.double
import com.github.ajalt.clikt.parameters.types.int
import com.github.ajalt.clikt.parameters.types.restrictTo
import io.github.zerumi.cli.drawMatrix
import io.github.zerumi.cli.drawPlot
import io.github.zerumi.cli.reduceList
import io.github.zerumi.correction.RungeRule
import io.github.zerumi.equations.FirstEquation
import io.github.zerumi.equations.SecondEquation
import io.github.zerumi.equations.ThirdEquation
import io.github.zerumi.method.AdamsMethod
import io.github.zerumi.method.EulerMethod
import io.github.zerumi.method.RungeKuttaMethod

fun main(args: Array<String>) = CLI().main(args)

class CLI : CliktCommand() {
    private val equation by option()
        .choice(
            "1" to FirstEquation,
            "2" to SecondEquation,
            "3" to ThirdEquation,
        ).prompt(
            "Select equation (choose one of listed below):\n" +
                    "1) y' = y + (1 + x) * y^2\n" +
                    "2) y' = 0.6 - y / 5\n" +
                    "3) y' = 2xy / (x^2 - y^2)"
        )
        .help("Select pre-defined equation")

    private val x0 by option("-x0")
        .double()
        .prompt("Input interval start (x0)")
        .help("Interval start (x0)")

    private val xN by option("-xN")
        .double()
        .prompt("Input interval finish (xN)")
        .help("Interval finish (xN)")

    private val y0 by option("-y0")
        .double()
        .prompt("Input start condition (y0 = f(x0))")
        .help("Start condition (y0 = f(x0))")

    private val h by option("-h", "--step")
        .double()
        .prompt("Input step (h)")
        .help("Method step (h)")

    private val accuracy by option("-e", "--epsilon", "--accuracy")
        .int()
        .restrictTo(min = 0, max = 8)
        .prompt("Input how accurate result should be (e -> 10^(-e), from 0 to 8)")
        .help("How accurate result should be (e -> 10^(-e), from 0 to 8)")

    override fun run() {
        val xSequence = generateSequence(x0) { it + h }.takeWhile { it < xN + h }

        val eulerMethod = RungeRule(EulerMethod(equation), accuracy)
        val eulerSolution = reduceList(eulerMethod.solve(x0, xN, h, y0), x0, xN, h)
        // println("Euler method solution: $eulerSolution")

        val rungeKuttaMethod = RungeRule(RungeKuttaMethod(equation), accuracy)
        val rungeKuttaSolution = reduceList(rungeKuttaMethod.solve(x0, xN, h, y0), x0, xN, h)
        // println("RungeKutta method solution: $rungeKuttaSolution")

        val adamsMethod = RungeRule(AdamsMethod(equation, RungeKuttaMethod(equation)), accuracy)
        val adamsSolution = reduceList(adamsMethod.solve(x0, xN, h, y0), x0, xN, h)
        // println("Adams method solution: $adamsSolution")

        drawMatrix(
            arrayOf(
                xSequence.toList().toTypedArray(),
                eulerSolution.toTypedArray(),
                rungeKuttaSolution.toTypedArray(),
                adamsSolution.toTypedArray()
            )
        )
        drawPlot(rungeKuttaMethod.solve(x0, xN, h, y0), x0, xN)
    }
}
