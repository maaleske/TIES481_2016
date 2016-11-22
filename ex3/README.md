Exercise 3: Running and analyzing experiments

So, like it was told, we run the simulation by using the following means:
  Interarrival time: 25
  Preparation time:  40
  Operation time:    20
  Recovery time:     40.
Ideally, we should have 80% utilization of operation room and on average less than two 
patients in preparation and recovery. We run the simulation (20 independent samples of
1000 time units) and see what happens. The simulation data can be found in the .ods files
in this directory. We run the samples in such a way that we made one long simulation and
we cut a beginning (which is the initial transient).

1. 3 preparation rooms, 4 recovery rooms (3p,4r)
    
   AVERAGES:                                         |  3p,4r        |  3p,5r       |  4p,5r
    ------------------------------------------------ | -------------:| ------------:| ------:
   Length of the queue before the preparation room:  |  4.37         |  4.04        |  1.84
   Length of the idle queue for prep. rooms:         |  0.54         |  0.58        |  1.30
   Probability of operation being in waiting state:  |  0.07         |  0.01        |  0.01
   
   CONFIDENCE INTERVALS:                             |  3p,4r        |  3p,5r       |  4p,5r 
    ------------------------------------------------ | -------------:| ------------:| ------:
   Length of the queue before the preparation room:  |  [2.35,6.38]  |  [2.07,6.02] |  [0.46,3.22]
   Length of the idle queue for prep. rooms:         |  [0.31,0.77]  |  [0.35,0.82] |  [0.92,1.69]
   Probability of operation being in waiting state:  |  [0.04,0.10]  |  [0.00,0.01] |  [0.00,0.02]

2. Do the results differ significantly?
   Let's take a look at the length of the queue before the preparation room. For (3p,4r) and (3p,5r)
   results don't change so much, and the confidence intervals (CIs) are highly overlapping. The 
   average decreases a little when there is one recovery room more available. Adding one 
   preparation room more makes a big difference: the average value is smaller having also a not
   much overlapping CI.
   
   Then let's take a look at the lengths of the idle queues. Again, there is not much difference
   between (3p,4r) and (3p,5r) results, but the results of (4p,5r) are remarkably different. Adding
   one preparation room more, the length of the idle queue for preparation room becomes shorter.
   It should be noted that even though in the case of (4p,5r) on a given time there ought to be at
   least one preparation room in the idle state, the average queue of patients more than doubles if
   we move to the (3p,5r) configuration.
   
   In the third case, the result of (3p,4r) is different from the two others. So, the probability 
   of being in waiting state is higher when only 4 recovery rooms are available. When there are 5 
   available, it is no probable to have the operation room in a waiting state.
   
3. Now we consider simulation results pairwise. All the calculations can be found in
   the file "ties_laskuja_PROSENTEILLA.ods". We used the same random seed for each
   configuration but the seed is different in different runs. We have studied results 
   pairwise in such a way that in particular we study differences between the same 
   configurations (but different runs). 
   We have calculated differences for each simulation samples and finally calculated
   averages, standard deviations and confidence intervals.

   AVERAGES:                                         |  3p,4r        |  3p,5r       |  4p,5r 
    ------------------------------------------------ | -------------:| ------------:| ------:
   Length of the queue before the preparation room:  |  -1.38        |  -0.92       |  0.04
   Length of the idle queue for prep. rooms:         |  0.17         |  0.13        |  0.11
   Probability of operation being in waiting state:  |  -0.01        |  0.00        |  0.00
   
   CONFIDENCE INTERVALS:                             |  3p,4r        |  3p,5r       |  4p,5r 
    ------------------------------------------------ | -------------:| ------------:| ------:
   Length of the queue before the preparation room:  |  [-4.58,1.82] |  [-3.88,2.04]|  [-1.70,1.79]
   Length of the idle queue for prep. rooms:         |  [-0.10,0.44] |  [-0.16,0.42]|  [-0.35,0.57]
   Probability of operation being in waiting state:  |  [-0.03,0.01] |  [-0.01,0.01]|  [-0.01,0.02]

   The differences between configurations are significant if confidence intervals do not overlap
   well. Now results for are more similar than previously. The configuration (4p,5r) has shorter
   CI for the length of the queue before the preparation room and longer for the length of the idle
   queue for preparation rooms, but intervals are overlapping pretty well. 

   On the other hand, averages of differences should be really close to zero. Then there would not 
   be much difference between different simulations. The results for the length of the queue before
   the preparation room are farther away from zero, but otherwise they are pretty close to zero.
   
   Large absolute values of the averages of the differences would tell us that our results would 
   have been highly dependent on the particular simulation run. In other words, our samples would
   have been too short or there would have been too few of them. In this case, the averages seem to
   be quite low compared to the length of the confidence interval, so we can assume our results to
   be good enough for qualitative analysis on the difference of the configurations.


4. Now we consider the blocking of the operation room. Operation room is blocked when a patient is
   ready, but all the recovery rooms are busy, so the patient can not continue. Our simulations gave
   following results:
   

   AVERAGES:                                         |  3p,4r        |  3p,5r       |  4p,5r 
    ------------------------------------------------ | -------------:| ------------:| ------:
   Probability of op. room being blocked (1st run)   |  0.02         |  0.01        |  0.01
   Probability of rec. room being busy  (1st run)    |  0.07         |  0.02        |  0.02
   Probability of op. room being blocked (2nd run)   |	0.03         |  0.01        |  0.01
   Probability of rec. room being busy  (2nd run)    |  0.08         |  0.03        |  0.03
   
   CONFIDENCE INTERVALS:                             |  3p,4r        |  3p,5r       |  4p,5r 
    ------------------------------------------------ | -------------:| ------------:| ------:
   Probability of op. room being blocked (1st run)   |  [0.01,0.03]  |  [0.00,0.01] |  [0.00,0.02]
   Probability of rec. room being busy  (1st run)    |  [0.04,0.10]  |  [0.00,0.04] |  [0.00,0.03]
   Probability of op. room being blocked (2nd run)   |	[0.01,0.04]  |  [0.00,0.01] |  [0.00,0.01]
   Probability of rec. room being busy  (2nd run)    |  [0.05,0.11]  |  [0.01,0.04] |  [0.02,0.04]

   Between different configurations, the probabilities of the operation room being blocked when
   observed directly seem somewhat similar. Yet, this result is unreliable, as the actual count of
   observed blocked states is very small. Most samples had operation room blocked zero times, some
   of them had it few times.
   
   More reliable variable, in this case is, the probability of all recovery rooms being busy, as these
   situations occurred significantly often. As we can see, these probabilities vary noticeably between
   configurations. Adding one recovery room to our system reduced the propability to approximately one
   third in both runs, which seems reasonable. Confidence interval's length gets also reduced.
   
   These two variables are not the same, but they are related. A naive assumption would be that the 
   probability of the operation room being blocked would be simply a product of the probabilities of the
   operation being finished at a given time and the recovery rooms being busy at a given time. Because
   we know the distribution of the operating times, we could easily calculate the blocked probability
   from the busy probability. However, this approach has a pitfall in it, as the two probabilities are
   most likely not independent. For a reliable calculation, one should thus consider the interdependence
   and covariance between the variables.