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

  def normalizeMinMax(min :Int, max :Int, inputs: Int, dataSet :List[Array[Double]]): Unit = {

    var minMaxArray = new Array[Array[Double]](inputs)

    for(mmIdx <- minMaxArray.indices)
      minMaxArray(mmIdx) = Array(Double.MaxValue, Double.MinValue)

    for(row <- dataSet){
      for(colIdx <- 0 until inputs){
        if(row(colIdx) < minMaxArray(colIdx)(0))
          minMaxArray(colIdx)(0) = row(colIdx)
        if(row(colIdx) > minMaxArray(colIdx)(1))
          minMaxArray(colIdx)(1) = row(colIdx)
      }
    }
    for(row <- dataSet){
      for(colIdx <- 0 until inputs){
        row(colIdx) = (row(colIdx) - minMaxArray(colIdx)(0))/(minMaxArray(colIdx)(1) - minMaxArray(colIdx)(0))
      }
    }
  }
}
