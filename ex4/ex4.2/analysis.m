clear
close all

% We import the full time series
d = importdata('full_timeseries_1000000.dat');

% Plot the full time series
h(1) = figure();
plot(d);
title('Entry queue length');
ylabel('Patients');
xlabel('Time');

% Calculate and plot the autocorrelation
h(2) = figure();
autocorr(d, length(d)-1);

% d_sampled returns a sampling of the full data series
% (corresponds to decreasing the report rate of the Reporter)
d_sampled = @(interval) d(1:interval:end);

% avgs calculates a moving average with a len-sized window
avgs = @(x, len) conv(x, ones(1,len)./len);

% sample_avgs samples the averages, taking into account the window length
sample_avgs = @(x, interval, len) x(len/2:interval:end-len/2);

% Simulate a Reporter with a given report rate collecting samples of a
% given length with a given interval
report_rate = 10;
sample_interval = 3000;
sample_length = 1000;
d_simul = sample_avgs( avgs( d_sampled(report_rate),...
                             sample_length ...
                            ), ...
                       sample_interval,...
                       sample_length ...
                      );

% Plot the simulated time series
h(3) = figure();
plot(d_simul);
title('Average entry queue length');
ylabel('Patients');
xlabel('Time');

% Calculate and plot the autocorrelation
h(4) = figure();
autocorr(d_simul, length(d_simul)-1);

% Save to file
for k = 1:length(h)
    print(h(k), ['fig_', int2str(k)], '-dpng');
end