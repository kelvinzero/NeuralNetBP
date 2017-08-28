import scala.util.Random

class NeuralNet (n_inputs :Int, n_hidden :Int, n_outputs :Int){

  val _numInputs  :Int          = n_inputs
  val _numHiddens :Int          = n_hidden
  val _numOutputs :Int          = n_outputs
  val _network    :Network.type = Network

  initializeNetwork()

  private def initializeNetwork(): Unit ={

    var inputlayer = Array.fill(n_inputs+1)(Random.nextDouble())
    var hiddenlayer = Array.fill(n_hidden+1)(Random.nextDouble())
    var outputlayer = Array.fill(n_outputs)(Random.nextDouble())

    _network.append(inputlayer)
    _network.append(hiddenlayer)
    _network.append(outputlayer)

    print(_network.toString)
  }


}

