The class LehmerGen.java includes our implementation of the original Lehmer generator. Actually you can run Lehmer generator
by using different sets of constants, a couple of examples are given in the comments.

Also we have implemented a shuffled version of Lehmer generator, in which we used a Sinclair generator values, too. For more
information, see the comments of the file.

For testing we have also implemented a method "vectorpart" which picks those values of an array, which next number 
exists in a given interval. So, this makes testing of randomness easier: also a given subset of numbers should be
random (uniformely).
