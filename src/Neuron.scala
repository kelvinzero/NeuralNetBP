/**
  * Layer neuron containing input weights and transfer values.
  *
  * @author Joshua Cotes
  *
  * @param weights - The weights for this neuron
  */
class Neuron(weights : Array[Double]){

  var _output             :Double = 0.0d
  var _transferDerivative :Double = 0.0d
  var _deltaError         :Double = 0.0d
  var _weights            :Array[Double] = weights

  /**
    * Calculate the neuron output and transfer derivative
    * @param inputs - Layer above outputs
    * @return - Neuron output
    */
  def activate(inputs: Array[Double]): Double = {
    var activation = _weights.last

    for(i <- 0 until _weights.length-1)
      activation += _weights(i) * inputs(i)

    _output             = 1.0 / (1.0 + Math.exp(-activation))
    _transferDerivative = _output * (1 - _output)
    _output
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
