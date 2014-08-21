package jmse.ejb.business;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 * Session Bean implementation class CreateRunningSimulationBean
 */
@Stateless
public class CreateRunningSimulationBean {
	
	////private int simulationId;

    /**
     * Default constructor. 
     */
    public CreateRunningSimulationBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    @Resource
    TimerService service;

    public void startTimer(int simulationId) {
    	//this.setSimulationId(simulationId);
    	System.out.println(":) Timers set for: " + simulationId);
        Timer timer = service.createTimer(15000, 10000, null);//inital start, interval duration, serializeInfo
        
    }
 
    @Timeout
    public void handleTimeout(Timer timer) {
        System.out.println("Handle timeout event here... " + timer.getNextTimeout());
    }
 
    public String checkTimerStatus() {
        Timer timer = null;
        Collection<Timer> timers = service.getTimers();
        Iterator<Timer> iterator = timers.iterator();
        while (iterator.hasNext()) {
            timer = iterator.next();
            return ("Timer will expire after " +
                    timer.getTimeRemaining() + " milliseconds.");
        }
        return ("No timer found");
    }

}

















