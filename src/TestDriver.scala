object TestDriver{

  def main(args: Array[String]): Unit = {

    var loader = SetLoader
    val set :List[Array[Double]] = loader.loadSet("./adder.txt")

    /*
    for(index <- set.indices){
      for(i2 <- set(index).indices)
        print(set(index)(i2) + " ")
      println()
    }
    */

    val n_inputs = set.head.length-1
    var net = new NeuralNet(n_inputs, 2, 2)
    net.trainNetwork(set, 0.50, 50)
  }
}