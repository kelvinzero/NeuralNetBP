import scala.Array.emptyDoubleArray
import scala.collection.mutable.ArrayBuffer

object Network{

  var _layers: ArrayBuffer[Array[Neuron]] = ArrayBuffer.empty[Array[Neuron]]

  def append(layer :Array[Neuron]){_layers += layer}

  override def toString: String = {

    val builder :StringBuilder = new StringBuilder

    for(layer <- _layers.indices){
      builder
        .append("Layer :[")
        .append(layer+1)
        .append("]\n")
      for(neuron <- _layers(layer).indices){
        builder
            .append("Neuron: [")
            .append(neuron+1)
            .append("] ")
          .append(_layers(layer)(neuron).toString)
        if(neuron < _layers(layer).length-1)
          builder.append("\n")
      }
      builder.append("\n")
    }
    builder.toString()
  }
}

class Neuron(weights : Array[Double]){

  var _output             :Double = 0.0d
  var _transferDerivative :Double = 0.0d
  var _deltaError         :Double = 0.0d
  var _weights            :Array[Double] = weights


  def activate(inputs: Array[Double]): Double = {
    var activation = _weights.last
    for(i <- 0 until _weights.length-1)
      activation += _weights(i) * inputs(i)
    transfer(activation)
    _output
  }

  private def transfer(activation :Double): Unit = {
    _output           = 1.0 / (1.0 + Math.exp(-activation))
    _transferDerivative = _output * (1 - _output)
  }

  override def toString: String = {
    val builder :StringBuilder = new StringBuilder

    builder.append("Delta error: [")
    builder.append(_deltaError)
    builder.append("] Output: [")
    builder.append(_output)
    builder.append("]")
    builder.append(" 'Weights' {")

    for(idx <- _weights.indices) {
      builder.append(_weights(idx))
      if(idx < _weights.length-1)
        builder.append(", ")
    }
    builder.append("}")
    builder.toString()
  }
}