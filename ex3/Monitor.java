

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
    	roomCount = mcount; // # of machines M1
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
    		 
    		 for (int i=0; i<roomCount; i++)
    		 {
    			 // Note that we only monitor the machines M1
    			 if (Clinic.Prep[i].processing())
    			 {
        			 working[i]++;
    			 }
    		 }
    		 
    		 for (int i=0; i<queueCount; i++)
    		 {
    			 queueLength[i] += Clinic.PatientQ[i].queueSize();
    		 }
			 
    		 
    	}
    	
    }

    public void report ()
    {
        System.out.println("Checks "+checkCount);
        for (int i=0; i<roomCount; i++)
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
    	for (int i=0; i<roomCount; i++)
        {
    		working[i]=0;
        }
    	for (int i=0; i<queueCount; i++)
    	{
    		queueLength[i]=0;
    	}


    }
    
    private long checkCount;
    private long checkInterval;
    private long[] working;
    private long[] queueLength;
    private long roomCount;
    private long queueCount;
}
