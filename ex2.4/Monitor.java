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
    	//working = new long[mcount];
    	working = 0;
    	//queueLength = new long[qcount];
    	queueLength = 0;
    	machineCount = mcount;
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
    			 
    			 //if (MachineShop.M[i].processing())
    			 if (MachineShop.M.processing())
    			 {
    				 working++;
        			 // working[i]++;
    			 }
    		 }
    		 
    		 for (int i=0; i<queueCount; i++)
    		 {
    			 // queueLength[i] += MachineShop.JobQ[i].queueSize();
    			 queueLength += MachineShop.JobQ.queueSize();
    		 }
			 
    		 
    	}
    	
    }

    public void report ()
    {
    	double utilization = Long.valueOf(working).doubleValue()/Long.valueOf(checkCount).doubleValue();
    	double queueAvg = Long.valueOf(queueLength).doubleValue()/Long.valueOf(checkCount).doubleValue();
        System.out.println("Checks "+checkCount);
        for (int i=0; i<machineCount; i++)
		{
        	System.out.println("Machine "+i+" utilization "+ utilization);
		}
        
        for (int i=0; i<queueCount; i++)
		{
        	System.out.println("Queue "+i+" average length "+ queueAvg);
		}
    }
    
    public void restart()
    {
    	checkCount=0;
    	for (int i=0; i< machineCount; i++)
        {
    		//working[i]=0;
    		working=0;
    		//queueLength[i]=0;
    		queueLength=0;
        }


    }
    
    private long checkCount;
    private long checkInterval;
    //private long[] working;
    private long working;
    //private long[] queueLength;
    private long queueLength;
    private long machineCount;
    private long queueCount;
}
