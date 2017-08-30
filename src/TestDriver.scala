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
    var net = new NeuralNet(2, 3, 2)
    var output = net.forwardPropagate(set.head)
    output.foreach(E => print(E.+(" ")))
    println()
    print(net._network)
    println()
    var expected :Array[Double] = Array(1, 0)

    net.backPropagate(expected)
    print(net._network)
    println()
  }
}