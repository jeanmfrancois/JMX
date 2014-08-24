package jmse.ejb.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerHandle;
import javax.ejb.TimerService;
import javax.inject.Named;

import jmse.ejb.facade.SecurityFacade;
import jmse.ejb.facade.SimulationFacade;
import jmse.persistence.model.Security;
import jmse.persistence.model.Simulation;
import jmse.utils.Graphing;

/**
 * Singleton Bean implementation class RunningSimulation
 */
//@Singleton
@Singleton
public class RunningSimulation implements Serializable {	

    /**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private final static int EXECUTIONS_PER_DAY = 10;
	
	//private final static int ADJUSTED_EXECUTIONS = 10;
	
	private static int currentExecutionCount = EXECUTIONS_PER_DAY;
	
	private int curDay = 1;


	/**
     * Default constructor. 
     */
	
	public RunningSimulation() {
        //System.out.println("Starting the Singleton Bean????????????????????????");        
        //initialise
    	//canProgressSegment = false;
    }
	
	public RunningSimulation getInstance() {
		if (RunningSimulation.runningSimulation != null) {
			System.out.println("Call for singleton instance");
			
		} else {
			RunningSimulation.runningSimulation = new RunningSimulation();
			System.out.println("Real Singleton bean creation");
			System.out.println("Construction Call");
		}
		
		
    	//canProgressSegment = false;
		
		return RunningSimulation.runningSimulation;
    	
    }
    
	//main instance
	private static RunningSimulation runningSimulation;
    
    @Resource
	TimerService service;
    
    @EJB
    SimulationFacade simf;
    
	@EJB 
	SecurityFacade secf;
    
	private Timer timer;
	private int	selectedSimulationId;
	private boolean simulationActive;
	private Simulation selectedSimulation;
	private Simulation loadedSimulation;	
	
	private boolean canProgressSegment;
	private long dayPercentDuration;
	private long elaspedPartDuration;
	private long	adjustedDayPartDuration;
	private long currentTimerDuration;
	
	

	//Collections
	public ArrayList<Integer> contractsCountOnDayCollection;
	public ArrayList<Double> openPriceOnDayCollection;
	public ArrayList<Double> highPriceOnDayCollection;
	public ArrayList<Double> lowPriceOnDayCollection;
	public ArrayList<Double> closePriceOnDayCollection;
	public ArrayList<Double> projectedPlotPointsYCollection;

	private long	elaspedPercentDuration;

	

	
	
	//public static Simulation getRunningSimulation() {
    	//return RunningSimulation.runningSimulation;
    //}
	
	public void initMyTimer(int simId) {
    	System.out.println("INIT SINGLETON: -- " + "Init Timer set for: " + simId);
    	
    	selectedSimulationId = simId;
    	System.out.println("INIT SINGLETON: -- " + "SelectedSimulation ID Registered: " + selectedSimulationId);
    	
    	try {
    		System.out.println("INIT SINGLETON: -- " + "SelectedSimulation Registered Correctly: " + selectedSimulationId);
    		selectedSimulation = simf.getSimulationById(selectedSimulationId);
    		System.out.println("Sim is..." + selectedSimulation.getId());
    		dayPercentDuration = selectedSimulation.getDayDuration()/100;
    		adjustedDayPartDuration = selectedSimulation.getDayDuration() / EXECUTIONS_PER_DAY;
    		
    		System.out.println("INIT SINGLETON: -- " + "day duration part set: " + adjustedDayPartDuration);
    		//set the abilty to progress segment
    		setCanProgressSegment(false);
    		//initCollections();
    	}
    	
    	catch(Exception e) {
    		System.out.println("Sim Id retrieval issue... LINE 115 (107?)");
    		e.printStackTrace();
    	}
    	
    }
	/*
	public void initCollections() {
		System.out.println("INIT SINGLETON: -- " + "You need to stop the simulation first");		
		contractsCountOnDayCollection = secf.getContractsCountOnDayCollection(sec.getId());
    	openPriceOnDayCollection = secf.getOpenPriceOnDayCollection(sec.getId());
    	highPriceOnDayCollection = secf.getHighPriceOnDayCollection(sec.getId());
    	lowPriceOnDayCollection = secf.getLowPriceOnDayCollection(sec.getId());
    	closePriceOnDayCollection = secf.getClosePriceOnDayCollection(sec.getId());
    	projectedPlotPointsYCollection = secf.getProjectedPlotPointsYCollection(sec.getId());
	}*/
    
