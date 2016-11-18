
import org.javasim.*;


public class PreparationRoom extends SimulationProcess
{
    

public PreparationRoom ()
    {
	working = false;
	J = null;
    }

public void run ()
    {
	for (;;)
	{
	    working = true;
	    if (!Clinic.EntQ.isEmpty()) {
	    	J = Clinic.EntQ.remove();
	    	try {
	    		hold(J.PreparationTime());
	    		Clinic.JobQ.add(J);
	    		if (!Clinic.M.Processing()) {
	    			Clinic.M.activate();
	    		}
	    	}
	    	catch (SimulationException e){}
	    	catch (RestartException e){}
	    	working = false;
	    	Clinic.WaitQ.add(this);
	    	try {
	    		passivate();
	    	}
	    	catch (RestartException e){}
	    }
	}
    }

public void Block ()
    {
	operational = false;
    }
    
public void Release ()
    {
	operational = true;
    }
    
public boolean IsOperational ()
    {
	return operational;
    }
    
public boolean Processing ()
    {
	return working;
    }
    

private boolean operational;
private boolean working;
private Patient J;

};
