package jmse.ejb.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import jmse.ejb.business.CreateRunningSimulationBean;
import jmse.persistence.model.SecuritySet;
import jmse.persistence.model.Segment;
import jmse.persistence.model.Simulation;
import jmse.persistence.model.Sponsor;
import jmse.persistence.model.Team;

/**
 * Session Bean implementation class SimulationFacade
 */
@Stateless
@Named
public class SimulationFacade {

	@PersistenceContext(unitName="em")
    EntityManager em;
	
	@EJB
	private CreateRunningSimulationBean createRunningSimulationBean;
	
    /**
     * Default constructor. 
     */
	
    public SimulationFacade() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    
   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public List<Simulation> getAllSimulations() {
    	ArrayList<Simulation> simulations = new ArrayList<Simulation>();
    	Query q = em.createNamedQuery("Simulation.findAll");
    	for (Object simulation :q.getResultList()) {
    		simulations.add((Simulation)simulation);
    	}
    	
    	//Simulation newSim = createSimulation(1, 1, "My First Simulation", 240, 14400, false, "1st Simulation", 240, 4, 0, 0, 0, null, null, null, null);
    	return simulations;
   }
    
   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Simulation createSimulation(String name, String description, int duration, int totalDays, int totalSegments) {
	   	   
	    Simulation simulation = createSimulation(1, 1, description, duration, duration*60, false, name, totalDays, totalSegments, 0, 0, 0, null, null, null, null);
    	
    	em.persist(simulation);
    	//em.flush();
    	System.out.println("PERSISTED!");
    	return simulation;
    	
    	
    }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public Simulation getSimulationsById(int id) {
    	Query q = em.createNamedQuery("Simulation.findById");
        q.setParameter("id", id);
        return (Simulation) q.getSingleResult();
    }
   
   public void createRunningSimulation(int simulationId){
	   createRunningSimulationBean.startTimer(simulationId);
	   System.out.println("Started timer? " + simulationId);
	   System.out.println("Status - " + createRunningSimulationBean.checkTimerStatus());
   }

    


	private List<Segment> initSegments(int totalSegments, Simulation simulation) {
		List<Segment> segments = new ArrayList<Segment> ();
		
		for (int i = 1; i <= totalSegments; i++) {
			segments.add(new Segment(0,null,simulation));
		}
	// TODO Auto-generated method stub
	return null;
}




	/**
	 * @param id
	 * @param curDayId
	 * @param curSegmentId
	 * @param description
	 * @param durationInMinutes
	 * @param durationInSeconds
	 * @param isActive
	 * @param name
	 * @param totalDays
	 * @param totalSegments
	 * @param totalSimulationTrades
	 * @param totalTeams
	 * @param totalUsers
	 * @param segments
	 * @param securitySet
	 * @param sponsors
	 * @param teams
	 */
	public Simulation createSimulation(Integer curDayId, Integer curSegmentId,
			String description, Integer durationInMinutes,
			Integer durationInSeconds, Boolean isActive, String name,
			Integer totalDays, Integer totalSegments,
			Integer totalSimulationTrades, Integer totalTeams,
			Integer totalUsers, List<Segment> segments,
			SecuritySet securitySet, List<Sponsor> sponsors, List<Team> teams) {
		
		Simulation simulation = new Simulation();
		simulation.setCurDayId(curDayId);
		simulation.setCurSegmentId(curSegmentId);
		simulation.setDescription(description);
		simulation.setDurationInMinutes(durationInMinutes);
		simulation.setDurationInSeconds(durationInSeconds);
		simulation.setIsActive(isActive);
		simulation.setName(name);
		simulation.setTotalDays(totalDays);
		simulation.setTotalSegments(totalSegments);
		simulation.setTotalSimulationTrades(totalSimulationTrades);
		simulation.setTotalTeams(totalTeams);
		simulation.setTotalUsers(totalUsers);
		simulation.setSegments(segments);
		simulation.setSecuritySet(securitySet);
		simulation.setSponsors(sponsors);
		simulation.setTeams(teams);
		return simulation;
	}
	
	
	public int getDayProgress(int id) {
		System.out.println("GET DAY PROGRESS... F");
		Simulation simulation = this.getSimulationsById(id);
		
		int dayProgress = simulation.getDayProgress();
		System.out.println("GET DAY PROGRESS... AMOUNT " + dayProgress);
		return dayProgress;
	}

	public int getSegmentProgress(int id) {
		Simulation simulation = this.getSimulationsById(id);
		return simulation.getSegmentProgress();
	}
	
	public int getSimulationProgress(int id) {
		Simulation simulation = this.getSimulationsById(id);
		return simulation.getSimulationProgress();
	} 
	
	public int getCurrentDayCountInSegment(int id) {
		Simulation simulation = this.getSimulationsById(id);
		return simulation.getCurrentDayCountInSegment();
	}

	public int getCurrentSegmentCountInSimulation(int id) {
		Simulation simulation = this.getSimulationsById(id);
		return simulation.getCurrentSegmentCountInSimulation();
	}
	
	public int getCurrentDayCountInSimulation(int id) {
		Simulation simulation = this.getSimulationsById(id);
		return simulation.getCurrentDayCountInSimulation();
	} 
	


}
