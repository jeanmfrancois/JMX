package jmse.web.backing;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import jmse.ejb.facade.SimulationFacade;
import jmse.persistence.model.Simulation;

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
	private Simulation selectedSimulation;
	private Integer dayProgress; 
	private Integer segmentProgress; 
	private Integer simulationProgress; 
	private String selectedSimulationInfo;
	private boolean cantRun;
	private boolean cantStop;
	private int curDay;
	private int curSegment;
	private int curSimulationDay;
	private int	simId;
	
	
	@EJB
	SimulationFacade simf;

	

    /**
     * Default constructor. 
     */
    public RunSimulationController() {
    	// TODO Auto-generated constructor stub
    	this.setCantRun(true);
    	this.setCantStop(true);
    	this.setDayProgress(0);
    	this.setSegmentProgress(0);
    	this.setSimulationProgress(0);
    	this.setCurDay(0);
    	this.setCurSegment(0);
    	this.setCurSimulationDay(0);    	
    	this.simId=0;
    }    
    
    //get value from "f:param for simId"
  	public String getSimParam(FacesContext fc){
   
  		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
  		return params.get("simId");
   
  	}
    
    public void onComplete() {  
    	System.out.println("OnComplete Execute");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "Completed"));  
        enableRun();
    }  
      
    public void cancel() {  
    	System.out.println("CANCEL");
    	dayProgress = null;  
    	segmentProgress = null;  
    	simulationProgress = null;  
    }      
    
    public void enableRun() {
    	System.out.println("Ajax Enable Run and Disable Stop");
    	this.setCantRun(false);
    	this.setCantStop(true);
    }
    
    public void enableStop() {
    	System.out.println("Ajax Disable Run and Enable Stop");
    	this.setCantRun(true);
    	this.setCantStop(false);
    }
    
    public void runSimulation() {
    	System.out.println("RUN EXECUTED");
    	//////simf.createRunningSimulation(this.selectedSimulationId);
    	enableStop();
    }
    
    public void stopSimulation() {
    	System.out.println("STOP BUTTON PUSHED");
    	enableRun();
    }
    
    public Integer getDayProgress() {  
    	System.out.println("pba GET DAY PROGRESS... C");
    	
    	if(selectedSimulationId != 0) {
    		System.out.println("TODO");
    		dayProgress = simf.getDayProgress(selectedSimulationId);  
        } else {
        	System.out.println("NOT TODO");
        }
          
        return dayProgress;  
    }  
  
    public void setDayProgress(Integer progress) {  
        this.dayProgress = progress;  
    }  
      
    
	
	public Integer getSegmentProgress() {
		
		System.out.println("pbb GET DAY PROGRESS... C");
    	
    	if(selectedSimulationId != 0) {
    		segmentProgress = simf.getSegmentProgress(selectedSimulationId);  
        } 
          
        return segmentProgress;   
	}

	
	public void setSegmentProgress(Integer progress) {
		this.segmentProgress = progress;
	}

	
	public Integer getSimulationProgress() {
		System.out.println("pba GET DAY PROGRESS... C");
		
    	if(selectedSimulationId != 0) {
    		simulationProgress = simf.getSimulationProgress(selectedSimulationId);  
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
	
	public Simulation getSelectedSimulation() {
		return selectedSimulation;
	}
	
	public void setSelectedSimulation(Simulation selectedSimulation) {
		System.out.println("Selected a sim " + selectedSimulation.getName());
		
		this.selectedSimulation = selectedSimulation;
		this.selectedSimulationId = selectedSimulation.getId();
	}

	public List<Simulation> getSimulations() {
		return simf.getAllSimulations();
	}
	
	public void setSimulations(List<Simulation> simulations) {
		
	}

	
	public String getSelectedSimulationInfo() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		simId = Integer.parseInt(getSimParam(fc));
		
		if(selectedSimulationId != 0) {
			selectedSimulationInfo = simf.getSimulationsById((selectedSimulationId)).getName() + " is currently selected.";			
		} else if (simId != 0) {
			selectedSimulationInfo = simf.getSimulationsById((simId)).getName() + " is currently selected. Passed? : " + simId;
		} else {
			selectedSimulationInfo = "No simulation has been selected yet.";
		}
		return selectedSimulationInfo;
	}

	
	public void setSelectedSimulationInfo(String selectedSimulationInfo) {
		this.selectedSimulationInfo = selectedSimulationInfo;
	}
	
	
	public int getCurDay() {
		if (selectedSimulationId != 0) {
			this.curDay = simf.getCurrentDayCountInSegment(selectedSimulationId);
		}
		return curDay;
	}

	
	public void setCurDay(int curDay) {
		System.out.println("Setting Day in Segment!");
		this.curDay = curDay;
	}

	
	public int getCurSegment() {
		if (selectedSimulationId != 0) {
			this.curSegment = simf.getCurrentSegmentCountInSimulation(selectedSimulationId);
		}
		return curSegment;
	}

	
	public void setCurSegment(int curSegment) {
		System.out.println("Setting Segment in Simulation!");
		this.curSegment = curSegment;
	}

	
	public int getCurSimulationDay() {
		if (selectedSimulationId != 0) {
			this.curSimulationDay = simf.getCurrentDayCountInSimulation(selectedSimulationId);
		}
		return curSimulationDay;
	}

	
	public void setCurSimulationDay(int curSimulationDay) {
		System.out.println("Setting Day in Simulation!");
		this.curSimulationDay = curSimulationDay;
	}

	public boolean isCantRun() {
		return cantRun;
	}

	
	public void setCantRun(boolean cantRun) {
		this.cantRun = cantRun;
	}

	
	public boolean isCantStop() {
		return cantStop;
	}

	
	public void setCantStop(boolean cantStop) {
		this.cantStop = cantStop;
	}
	
	

}