	public void startMyTimer() {
    	System.out.println("INFO SINGLETON: -- " + "Timer about to start");
    	
    		
    		loadedSimulation = selectedSimulation;
    		elaspedPercentDuration = simf.getDayProgress(loadedSimulation.getId()) * loadedSimulation.getDayDuration();
    		elaspedPercentDuration/=100;
    		currentTimerDuration = elaspedPartDuration * this.EXECUTIONS_PER_DAY;
    		System.out.println("INIT SINGLETON: -- " + "elasped day percent duration: " + elaspedPartDuration);
    		curDay = simf.getCurrentDayCountInSimulation(loadedSimulation.getId());
        	timer = service.createTimer(currentTimerDuration, adjustedDayPartDuration, null);
        	System.out.println("SINGLETON: -- " + "Timer started!");
	}
    

 
    @Timeout
    public void incrementDayPercent(Timer timer) {
        
    	
    	
    	//////////////////////////////////System.out.println("INIT SINGLETON: -- " + "SINGLETON: -- " + "A day percent has passed... " + loadedSimulation.getDayProgress());
        
        	////if (currentExecutionCount % EXECUTIONS_PER_DAY == 0) {
        		
        		
        		System.out.println("Adjusted execution rate performing count " + currentExecutionCount);
        		
	        	//modifySecurities
	        	for (Security sec : loadedSimulation.getSecurities()) {
	        		progressSecurityAction(sec);
	        	}
	        	
	        	//Simulation sim = getRunningSimulation();
	        	//perDayPercentSecurityActions();
		        
		        //increment the simulation       
	        	//System.out.println("Error prone:");
	        	simf.incrementDuration(loadedSimulation.getId(), currentTimerDuration);
	        	currentTimerDuration = adjustedDayPartDuration;
	        	//System.out.println("SEG PROGRESS: " + loadedSimulation.getSegmentProgress()  + " - DAY PROGRESS: " + loadedSimulation.getDayProgress());
	        	/////////////////////////////////System.out.println("SEG PROGRESS: " + simf.getSegmentProgress(loadedSimulation.getId()) + " - DAY PROGRESS: " + simf.getDayProgress(loadedSimulation.getId()));
		
		        if (simf.getSegmentProgress(loadedSimulation.getId()) > 99 && simf.getDayProgress(loadedSimulation.getId()) > 95) {
		        	setCanProgressSegment(true);
		        	System.out.println("SIDE NOTE - IN RANGE TO MOVE SEGMENT");
		        }            
		        
		        if (canProgressSegment==true) {
		        	//this is where we would stop the simulation...
		        	System.out.println("WARNING SINGLETON: -- " + "Simulation is being incremented to next segment");
		        	incrementSegment();
		        	
		        } 
		        
		        
		        
	       //// } else {
        	////	System.out.println("-----PAUSE " + currentExecutionCount);
        ////	}
        	
        	currentExecutionCount += 1;
        	
        /*}	else {
        	
	        	System.out.println("WARNING SINGLETON: -- " + "RunningSimulation was NULL and was attempted to be incremented or stopped loaded Simulation needs to be re-initialized");
	        	System.out.println("ERROR SINGLETON: -- " + "RunningSimulation will now be stopped");
	        	this.cancelAllTimers();
	        }*/
        	
    }

