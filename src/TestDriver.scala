object TestDriver{

  def main(args: Array[String]): Unit = {

    var loader = SetLoader
    val set :List[Array[Double]] = loader.loadSet("./adder.txt")
    val n_inputs = set.head.length-1
    var net = new NeuralNet(n_inputs, 2, 2)
    net.trainNetwork(set, 0.50, 50)

    for(row <- set){
      val prediction = net.predict(row)
      printf("Expected: %f, Actual: %d\n", row.last, prediction)
    }
  }
}