#Ex4

1. We have the following factors (6) which have two values:
    - interarrival time distribution (unif/exp)    x1
    - interarrival time mean (22.5/25)             x2
    - preparation time distribution (unif/exp)     x3
    - recovery time distribution (unif/exp)        x4
    - number of preparation units (4/5)            x5
    - number of recovery units (4/5)               x6
    Let us denote these factors by x's. Now, there would be 64 different combinations to test and we 
    want to construct a design of 8 experiments (2^(6-3)). We choose to take those combinations
    for which x1x3x4=x1x5x6=x2x4x6=1 (if possible values are 1 and -1). This way, we didn't alias
    1st order terms to each order (which would have happened if we choose x1x2x3x4x5x6=1 and continue
    from that).
    
2. Now we take one experiment from the previous design:we choose the combination with the least rooms 
    and the shortest interarrival times. 
    
    ADD TABLE
    
    
