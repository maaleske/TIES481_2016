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



import org.javasim.RestartException;
import org.javasim.Scheduler;
import org.javasim.Simulation;
import org.javasim.SimulationException;
import org.javasim.SimulationProcess;
import org.javasim.streams.ExponentialStream;

public class Clinic extends SimulationProcess
{
    public Clinic(boolean isBreaks)
    {
        useBreaks = isBreaks;
        TotalResponseTime = 0.0;
        TotalPatients = 0;
        ProcessedJobs = 0;
        PatientsInQueue = 0;
        CheckFreq = 0;
        double[] machineActiveTime = {0.0 , 0.0 , 0.0};
        RoomActiveTime = machineActiveTime;
        double[] machineFailedTime = {0.0 , 0.0 , 0.0};
        RoomFailedTime = machineFailedTime;
        PreparationRooms = 3;
        OperationRooms = 1;
        RecoveryRooms = 3;
        Prep = new Room[PreparationRooms];
        Oper = new Room[OperationRooms];
        Reco = new Room[RecoveryRooms];
        PatientQ = new Queue[3];
        IdleQ = new ProcessQueue[3];
        WaitingQ = new ProcessQueue[2];
        for (int i=0; i < 3; i++) PatientQ[i] = new Queue();
        for (int i=0; i < 3; i++) IdleQ[i] = new ProcessQueue();
        for (int i=0; i < 2; i++) WaitingQ[i] = new ProcessQueue();
    }

    public void run ()
    {
        try
        {
            Breaks B = null;
            Arrivals A = new Arrivals(
            		new ExponentialStream(25), // Interarrival time distr.
            		new ExponentialStream(40), // Preparation - 
            		new ExponentialStream(20), // Operation -
            		new ExponentialStream(40)  // Recovery -
            		);
            for (int i=0;i < PreparationRooms; i++)
            {
            	Clinic.Prep[i] = new Room(1);
            }
            for (int i=0;i < OperationRooms; i++)
            {
            	Clinic.Oper[i] = new Room(2);
            }
            for (int i=0;i < RecoveryRooms; i++)
            {
            	Clinic.Reco[i] = new Room(3);
            }
            Monitor Mon = new Monitor(1, PreparationRooms, 3);
            
            //double[] times = {5, 5, 5};
            //Patient J = new Patient(times);

            A.activate();
            Mon.activate();

            if (useBreaks)
            {
                B = new Breaks();
                B.activate();
            }

            Simulation.start();

            hold(1000);
            
            
            //while (Clinic.ProcessedJobs < 10000)
            //   hold(1000);

            for (int i=0; i<20; i++)
            {
            	Mon.restart();
	            hold(1000);
	            Mon.report();
	            hold(1000);
            }
            
            
            System.out.println("Current time "+currentTime());
            System.out.println("Total number of jobs present " + TotalPatients);
            System.out.println("Total number of jobs processed "
                    + ProcessedJobs);
            System.out.println("Total response time of " + TotalResponseTime);
            System.out.println("Average response time = "
                    + (TotalResponseTime / ProcessedJobs));
            System.out
                    .println("Probability that one of the machine 1 is working = "
                            + ((RoomActiveTime[0] - RoomFailedTime[0]) /(3 * currentTime())));
            //System.out.println("Probability that machine 1 has failed = "
            //        + (MachineFailedTime[0] / MachineActiveTime[0]));
            System.out
                    .println("Probability that one of the machine 2 is working = "
                            + ((RoomActiveTime[1] - RoomFailedTime[1]) /(3 * currentTime())));
            //System.out.println("Probability that machine 2 has failed = "
            //        + (MachineFailedTime[1] / MachineActiveTime[1]));
            System.out
                    .println("Probability that one of the machine 3 is working = "
                            + ((RoomActiveTime[2] - RoomFailedTime[2]) /(3 * currentTime())));
            //System.out.println("Probability that machine 3 has failed = "
            //        + (MachineFailedTime[2] / MachineActiveTime[2]));
            System.out.println("Average number of jobs present = "
                    + (PatientsInQueue / CheckFreq));

            Simulation.stop();

            A.terminate();
            
            for (int i=0;i < PreparationRooms; i++)
            {
            	Clinic.Prep[i].terminate();
            }
            
            for (int i=0;i < OperationRooms; i++)
            {
            	Clinic.Oper[i].terminate();
            }
            
            for (int i=0;i < RecoveryRooms; i++)
            {
            	Clinic.Reco[i].terminate();
            }

            Mon.report();
            Mon.terminate();
            
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

    public static Room[] Prep = null;
    
    public static int PreparationRooms;

    public static Room[] Oper = null;
    
    public static int OperationRooms;

    public static Room[] Reco = null;
    
    public static int RecoveryRooms;
    
    public static ProcessQueue[] IdleQ = null;
    
    public static ProcessQueue[] WaitingQ = null;

    public static Queue[] PatientQ = null;

    public static ExponentialStream ServiceTimes = null;
    
    public static double TotalResponseTime = 0.0;

    public static long TotalPatients = 0;

    public static long ProcessedJobs = 0;

    public static long PatientsInQueue = 0;

    public static long CheckFreq = 0;

    public static double[] RoomActiveTime = null;

    public static double[] RoomFailedTime = null;

    private boolean useBreaks;
}
