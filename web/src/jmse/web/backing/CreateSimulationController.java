package jmse.web.backing;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import jmse.ejb.business.MenuBarBean;
import jmse.ejb.facade.SimulationFacade;
import jmse.persistence.model.Simulation;


@ManagedBean
@SessionScoped
public class CreateSimulationController implements Serializable{
	
		/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

		private int daysPerSegment;
	    
		@Size(min=1,max=25,message="Simulation name must be present and no longer than 25 characters.")
	    private String simName;
		
		@Size(min=1,max=100,message="Desciption must be present and no longer than 100 characters.")
	    private String simDescription;
		
		private int totalDays;
		
		@Min(value=1, message="Total number of divisions must be entered.") @Max(value=10, message="Can not have more than 10 divisions.")
	    private int totalSegments;
		
		@Min(value=1, message="Total simulation duration must be entered.") @Max(value=6000, message="Can not be longer than 6000 minutes or 10 hours.")
	    private int durationInMinutes;
	    
		@EJB
	    private SimulationFacade simf;		
	    
	    public CreateSimulationController() {
	    	totalSegments = 4;
	    	totalDays = 240;
	    	durationInMinutes = 120;
	    }
	    
	    public String getSimName() {
			return simName;
		}

		public void setSimName(String simName) {
			this.simName = simName;
		}

		public String getSimDescription() {
			return simDescription;
		}

		public void setSimDescription(String simDescription) {
			this.simDescription = simDescription;
		}

		public int getTotalDays() {
			return totalDays;
		}

		public void setTotalDays(int totalDays) {
			this.totalDays = totalDays;
		}

		public int getTotalSegments() {
			return totalSegments;
		}

		public void setTotalSegments(int totalSegments) {
			this.totalSegments = totalSegments;
		}		
	    
	    public int getDurationInMinutes() {
			return durationInMinutes;
		}

		public void setDurationInMinutes(int durationInMinutes) {
			this.durationInMinutes = durationInMinutes;
		}

		public int getDaysPerSegment() {
			return daysPerSegment;
		}

		public void setDaysPerSegment(int daysPerSegment) {
			this.daysPerSegment = daysPerSegment;
		}

		public void createSimulation() {
	    	Simulation simulation = simf.createSimulation(simName,simDescription, durationInMinutes, totalDays, totalSegments);
	    	//menuBarBean.setLoadedSimulation(simulation);
	    	FacesContext context = FacesContext.getCurrentInstance();  	          
	        context.addMessage(null, new FacesMessage("Successfully Created " + simulation.getName(), "Use Run Commmand to Start Simulation"));
	    }
	    
	    
	} 

/*********** Validator **********************/
