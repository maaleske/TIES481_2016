/*
 * Copyright 1990-2008, Mark Little, University of Newcastle upon Tyne
 * and others contributors as indicated 
 * by the @authors tag. All rights reserved. 
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors. 
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 * 
 * (C) 1990-2008,
 */

package org.javasim.examples.basic;

import org.javasim.RestartException;
import org.javasim.Scheduler;
import org.javasim.Simulation;
import org.javasim.SimulationException;
import org.javasim.SimulationProcess;
import org.javasim.streams.ExponentialStream;

public class MachineShop extends SimulationProcess
{
    public MachineShop(boolean isBreaks)
    {
        useBreaks = isBreaks;
        TotalResponseTime = 0.0;
        TotalJobs = 0;
        ProcessedJobs = 0;
        JobsInQueue = 0;
        CheckFreq = 0;
        double[] machineActiveTime = {0.0 , 0.0 , 0.0};
        MachineActiveTime = machineActiveTime;
        double[] machineFailedTime = {0.0 , 0.0 , 0.0};
        MachineFailedTime = machineFailedTime;
        M1Count = 3;
        M2Count = 3;
        M3Count = 3;
        M1 = new Machine[M1Count];
        M2 = new Machine[M2Count];
        M3 = new Machine[M3Count];
        JobQ = new Queue[3];
        IdleQ = new ProcessQueue[3];
        for (int i=0; i < 3; i++) JobQ[i] = new Queue();
        for (int i=0; i < 3; i++) IdleQ[i] = new ProcessQueue();
    }

    public void run ()
    {
        try
        {
            Breaks B = null;
            Arrivals A = new Arrivals(5, 20);
            for (int i=0;i < M1Count; i++)
            {
            	MachineShop.M1[i] = new Machine(1);
            }
            for (int i=0;i < M2Count; i++)
            {
            	MachineShop.M2[i] = new Machine(2);
            }
            for (int i=0;i < M3Count; i++)
            {
            	MachineShop.M3[i] = new Machine(3);
            }
            
            double[] times = {5, 5, 5};
            Job J = new Job(times);

            A.activate();

            if (useBreaks)
            {
                B = new Breaks();
                B.activate();
            }

            Simulation.start();

            while (MachineShop.ProcessedJobs < 10000)
                hold(1000);

            System.out.println("Current time "+currentTime());
            System.out.println("Total number of jobs present " + TotalJobs);
            System.out.println("Total number of jobs processed "
                    + ProcessedJobs);
            System.out.println("Total response time of " + TotalResponseTime);
            System.out.println("Average response time = "
                    + (TotalResponseTime / ProcessedJobs));
            System.out
                    .println("Probability that one of the machine 1 is working = "
                            + ((MachineActiveTime[0] - MachineFailedTime[0]) /(3 * currentTime())));
            //System.out.println("Probability that machine 1 has failed = "
            //        + (MachineFailedTime[0] / MachineActiveTime[0]));
            System.out
                    .println("Probability that one of the machine 2 is working = "
                            + ((MachineActiveTime[1] - MachineFailedTime[1]) /(3 * currentTime())));
            //System.out.println("Probability that machine 2 has failed = "
            //        + (MachineFailedTime[1] / MachineActiveTime[1]));
            System.out
                    .println("Probability that one of the machine 3 is working = "
                            + ((MachineActiveTime[2] - MachineFailedTime[2]) /(3 * currentTime())));
            //System.out.println("Probability that machine 3 has failed = "
            //        + (MachineFailedTime[2] / MachineActiveTime[2]));
            System.out.println("Average number of jobs present = "
                    + (JobsInQueue / CheckFreq));

            Simulation.stop();

            A.terminate();
            
            for (int i=0;i < M1Count; i++)
            {
            	MachineShop.M1[i].terminate();
            }
            
            for (int i=0;i < M2Count; i++)
            {
            	MachineShop.M2[i].terminate();
            }
            
            for (int i=0;i < M3Count; i++)
            {
            	MachineShop.M3[i].terminate();
            }

            if (useBreaks)
                B.terminate();

            SimulationProcess.mainResume();
        }
        catch (SimulationException e)
        {
        }
        catch (RestartException e)
        {
        }
    }

    public void await ()
    {
        this.resumeProcess();
        SimulationProcess.mainSuspend();
    }

    public static Machine[] M1 = null;
    
    public static int M1Count;

    public static Machine[] M2 = null;
    
    public static int M2Count;

    public static Machine[] M3 = null;
    
    public static int M3Count;
    
    public static ProcessQueue[] IdleQ = null;

    public static Queue[] JobQ = null;

    public static ExponentialStream ServiceTimes = null;
    
    public static double TotalResponseTime = 0.0;

    public static long TotalJobs = 0;

    public static long ProcessedJobs = 0;

    public static long JobsInQueue = 0;

    public static long CheckFreq = 0;

    public static double[] MachineActiveTime = null;

    public static double[] MachineFailedTime = null;

    private boolean useBreaks;
}
