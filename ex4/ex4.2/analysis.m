clear
close all

d = importdata('full_timeseries_1000000.dat');
d_sampled = @(interval) d(1:interval:end);

% Calculate averages over len-sized times
avgs = @(x, len) conv(x, ones(1,len)./len);
sample = @(x, interval, len) x(len/2:interval:end-len/2);

report_rate = 10;
sample_interval = 3000;
sample_length = 1000;
d_simul = sample(avgs(d_sampled(report_rate),sample_length), sample_interval, sample_length);

h(1) = figure();
plot(d_simul)

h(2) = figure();
autocorr(d_simul, length(d_simul)-1);
%plot(acf)