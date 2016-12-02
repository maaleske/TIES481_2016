clear
close all


% Import our experiments in a matrix form
X = importdata('experiments.dat');

% Import the data for regression modeling the queue length
Y1 = importdata('average_queues.dat');

% Import the data for regression modeling the utilization
Y2 = importdata('average_utilizations.dat');

% Regression model: Y1 = X * B1
B1 = X\Y1;

% Regression model: Y2 = X * B2
B2 = X\Y2;