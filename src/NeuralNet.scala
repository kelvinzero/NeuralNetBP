import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class NeuralNet (n_inputs :Int, n_hidden :Int, n_outputs :Int){

  val _numInputs  :Int          = n_inputs
  val _numHiddens :Int          = n_hidden
  val _numOutputs :Int          = n_outputs
  val _network    :Network.type = Network

  initializeNetwork()

  private def initializeNetwork(): Unit ={

    var hiddenLayer :ArrayBuffer[Neuron] = new ArrayBuffer[Neuron]()
    var outputLayer :ArrayBuffer[Neuron] = new ArrayBuffer[Neuron]()

    for(hidden <- 0 until _numHiddens){
      var neuronWeights = Array.fill(_numInputs + 1)(Random.nextDouble())
      hiddenLayer += new Neuron(neuronWeights)
    }
    for(outputs <- 0 until _numOutputs) {
      var neuronWeights = Array.fill(_numHiddens + 1)(Random.nextDouble())
      outputLayer += new Neuron(neuronWeights)
    }
    _network.append(hiddenLayer.toArray)
    _network.append(outputLayer.toArray)
  }

  def forwardPropagate(inputs :Array[Double]): Array[Double] ={

    var layerInputs = inputs
    var layerOutputs :ArrayBuffer[Double] = new ArrayBuffer[Double]()

    _network._layers.foreach(
      layer => {layer.foreach(
        neuron => {layerOutputs += neuron.activate(layerInputs)})
        layerInputs = layerOutputs.toArray
        layerOutputs.clear()
      })
    layerInputs
  }
}

