import scala.collection.mutable.ArrayBuffer

object Network{

  var _layers: ArrayBuffer[Array[Double]] = ArrayBuffer.empty[Array[Double]]

  def append(layer :Array[Double]): Unit = {
    _layers += layer
  }

  def activate(weights: Array[Double], inputs: Array[Double]): Unit = {

    var activation = weights.last
    for(i <- 0 until weights.length-1)
      activation += weights(i) * inputs(i)
    activation
  }

  override def toString: String = {

    val builder     :StringBuilder        = new StringBuilder
    val layerarray  :Array[Array[Double]] = _layers.toArray

    builder
      .append("'Weights'")
      .append("\n")

    for(i <- layerarray.indices) {
      builder.append("[ ")
      for (j <- layerarray(i).indices)
        builder
          .append(layerarray(i)(j))
          .append(" ")
      builder
        .append("]\n")
    }
    builder.toString()
  }
}