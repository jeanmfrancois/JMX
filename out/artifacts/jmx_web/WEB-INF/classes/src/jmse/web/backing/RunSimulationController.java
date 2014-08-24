package jmse.web.backing;

import jmse.ejb.business.RunningSimulation;
import jmse.ejb.facade.SimulationFacade;
import jmse.persistence.model.Simulation;
import jmse.utils.Params;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Session Bean implementation class RunSimulationController
 */
@ManagedBean
@RequestScoped
public class RunSimulationController implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private int selectedSimulationId;
	private Simulation selectedSim;
	private Integer dayProgress; 
	private Integer segmentProgress; 
	private Integer simulationProgress; 
	private String selectedSimulationInfo;
	private boolean runDisabled;
	private boolean stopDisabled;
	private int curDay;
	private int curSegment;
	private int curSimulationDay;
	
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Map<String,String> params;
	
	
	@EJB
	SimulationFacade simf;
	
	@EJB
	RunningSimulation runningSim;
	

    /**
     * Default constructor. 
     */
    public RunSimulationController() {
    	// TODO initialisation goes in initPage method
    	initHere();
    	
    }    
    
    public void initHere() {
    	
    	
    }
    
    public void initPage() {
    	if (selectedSim == null) {
	    	initParams();
	    	initVars();
	    	initSimulation();
    	} else {
    		System.out.println("WARNING -- retrieval of Simulation when it was previously initialized");
    	}
    }
    
    public void initParams() {    	
    		setSelectedSimulationId(Params.initIntParameter("simId"));	   
    	
    }
    
    public void initVars() {

    }
    
    public void initSimulation() {
    	try {
    		if (selectedSimulationId>0) {
    			System.out.println("Simulation ID is.." + getSelectedSimulationId());
    			  this.selectedSim = simf.getSimulationById(getSelectedSimulationId());
    			  if (selectedSim == null) {
    				  System.out.println("Sim is null");
    			  }
    			  setSelectedSimulationInfo();
    			  
    			  runningSim.initMyTimer(selectedSimulationId);
    		} else {
    			System.out.println("Simulation ID is 0");
    			setSelectedSimulationInfo();
    		}
    		

        	
        	
    	}
    	catch(Exception e) {
    		System.out.println("Error initialising page");
    	}    		
    }
    
    public void onComplete() {  
    	System.out.println("OnComplete Execute and will stop Timer... (But its not my job!) - and completion on what??? I run for every day segment and SIM!");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Completed"));  
        runningSim.endTimer();
    }  
      
    public void cancel() {  
    	System.out.println("CANCEL");
    	dayProgress = null;  
    	segmentProgress = null;  
    	simulationProgress = null;  
    }      
    
    public void runSimulation() {
    	System.out.println("RUN EXECUTED");
    	//////simf.createRunningSimulation(this.selectedSimulationId);
    	////////enableStopButton();
    	runningSim.startMyTimer();
    	
    }
    
    public void stopSimulation() {
    	System.out.println("STOP BUTTON PUSHED");
    	selectedSim = null;
    	runningSim.endTimer();
    	
    	////////enableRunButton();
    }
    
    
    public void enableStopButton() {
    	System.out.println("You can run and never stop!");
    	this.setRunDisabled(true);
    	this.setStopDisabled(false);
    }
    
    public void enableRunButton() {
    	System.out.println("Just stop, you can never run from me!");
    	this.setRunDisabled(false);
    	this.setStopDisabled(true);
    }
    
    public Integer getDayProgress() {  
    	
    	if(selectedSim != null) {
    		////System.out.println("TODO");
    		dayProgress = simf.getSimulationById(selectedSimulationId).getDayProgress();  
        } else {
        	System.out.println("No selected Sim - can NOT set day progress...");
        }
          
        return dayProgress;  
    }  
  
    public void setDayProgress(Integer progress) {  
        this.dayProgress = progress;  
    }  
      
    
	
	public Integer getSegmentProgress() {
		if(selectedSim != null) {
    		/////System.out.println("TODO");
    		segmentProgress = simf.getSimulationById(selectedSimulationId).getSegmentProgress(); 
        } 
          
        return segmentProgress;   
	}

	
	public void setSegmentProgress(Integer progress) {
		this.segmentProgress = progress;
	}

	
	public Integer getSimulationProgress() {
		if(selectedSim != null) {
    		/////System.out.println("TODO");
    		simulationProgress = simf.getSimulationById(selectedSimulationId).getSimulationProgress();
        } 
          
        return simulationProgress;  
	}

	
	public void setSimulationProgress(Integer progress) {
		this.simulationProgress = progress;
	}

	public int getSelectedSimulationId() {
		return selectedSimulationId;
	}
	
	public void setSelectedSimulationId(int selectedSimulationId) {
		this.selectedSimulationId = selectedSimulationId;
	}
	
	public Simulation getselectedSim() {
		return selectedSim;
	}
	
	
	public boolean isRunDisabled() {
		return runDisabled;
	}

	
	public void setRunDisabled(boolean runDisabled) {
		this.runDisabled = runDisabled;
	}

	
	public boolean isStopDisabled() {
		return stopDisabled;
	}

	
	public void setStopDisabled(boolean stopDisabled) {
		this.stopDisabled = stopDisabled;
	}

	public void setselectedSim(Simulation selectedSim) {
		System.out.println("Selected a simulation title: " + selectedSim.getName());
		
		this.selectedSim = selectedSim;
	}


	public List<Simulation> getSimulations() {
		return simf.getAllSimulations();
	}
	
	public void setSimulations(List<Simulation> simulations) {
		
	}

	
	public String setSelectedSimulationInfo() {
		////System.out.println("Setting Simulation Info");
		
		if(selectedSimulationId != 0) {
			selectedSimulationInfo = selectedSim.getName() + " is currently selected.";			
		} else {
			selectedSimulationInfo = "No simulation has been selected yet.";
		}
		return selectedSimulationInfo;
	}

	
	public String getSelectedSimulationInfo() {
		return selectedSimulationInfo;
	}
	
	
	public int getCurDay() {
		if (selectedSimulationId != 0) {
			this.curDay = simf.getCurrentDayCountInSegment(selectedSimulationId);
		}
		return curDay;
	}

	
	public void setCurDay(int curDay) {
		////System.out.println("Setting Day in Segment!");
		this.curDay = simf.getCurrentDayCountInSegment(selectedSimulationId);
	}

	
	public int getCurSegment() {
		if (selectedSimulationId != 0) {
			this.curSegment = simf.getCurrentSegmentCountInSimulation(selectedSimulationId);
		}
		return curSegment;
	}

	
	public void setCurSegment(int curSegment) {
		////System.out.println("Setting Segment in Simulation!");
		this.curSegment =  simf.getCurrentSegmentCountInSimulation(selectedSimulationId);;
	}

	
	public int getCurSimulationDay() {
		if (selectedSimulationId != 0) {
			this.curSimulationDay = simf.getCurrentDayCountInSimulation(selectedSimulationId);
		}
		return curSimulationDay;
	}

	
	public void setCurSimulationDay(int curSimulationDay) {
		////System.out.println("Setting Day in Simulation!");
		this.curSimulationDay = simf.getCurrentDayCountInSimulation(selectedSimulationId);
	}

	
	
	/*
	public void preRenderComponentEvent() {
		System.out.println("PRE - Comp");
	}
	
	public void preRenderViewEvent() {
		System.out.println("PRE - View");
		
	} 
	
	public void postAddToViewEvent() {
		System.out.println("Post - View");

    	initPage();
	}*/
}
