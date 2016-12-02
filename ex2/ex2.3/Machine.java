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
import org.javasim.SimulationException;
import org.javasim.SimulationProcess;

public class Machine extends SimulationProcess {

    public Machine(int type) {
        machineType = type;
        operational = true;
        working = false;
        J = null;
        MachineShop.IdleQ[machineType - 1].Enqueue(this);
    }

    public void run() {
        double ActiveStart, ActiveEnd;

        while (!terminated()) {
            working = true;

            switch (machineType) {
                case 1:
                    while (!MachineShop.JobQ[machineType - 1].isEmpty()) {
                        ActiveStart = currentTime();
                        MachineShop.CheckFreq++;

                        MachineShop.JobsInQueue += MachineShop.JobQ[machineType - 1].queueSize();
                        J = MachineShop.JobQ[machineType - 1].dequeue();

                        try {
                            hold(J.ServiceTime[machineType - 1]);
                        } catch (SimulationException e) {
                        } catch (RestartException e) {
                        }

                        ActiveEnd = currentTime();
                        MachineShop.MachineActiveTime[machineType - 1] += ActiveEnd - ActiveStart;
                        MachineShop.JobQ[machineType].enqueue(J);

                        if (!MachineShop.IdleQ[machineType].IsEmpty()) {
                            Machine next = (Machine) MachineShop.IdleQ[machineType].Dequeue();

                            if (!MachineShop.JobQ[machineType].isEmpty() && !next.processing()
                                    && next.isOperational()) {
                                try {
                                    next.activate();
                                } catch (SimulationException e) {
                                } catch (RestartException e) {
                                }
                            }
                        }

                    }

                    MachineShop.IdleQ[machineType - 1].Enqueue(this);
                    break;

                case 2:
                    while (!MachineShop.JobQ[machineType - 1].isEmpty()) {
                        ActiveStart = currentTime();
                        MachineShop.CheckFreq++;

                        MachineShop.JobsInQueue += MachineShop.JobQ[machineType - 1].queueSize();
                        J = MachineShop.JobQ[machineType - 1].dequeue();

                        try {
                            hold(J.ServiceTime[machineType - 1]);
                        } catch (SimulationException e) {
                        } catch (RestartException e) {
                        }

                        ActiveEnd = currentTime();
                        MachineShop.MachineActiveTime[machineType - 1] += ActiveEnd - ActiveStart;
                        MachineShop.JobQ[machineType].enqueue(J);

                        if (!MachineShop.IdleQ[machineType].IsEmpty()) {
                            Machine next = (Machine) MachineShop.IdleQ[machineType].Dequeue();

                            if (!MachineShop.JobQ[machineType].isEmpty() && !next.processing()
                                    && next.isOperational()) {
                                try {
                                    next.activate();
                                } catch (SimulationException e) {
                                } catch (RestartException e) {
                                }
                            }
                        }

                    }

                    MachineShop.IdleQ[machineType - 1].Enqueue(this);
                    break;

                case 3:
                    while (!MachineShop.JobQ[machineType - 1].isEmpty()) {
                        ActiveStart = currentTime();
                        MachineShop.CheckFreq++;

                        MachineShop.JobsInQueue += MachineShop.JobQ[machineType - 1].queueSize();
                        J = MachineShop.JobQ[machineType - 1].dequeue();

                        try {
                            hold(J.ServiceTime[machineType - 1]);
                        } catch (SimulationException e) {
                        } catch (RestartException e) {
                        }

                        ActiveEnd = currentTime();
                        MachineShop.MachineActiveTime[machineType - 1] += ActiveEnd - ActiveStart;
                        MachineShop.ProcessedJobs++;

                        /*
                         * Introduce this new method because we usually rely upon the
                         * destructor of the object to do the work in C++.
                         */
                        J.finished();
                    }

                    MachineShop.IdleQ[machineType - 1].Enqueue(this);
                    break;
            }
            working = false;

            try {
                cancel();
            } catch (RestartException e) {
            }
        }
    }

    public void broken() {
        operational = false;
    }

    public void fixed() {
        operational = true;
    }

    public boolean isOperational() {
        return operational;
    }

    public boolean processing() {
        return working;
    }

    private boolean operational;

    private boolean working;

    private int machineType;

    private Job J;
}
