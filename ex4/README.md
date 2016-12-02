#Ex4

1.  We have the following factors (6) which have two values:
    - (x1) interarrival time distribution (unif/exp)
    - (x2) interarrival time mean (22.5/25)
    - (x3) preparation time distribution (unif/exp)
    - (x4) recovery time distribution (unif/exp)
    - (x5) number of preparation units (4/5)
    - (x6) number of recovery units (4/5)

    Let us denote these factors by x's. Now, there would be 64 different combinations to test and we want to construct a design of 8 experiments (2^(6-3)). We choose to take those combinations for which x1x3x4=x1x5x6=x2x4x6=1 (if possible values are 1 and -1). This way, we didn't alias 1st order terms to each order (which would have happened if we had chosen x1x2x3x4x5x6=1, etc.)
 
    This resulted in the set of simulation experiments with the following selection of parameters:

    ADD THE TABLE
    
2.  We chose the worst-case scenario (one with the lowest interarrival times and longest preparation/recovery times) of our 8 tests to run a single simulation, and used MATLAB to analyze the series correlation properties. See [the full analysis.](./ex4.2/) 

    
3.  We decided 
