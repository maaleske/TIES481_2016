
import org.javasim.*;
import org.javasim.streams.*;

import java.io.IOException;

public class Arrivals extends SimulationProcess
{
    
public Arrivals (RandomStream inter, RandomStream pre, RandomStream op, RandomStream rec)
    {
	InterArrivalTime = inter;
	PreparationTime = pre;
	OperationTime = op;
	RecoveryTime = rec; 
    }

public void run ()
    {
	for (;;)
	{
	    try
	    {
		    Patient work = new Patient(PreparationTime.getNumber(), OperationTime.getNumber(), RecoveryTime.getNumber());
		    hold(InterArrivalTime.getNumber());
	    }
	    catch (SimulationException e)
	    {
	    }
	    catch (RestartException e)
	    {
	    }
	    catch (IOException e)
	    {
	    }
	}
    }    
private RandomStream InterArrivalTime;
private RandomStream PreparationTime;
private RandomStream OperationTime;
private RandomStream RecoveryTime;    
};
