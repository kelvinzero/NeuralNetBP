import scala.collection.mutable.ListBuffer
import scala.io.Source

object SetLoader{

  def loadSet(path :String): List[Array[Double]] = {

    var dataSet :ListBuffer[Array[Double]] = new ListBuffer[Array[Double]]

    for(line <- Source.fromFile(path).getLines()){

      val splitline = line.split("\t+| +")
      var numericLine = new Array[Double](splitline.length)

      for(index <- splitline.indices)
        numericLine(index) = splitline(index).toDouble

      dataSet += numericLine
    }
    dataSet.toList
  }
}
