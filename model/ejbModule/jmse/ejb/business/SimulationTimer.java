package jmse.ejb.business;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.NoMoreTimeoutsException;
import javax.ejb.NoSuchObjectLocalException;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;

/**
 * Session Bean implementation class SimulationTimer
 */

//TODO Tried to implent my own Timer extension to hold active and not active...
public class SimulationTimer implements Timer {

    /**
     * Default constructor. 
     */
    public SimulationTimer() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void cancel() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TimerHandle getHandle() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getInfo() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getNextTimeout() throws IllegalStateException, NoSuchObjectLocalException, NoMoreTimeoutsException, EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleExpression getSchedule() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTimeRemaining() throws IllegalStateException, NoSuchObjectLocalException, NoMoreTimeoutsException, EJBException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCalendarTimer() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPersistent() throws IllegalStateException, NoSuchObjectLocalException, EJBException {
		// TODO Auto-generated method stub
		return false;
	}
    
    

}
