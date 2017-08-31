# NeuralNetBP
<p>
A back propigation neural net in Scala
</p>
<p>
The NeuralNet class contains all the methods for training and making predictions on a dataset.</br>
NeuralNet constructor takes three inputs: (number_inputs, number_hiddens, number_outputs).</br>
The SetLoader class loads numerical data into a list of Array[Double] and can be normalized using the normalizeZeroOne(min, max, inputs, dataset) method.</br>
<ul>
<li> Dataset is expected to be completely numerical. </li> 
<li> The number of outputs corrisponds to predictive classes </li>
<li> The known class variable in the dataset is expected to be in the last column </li>
<li> TestDriver class loads and tests the network against a biological dataset </li>
</ul>
