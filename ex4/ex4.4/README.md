# Ex 4.4

We used MATLAB (see [the code](analysis.m)) to calculate regression model parameters for both average queue length and average utilization rate from the results of the excercise 4.3. We got the following results:

Regression model for average queue length:
y =  369.10  +209.87 * x1  -236.17 * x2  +40.30 * x3  +15.15 * x4  -85.63 * x5  -55.75 * x6  -133.92 * x1 * x2

Regression model for average utilization rate:
y =  85.37  +0.04 * x1  -5.08 * x2  -1.27 * x3   -1.30 * x4  -0.49 * x5   -0.49 * x6   -0.33 * x1 * x2

From the formulas above we can directly read the impact that each system parameter has on the observables. Interarrival time distribution (x1) has very significant impact on queue length, with uniform distribution shortening the queue. For the utilization rate it is almost meaningless though. Mean of the interarrival time (x2) is of great significance for both observables, high mean shortening the queue but lowering the utilization. The joint effect of the previous two is consequently very significant for queue length, but has little impact on utilization rate. Preparation time distribution (x3) has very small impact on queue length, with uniform distribution shortening the queue,  but very meaningful impact on utilization rate, with uniform distribution raising the rate. Interestingly recovery time distribution (x4) has almost same impact on utilization rate, but significantly smaller, although similar, impact on queue length. The amounts of preparation (x5) and recovery (x6) rooms also have similar impacts on variables: having more rooms shortens the queue significantly, but lowers the utilization rate, though the impact on utilization rate is negligible.
