import scala.collection.mutable.ArrayBuffer

object Network{

  var _layers: ArrayBuffer[Array[Double]] = ArrayBuffer.empty[Array[Double]]

  def append(layer :Array[Double]): Unit = {
    _layers += layer
  }

  override def toString: String = {

    val builder     :StringBuilder        = new StringBuilder
    val layerarray  :Array[Array[Double]] = _layers.toArray

    builder.append("'Weights'").append("\n")

    for(i <- layerarray.indices) {
      builder.append("[ ")
      for (j <- layerarray(i).indices)
        builder.append(layerarray(i)(j) + " ")
      builder.append("]\n")
    }

    builder.toString()
  }
}


