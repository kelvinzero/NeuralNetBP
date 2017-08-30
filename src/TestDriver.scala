object TestDriver{

  def main(args: Array[String]): Unit = {

    val loader    : SetLoader.type       = SetLoader
    val dataSet   : List[Array[Double]]  = loader.loadSet("./seeds_dataset.txt")
    val n_inputs  : Int                  = dataSet.head.length-1
    val n_hiddens : Int                  = 3
    val n_outputs : Int                  = 4
    var error     : Double               = 0.0d
    val neuralNet : NeuralNet            = new NeuralNet(n_inputs, n_hiddens, n_outputs)

    loader.normalizeMinMax(0, 1, 7, dataSet)
    neuralNet.trainNetwork(dataSet, 0.03, 460)

    for(row <- dataSet){
      val prediction = neuralNet.predict(row)
      if(prediction - row.last != 0)
        error += 1
    }
    error /= dataSet.length
    printf("Error: %f%%\n", error*100)
  }
}