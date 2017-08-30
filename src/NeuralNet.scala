import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class NeuralNet (n_inputs :Int, n_hidden :Int, n_outputs :Int){

  val _numInputs  :Int          = n_inputs
  val _numHiddens :Int          = n_hidden
  val _numOutputs :Int          = n_outputs
  val _network    :Network.type = Network

  initializeNetwork()

  /**
    * Set random weight values for all hidden and output neurons in the network
    */
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

  /**
    * Calculate the neuron outputs from the dot product of weights * last neuron outputs.
    * @param inputs - Training inputs from the data set
    * @return - The outputs from neurons in the output layer
    */
  def forwardPropagate(inputs :Array[Double]): Array[Double] ={

    var layerInputs = inputs
    var layerOutputs :ArrayBuffer[Double] = new ArrayBuffer[Double]()

    _network._layers.foreach( // for each layer in the network
      layer => {layer.foreach( // foreach neuron in the layer
        neuron => {layerOutputs += neuron.activate(layerInputs)}) // calculate the neuron outputs (activate)
        layerInputs = layerOutputs.toArray // this layer outputs become next layer's input
        layerOutputs.clear()
      })
    layerInputs
  }

  /**
    * Calculate error delta in each neuron using expected outputs and previous layer error delta values.
    * @param expected - Array[Double] of expected outputs
    */
  def backPropagate(expected: Array[Double]): Unit = {

    //// for each neuron in every layer, calculate the error delta using the previous layer's error delta ////

    for(layerIdx <- _network._layers.indices.reverse){  // for each layer in the network
      for(neuronIdx <- _network._layers(layerIdx).indices){  // for each neuron in the layer

        val thisNeuron = _network._layers(layerIdx)(neuronIdx)  // this neuron in the layer
        var error :Double = 0.0d

        if(layerIdx == _network._layers.length-1)  // if this is the output layer
         thisNeuron._deltaError = (expected(neuronIdx) - thisNeuron._output) * thisNeuron._transferDerivative

        else{  // else if this is a hidden layer
          for(nextLevelNeuron <- _network._layers(layerIdx+1)) // calculate neuron error using next layer nodes error delta
            error += (nextLevelNeuron._weights(neuronIdx) * nextLevelNeuron._deltaError)
          thisNeuron._deltaError = error * thisNeuron._transferDerivative
        }
      }
    }
  }

  //TODO: Implement update-weights method

  //TODO: Finish train network
  def trainNetwork(trainingSet :List[Array[Double]], learningRate :Double, numEpochs :Int): Unit ={
    for(epoch <- 0 until numEpochs){
      var error = 0.0d
      for(record <- trainingSet){
        var outputs = forwardPropagate(record)

      }
    }

  }

}

