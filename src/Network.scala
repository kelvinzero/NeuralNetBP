import scala.collection.mutable.ArrayBuffer

/**
  * Network object containing neuron layers.
  *
  * @author Joshua Cotes
  */
object Network{

  private var _layers: ArrayBuffer[Array[Neuron]] = ArrayBuffer.empty[Array[Neuron]]

  def append(layer :Array[Neuron]){_layers += layer}
  def getLayers: Array[Array[Neuron]] = {_layers.toArray}

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

