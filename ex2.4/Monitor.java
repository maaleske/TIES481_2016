package org.javasim.examples.basic;

import org.javasim.RestartException;
import org.javasim.Simulation;
import org.javasim.SimulationException;
import org.javasim.SimulationProcess;

public class Monitor extends SimulationProcess
{
    public Monitor(long interval, int mcount, int qcount)
    {
    	checkInterval = interval;
    	working = new long[mcount];
    	queueLength = new long[qcount];
    	machineCount = mcount; // # of machines M1
    	queueCount = qcount;
    }

    public void run ()
    {
    	
    	for(;;)
    	{
    		 try
             {
                 hold(checkInterval);
             }
             catch (SimulationException e)
             {
             }
             catch (RestartException e)
             {
             }
    		 
    		 checkCount++;
    		 
    		 for (int i=0; i<machineCount; i++)
    		 {
    			 // Note that we only monitor the machines M1
    			 if (MachineShop.M1[i].processing())
    			 {
        			 working[i]++;
    			 }
    		 }
    		 
    		 for (int i=0; i<queueCount; i++)
    		 {
    			 queueLength[i] += MachineShop.JobQ[i].queueSize();
    		 }
			 
    		 
    	}
    	
    }

    public void report ()
    {
        System.out.println("Checks "+checkCount);
        for (int i=0; i<machineCount; i++)
		{
        	System.out.println("Machine "+i+" utilization "+ ((double)working[i]/checkCount));
		}
        
        for (int i=0; i<queueCount; i++)
		{
        	System.out.println("Queue "+i+" average length "+ ((double)queueLength[i]/checkCount));
		}
    }
    
    public void restart()
    {
    	checkCount=0;
    	for (int i=0; i< machineCount; i++)
        {
    		working[i]=0;
    		queueLength[i]=0;
        }


    }
    
    private long checkCount;
    private long checkInterval;
    private long[] working;
    private long[] queueLength;
    private long machineCount;
    private long queueCount;
}
