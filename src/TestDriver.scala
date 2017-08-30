object TestDriver{

  def main(args: Array[String]): Unit = {

    var loader = SetLoader
    val set :List[Array[Double]] = loader.loadSet("./seeds_dataset.txt")

    /*
    for(index <- set.indices){
      for(i2 <- set(index).indices)
        print(set(index)(i2) + " ")
      println()
    }
    */

    var net = new NeuralNet(7, 3, 3)
    var output = net.forwardPropagate(set.head)
    output.foreach(E => print(E.+(" ")))
    println()
    print(net._network)
    println()
    var expected :Array[Double] = Array(0,0,1)

    net.backPropagate(expected)
    print(net._network)
    println()

    net.updateWeights(set.head, 0.3)
    print(net._network)
    println()
  }
}