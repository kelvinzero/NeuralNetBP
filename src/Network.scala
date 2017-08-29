import scala.Array.emptyDoubleArray
import scala.collection.mutable.ArrayBuffer

object Network{

  var _layers: ArrayBuffer[Array[Neuron]] = ArrayBuffer.empty[Array[Neuron]]

  def append(layer :Array[Neuron]){_layers += layer}

  override def toString: String = {

    val builder :StringBuilder = new StringBuilder

    for(layer <- _layers.indices){
      for(neuron <- _layers(layer).indices){
        builder
          .append("[")
          .append(_layers(layer)(neuron).toString)
          .append("]")
        if(neuron < _layers(layer).length-1)
          builder.append(", ")
      }
      builder.append("\n")
    }
    builder.toString()
  }
}

class Neuron(_weights : Array[Double]){

  var _output             :Double = 0.0d
  var _outputDerivative :Double = 0.0d

  def activate(inputs: Array[Double]): Double = {
    var activation = _weights.last
    for(i <- 0 until _weights.length-1)
      activation += _weights(i) * inputs(i)
    transfer(activation)
    _output
  }

  private def transfer(activation :Double): Unit = {
    _output           = 1.0 / (1.0 + Math.exp(-activation))
    _outputDerivative = _output * (1 - _output)
  }

  override def toString: String = {
    val builder :StringBuilder = new StringBuilder
    for(idx <- _weights.indices) {
      builder.append(_weights(idx))
      if(idx < _weights.length-1)
        builder.append(", ")
    }
    builder.toString()
  }
}