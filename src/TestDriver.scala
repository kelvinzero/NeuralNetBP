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
    var net = new NeuralNet(8, 2, 6)
    var output = net.forwardPropagate(set.head)
    output.foreach(E => print(E + " "))
    println()
  }
}