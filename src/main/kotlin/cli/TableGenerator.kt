package io.github.zerumi.cli

import com.indvd00m.ascii.render.Render
import com.indvd00m.ascii.render.api.IRender
import com.indvd00m.ascii.render.elements.Label
import com.indvd00m.ascii.render.elements.Table

fun drawMatrix(matrix: Array<Array<Double>>) {
    val render: IRender = Render()
    val builder = render.newBuilder()
    builder.width(9 * matrix[0].size).height(3 * matrix.size)
    val table = Table(matrix[0].size, matrix.size)
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            table.setElement(j + 1, i + 1, Label(matrix[i][j].toString()))
        }
    }
    builder.element(table)
    val canvas = render.render(builder.build())
    val s = canvas.text
    println(s)
}