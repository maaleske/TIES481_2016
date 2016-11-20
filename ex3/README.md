Exercise 3: Running and analyzing experiments
Note: "tables" don't show properly

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
   Length of the idle queue for prep. rooms:            0.54            0.58           1.30
   Probability of operation being in waiting state:     0.07            0.01           0.01
   
   CONFIDENCE INTERVALS:                                3p,4r           3p,5r          4p,5r 
   Length of the queue before the preparation room:     [2.35,6.38]     [2.07,6.02]    [0.46,3.22]
   Length of the idle queue for prep. rooms:            [0.31,0.77]     [0.35,0.82]    [0.92,1.69]
   Probability of operation being in waiting state:     [0.04,0.10]     [0.00,0.01]    [0.00,0.02]

2. Do the results differ significantly?
   Let's take a look on the length of the queue before the preparation room. For (3p,4r) and (3p,5r)
   results don't change so much, and the confidence intervals (CIs) are highly overlapping. The 
   average decreases a little when there is one recovery room more available. Adding one 
   preparation room more makes a big difference: the average value is smaller having also a not
   much overlapping CI.
   
   Then let's take a look on the lengths of the idle queues. Again, there is not much difference
   between (3p,4r) and (3p,5r) results, but the results of (4p,5r) is remarkably different. Adding
   one preparation room more, the length of the idle queue for preparation room becomes shorter.
   
   In the third case, the result of (3p,4r) is different from the two others. So, the probability 
   of being in waiting state is higher when only 4 recovery rooms are available. When there are 5 
   available, it is no probable to have operation in a waiting state.
