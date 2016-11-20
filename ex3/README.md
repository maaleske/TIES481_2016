Exercise 3: Running and analyzing experiments

So, like it was told, we run the simulation by using the following means:
  Interarrival time: 25
  Preparation time:  40
  Operation time:    20
  Recovery time:     40.
Ideally we should have 80% utilization of operation room and on average less than two 
patients in preparation and recovery. We run the simulation (20 independent samples of
1000 time units) and see what happens. The simulation data can be found in the .ods files
in this directory. We run the samples in such a way that we made one long simulation and
we cut a beginning (which is the initial transient).
TODO: kirjoita mikä oli kriteerinä - miten valittiin osa mikä pätkäistiin pois

1. 3 preparation rooms, 4 recovery rooms (3p,4r)
    
   AVERAGES:                                            3p,4r           3p,5r          4p,5r 
   Length of the queue before the preparation room:     4.37            4.04           1.84
   The length of the idle queue for prep. rooms:        0.54            0.58           1.30
   The probability of being in waiting state:           0.07            0.01           0.01
   
   CONFIDENCE INTERVALS:                                3p,4r           3p,5r          4p,5r 
   Length of the queue before the preparation room:     [2.35,6.38]     [2.07,6.02]    [0.46,3.22]
   The length of the idle queue for prep. rooms:        [0.31,0.77]     [0.35,0.82]    [0.92,1.69]
   The probability of being in waiting state:           [0.04,0.10]     [0.00,0.01]    [0.00,0.02]
