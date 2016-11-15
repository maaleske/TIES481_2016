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



import java.io.IOException;

import org.javasim.RestartException;
import org.javasim.SimulationException;
import org.javasim.SimulationProcess;
import org.javasim.streams.ExponentialStream;

public class Arrivals extends SimulationProcess
{
    public Arrivals(
    		ExponentialStream interArrival,
    		ExponentialStream s1TimeMean,
    		ExponentialStream s2TimeMean,
    		ExponentialStream s3TimeMean
    		)
    {
        InterArrivalTime = interArrival;
        Service1Time = s1TimeMean;
        Service2Time = s2TimeMean;
        Service3Time = s3TimeMean;
    }

    public void run ()
    {
        while (!terminated())
        {
            try
            {
                hold(InterArrivalTime.getNumber());
                double[] sTimes = {Service1Time.getNumber(), Service2Time.getNumber(), Service3Time.getNumber()};
                new Patient(sTimes);
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
    
    private ExponentialStream InterArrivalTime;

    private ExponentialStream Service1Time;
    
    private ExponentialStream Service2Time;
    
    private ExponentialStream Service3Time;
}