import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * A forward/back propagation neural network
  *
  * @author Joshua Cotes
  *
  * @param n_inputs - Number of inputs from set
  * @param n_hidden - Number of hidden neurons
  * @param n_outputs - Number of outputs
  */
class NeuralNet (n_inputs :Int, n_hidden :Int, n_outputs :Int){

  val _numInputs  :Int          = n_inputs
  val _numHiddens :Int          = n_hidden
  val _numOutputs :Int          = n_outputs
  val _network    :Network.type = Network

  initializeNetwork()

  /**
    * Set random weight values for hidden and output neurons in the network
    */
  private def initializeNetwork(): Unit ={

    var hiddenLayer :ArrayBuffer[Neuron] = new ArrayBuffer[Neuron]()
    var outputLayer :ArrayBuffer[Neuron] = new ArrayBuffer[Neuron]()

    for(hidden <- 0 until _numHiddens)
      hiddenLayer += new Neuron(Array.fill(_numInputs + 1)(Random.nextDouble()))
    for(outputs <- 0 until _numOutputs)
      outputLayer += new Neuron(Array.fill(_numHiddens + 1)(Random.nextDouble()))

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

    _network.getLayers.foreach( // for each layer in the network
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

    for(layerIdx <- _network.getLayers.indices.reverse){  // for each layer in the network
      for(neuronIdx <- _network.getLayers(layerIdx).indices){  // for each neuron in the layer

        val thisNeuron = _network.getLayers(layerIdx)(neuronIdx)  // this neuron in the layer
        var error :Double = 0.0d

        if(layerIdx == _network.getLayers.length-1)  // if this is the output layer
         thisNeuron._deltaError = (expected(neuronIdx) - thisNeuron._output) * thisNeuron._transferDerivative

        else{  // else if this is a hidden layer

          for(nextLevelNeuron <- _network.getLayers(layerIdx+1)) // calculate neuron error using next layer nodes error delta
            error += (nextLevelNeuron._weights(neuronIdx) * nextLevelNeuron._deltaError)
          thisNeuron._deltaError = error * thisNeuron._transferDerivative
        }
      }
    }
  }

  /**
    * Update weights in the layers using previously calculated delta errors.
    * @param record - The training record
    * @param learnRate - The learning rate
    */
  def updateWeights(record :Array[Double], learnRate :Double): Unit ={

    var layerInputs :Array[Double] = new Array[Double](_numInputs)

    for(i <- 0 until _numInputs)
      layerInputs(i) = record(i)

    for(layerIdx <- _network.getLayers.indices){ // for each layer
      for(neuronIdx <- _network.getLayers(layerIdx).indices){  // for each neuron in the layer

        var thisNeuron =  _network.getLayers(layerIdx)(neuronIdx)

        for(input <- layerInputs.indices) // adjust the weights using prior level inputs
          thisNeuron._weights(input) += learnRate * thisNeuron._deltaError * layerInputs(input)

        thisNeuron._weights(thisNeuron._weights.length-1) += learnRate * thisNeuron._deltaError
      }
      layerInputs = new Array[Double](_network.getLayers(layerIdx).length)

      for(neuronIdx <- _network.getLayers(layerIdx).indices)
        layerInputs(neuronIdx) = _network.getLayers(layerIdx)(neuronIdx)._output
    }
  }

  /**
    * Train the network using training set
    * @param trainingSet - The training set with known class values
    * @param learningRate - Learning rate
    * @param numEpochs - Number of epochs (training cycles)
    */
  def trainNetwork(trainingSet :List[Array[Double]], learningRate :Double, numEpochs :Int): Unit ={

    for(epoch <- 0 until numEpochs){

      var error = 0.0d

      for(record <- trainingSet){

        var outputs   = forwardPropagate(record)
        var expected  = Array.fill[Double](n_outputs)(0)
        expected(record.last.toInt) = 1

        for(idx <- expected.indices)
          error += math.pow(expected(idx)-outputs(idx), 2)

        backPropagate(expected)
        updateWeights(record, learningRate)
      }
    }
  }

  /**
    * Forward propagate and return the prediction
    * @param record - The record to predict from
    * @return - The prediction
    */
  def predict(record: Array[Double]): Int = {
    val output = forwardPropagate(record)
    output.indexOf(output.max)
  }
}

