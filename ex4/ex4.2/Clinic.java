import org.javasim.*;
import org.javasim.streams.*;
import java.util.*;
import java.io.*;


public class Clinic extends SimulationProcess
{
    
public Clinic ()
    {
	for(int i=0;i<4;i++)
		{
			PRooms[i]= new PreparationRoom();
			IWQ.add(PRooms[i]);
		}
	
	for(int i=0;i<4;i++)
		{
			RRooms[i]= new RecoveryRoom();
			IRQ.add(RRooms[i]);
		}

    }
    
public void run ()
    {
	try
	{
		ExponentialStream inter = new ExponentialStream(22.5,0,123256487,984653);
                ExponentialStream pre  = new ExponentialStream(40,10,5454315,42);
                ExponentialStream op = new ExponentialStream(20,20,687132135,545678231);
                ExponentialStream rec = new ExponentialStream(40,30,672315489,3877953);

		Arrivals A = new Arrivals(inter, pre, op, rec);
	    Clinic.M = new OperationRoom();
	    
	    monitor.activate();
	    A.activate();
	    
	    Simulation.start();
	    hold(10000);
	    monitor.reset();
            try{
            PrintStream out = new PrintStream(new FileOutputStream("data.dat"));
            System.setOut(out);
            }
            catch(FileNotFoundException e){
            }
             
	    for(int i=0;i<1000000;i++)
	    {
                hold(1);
                monitor.report();
                //hold(1000);
                monitor.reset();
	    }
            
	    System.out.println("Total number of jobs present "+TotalJobs);
	    System.out.println("Total number of jobs processed "+ProcessedJobs);
	    System.out.println("Total response time of "+TotalResponseTime);
	    System.out.println("Average response time = "+(TotalResponseTime / ProcessedJobs));
	    System.out.println("Probability that machine is working = "+((MachineActiveTime - MachineFailedTime) / currentTime()));
	    System.out.println("Probability that machine has failed = "+(MachineFailedTime / MachineActiveTime));
	    System.out.println("Average number of jobs present = "+(JobsInQueue / CheckFreq));


	    Simulation.stop();

	    A.terminate();
	    Clinic.M.terminate();
	    monitor.terminate();
    
	    SimulationProcess.mainResume();
	}
	catch (SimulationException e)
	{
	}
	catch (RestartException e)
	{
	}
    }

public void Await ()
    {
	this.resumeProcess();
	SimulationProcess.mainSuspend();
    }

public static OperationRoom M = null;
public static PreparationRoom[] PRooms = new PreparationRoom [10];
public static RecoveryRoom[] RRooms = new RecoveryRoom [10];
public static Reporter monitor = new Reporter(1);
public static LinkedList<Patient> JobQ = new LinkedList<Patient>();
public static LinkedList<Patient> RecQ = new LinkedList<Patient>();
public static LinkedList<Patient> EntQ = new LinkedList<Patient>();
public static LinkedList<SimulationProcess> WaitQ = new LinkedList<SimulationProcess>(); //preparations waiting operation
public static LinkedList<SimulationProcess> IWQ = new LinkedList<SimulationProcess>(); // Idle preparations
public static LinkedList<SimulationProcess> IRQ = new LinkedList<SimulationProcess>(); //Idle Recoveries

public static double TotalResponseTime = 0.0;
public static long TotalJobs = 0;
public static long ProcessedJobs = 0;
public static long JobsInQueue = 0;
public static long CheckFreq = 0;
public static double MachineActiveTime = 0.0;
public static double MachineFailedTime = 0.0;
    

    
};
