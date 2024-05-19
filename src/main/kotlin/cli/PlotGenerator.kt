package io.github.zerumi.cli

import com.indvd00m.ascii.render.Region
import com.indvd00m.ascii.render.Render
import com.indvd00m.ascii.render.api.IRender
import com.indvd00m.ascii.render.elements.Rectangle
import com.indvd00m.ascii.render.elements.plot.Axis
import com.indvd00m.ascii.render.elements.plot.AxisLabels
import com.indvd00m.ascii.render.elements.plot.Plot
import com.indvd00m.ascii.render.elements.plot.api.IPlotPoint
import com.indvd00m.ascii.render.elements.plot.misc.PlotPoint

fun drawPlot(
    sourcePoints : List<Double>,
    startX : Double,
    endX: Double,
) {
    val points: MutableList<IPlotPoint> = ArrayList()
    val xSize = endX - startX
    val step = xSize / (sourcePoints.size - 1)
    for (i in sourcePoints.indices) {
        val currentX = startX + i * step
        val decimalPoint = sourcePoints[i]
        val plotPoint: IPlotPoint = PlotPoint(currentX, decimalPoint)
        points.add(plotPoint)
    }
    val render: IRender = Render()
    val builder = render.newBuilder()
    builder.width(80).height(20)
    builder.element(Rectangle(0, 0, 80, 20))
    builder.layer(Region(1, 1, 78, 18))
    builder.element(Axis(points, Region(0, 0, 78, 18)))
    builder.element(AxisLabels(points, Region(0, 0, 78, 18)))
    builder.element(Plot(points, Region(0, 0, 78, 18)))
    val canvas = render.render(builder.build())
    val s = canvas.text
    println(s)
}