    private void progressSecurityAction(Security sec) {
    	int simId = loadedSimulation.getId();
		int secId = sec.getId();
		int day = simf.getCurrentDayCountInSimulation(simId);
		double dayProgress = simf.getDayProgress(simId);
		//double y1 = secf.getProjectedPlotPointsYCollection(sec.getId()).get(day-1);
		//double y2 = secf.getProjectedPlotPointsYCollection(sec.getId()).get(day);
		
		//double slope = (y2-y1) /.1;
		double curValue = (secf.getProjectedPlotPointsYCollection(sec.getId()).get(day));    
		System.out.println("Current security is being modified: day progress is " + dayProgress + "% and the value currently is " + curValue);
		secf.addActualPointDeviated(secId, day, dayProgress,curValue);
		System.out.println("perform sec activities");
		int newDay = simf.getCurrentDayCountInSimulation(loadedSimulation.getId());
		if(newDay > curDay) {
			System.out.println("--------------UPDATING THE CURRENT DAY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			secf.updateSecurityValuesOnNewDay(secId,curValue);
			simf.updateNews(simId);
			curDay = newDay;
			
			//change news
		}
		
		secf.updateSecurityValues(secId, curValue);
		
	
		
		
	}

	public void incrementSegment() {
        System.out.println("INIT SINGLETON: -- " + "SINGLETON: -- " + "A segment has passed... " + simf.getSegmentProgress(loadedSimulation.getId()) + " Segment: " + simf.getCurrentSegmentCountInSimulation(loadedSimulation.getId()));
        //if (loadedSimulation != null) {
        	
        	//simf.incrementDuration(loadedSimulation.getId(), currentTimerDuration);
        	System.out.println("Real Elapsed:" + simf.getSimulationById(loadedSimulation.getId()).getElapsedDurationInMilliseconds());
        	System.out.println("Segemnt Count:" + simf.getCurrentSegmentCountInSimulation(loadedSimulation.getId()));
        	System.out.println("To Set Elapsed:" + simf.getSimulationById(loadedSimulation.getId()).getSegmentDuration()*simf.getSimulationById(loadedSimulation.getId()).getCurrentSegmentCountInSimulation());
        	
        	//set the elapsed time to the count of the segment in the simulation times the duration of each segment plus an additional day
        	simf.setElapsedDuration(loadedSimulation.getId(),simf.getSimulationById(loadedSimulation.getId()).getSegmentDuration()*simf.getCurrentSegmentCountInSimulation(loadedSimulation.getId()) + dayPercentDuration);
        	//Simulation sim = getRunningSimulation();
        	//perDayPercentSecurityActions();
        	this.canProgressSegment = false;
        	
        	//end segment...
            endTimer();
            
            
        	
        	
       // } else {
        	//System.out.println("WARNING SINGLETON: -- " + "RunningSimulation was NULL and was attempted to be stopped");
        //}
        
                                
    }


    public void perDayPercentSecurityActions() {
		//perform duties relevant to the day percent for each security
        
		
		List<Security> securities = loadedSimulation.getSecurities();
        int tempCount = 1;
        for (Security sec : securities) {
        	System.out.println("INIT SINGLETON: -- " + "Security cur count is at.. " + tempCount);
        	tempCount+=1;
        	secf.updateSecurityDayStart(sec.getId());       	
        	
        	
        	////////
        	
        	ArrayList<Integer> contractsCountOnDayCollection = secf.getContractsCountOnDayCollection(sec.getId());
        	ArrayList<Double> openPriceOnDayCollection = secf.getOpenPriceOnDayCollection(sec.getId());
        	ArrayList<Double> highPriceOnDayCollection = secf.getHighPriceOnDayCollection(sec.getId());
        	ArrayList<Double> lowPriceOnDayCollection = secf.getLowPriceOnDayCollection(sec.getId());
        	ArrayList<Double> closePriceOnDayCollection = secf.getClosePriceOnDayCollection(sec.getId());
        	ArrayList<Double> projectedPlotPointsYCollection = secf.getProjectedPlotPointsYCollection(sec.getId());
            
            //for (int i = 0; i < getRunningSimulation().getTotalDays(); i++) {
            //increment sample data
        	int randomIndex = (int)(Math.random()*contractsCountOnDayCollection.size())-1;
        	Double oldValues[] = {
        			Double.parseDouble(contractsCountOnDayCollection.get(randomIndex).toString()),
        			openPriceOnDayCollection.get(randomIndex),
                	highPriceOnDayCollection.get(randomIndex),
                	lowPriceOnDayCollection.get(randomIndex),
                	closePriceOnDayCollection.get(randomIndex),
                	projectedPlotPointsYCollection.get(randomIndex)
        	};
        	
        	System.out.println("INIT SINGLETON: -- " + "Old values: " + Arrays.deepToString(oldValues));
            	
        	//RunningSimulation.secf.setContractsCountOnDayCollection(sec.getId(), contractsCountOnDayCollection);
        }
	}
    
    
    public void endTimer() {
    	System.out.println("SINGLETON: -- " + "STOP TIMER");
    	//TODO End the timer
    	System.out.println("loadedSimulation is now null and can be set");
    	timer = null;
    	cancelAllTimers();
    	
    	//must be called to reset active simulation state
    	////BAD-RunningSimulation.timer=null;        
    }
    
    public void cancelAllTimers() {
    	Collection<Timer> timers = service.getTimers();
    	System.out.println(timers.size() + " Timers found...");
    	for(Timer timer : timers) {
    		timer.cancel();
    		System.out.println("Cancelled all timers");
    	}
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

	
	public boolean isSimulationActive() {
		
		if (loadedSimulation!=null) {
			return true;
		} else {
			return false;
		}
	}
		
	public boolean isCanProgressSegment() {
		return this.canProgressSegment;
	}

	
	public void setCanProgressSegment(boolean canProgressSegment) {
		this.canProgressSegment = canProgressSegment;
	}

}

















