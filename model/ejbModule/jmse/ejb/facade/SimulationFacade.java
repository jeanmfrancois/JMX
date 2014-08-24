package jmse.ejb.facade;

import jmse.persistence.model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Session Bean implementation class SimulationFacade
 */
@Stateless
@Named
public class SimulationFacade {

	@PersistenceContext(unitName="em")
    EntityManager em;
	
	@EJB
	private SegmentFacade segf;
	
	@EJB
	private DayFacade dayf;
	
	@EJB
	private SecurityFacade secf;
	
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
    public Simulation createSimulation(String name, String description, Long durationMinutes, Integer totalDays, Integer totalSegments, String initNewsItem) {
	   	   
	   	//create simulation with given properties	   
	    Simulation simulation = new Simulation();
		simulation.setDescription(description);
		simulation.setDurationInMinutes(durationMinutes);
		simulation.setDurationInSeconds(durationMinutes*60);
		simulation.setDurationInMilliseconds(durationMinutes*60*1000);
		simulation.setElapsedDurationInMilliseconds(0L);
		simulation.setIsActive(false);
		simulation.setName(name);
		simulation.setTotalDays(totalDays);
		simulation.setTotalSegments(totalSegments);	
		simulation.setCurNewsItem(initNewsItem);

		//persist simulation to invoke id generation
	    em.persist(simulation);

	    Segment curSegment;
	    Day curDay;	    
	    int daysPerSegment = totalDays / totalSegments;
	    
	     //add the segments to the simulation
	    for (int i = 0; i < totalSegments; i++) {
	    	
	    	//create a new segment
	    	curSegment = segf.createSegment(i);
	    	
	    	//persist segment to invoke id generation
		    em.persist(curSegment);
		    
		    System.out.println("seg Id - " + curSegment.getId());
		    
		    //add segment to simulation
		    simulation.addSegment(curSegment);
		    
		    
		    
		    
		    
		    for (int j = 0; j < daysPerSegment; j++) {
		    	
		    	//create new day
		    	curDay = dayf.createDay(j);		    	

		    	//persist day to invoke id generation
		    	em.persist(curDay);		
		    	
		    	//add day to segment
		    	curSegment.addDay(curDay);
		    	
		    	//sample news ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    	NewsItem newsItem = new NewsItem();		    	
		    	em.persist(newsItem);
		    	System.out.println("get id for news:" + newsItem.getId());
		    	
		    	newsItem.setDay(curDay);
		    	curDay.setNewsItem(newsItem);
		    	
		    	System.out.println("get id for the day of news:" + newsItem.getDay().getId());
		    	
		    	newsItem.setHeadline("Working Hard " + newsItem.getDay().getId());
		    	
		    	
		    	
		    			    	
		    	//sample trade data...	  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    				    			    	
		    	//Trade sampleTrade = new Trade();
		    	//em.persist(sampleTrade);
		    	//curDay.addTrade(sampleTrade);	 
		    	//sampleTrade.setDay(curDay);
		    	//System.out.println("get id for trade:" + sampleTrade.getId());
		    	/*
				*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    	
		    	
		    	
		    }
	    }
	    
	    //add a couple securities/////////////////////////////////////////////////////////////////////////////////
	    
	    
	    //new Security(10.0,0.0,"Gold Metal", "Gold", "AU", simulation);
	    Security sec1 = secf.createSecurity(simulation, "Molson Consumer Discretionary Index", "The Molson Consumer Discretionary Index is designed to measure the overall performance of common stocks of North American companies in the Consumer Discretionary sector. The sector encompasses those industries that tend to be the most sensitive to economic cycles. Its manufacturing segment includes automotive, household durable goods, textiles and apparel, and leisure equipment. The services segment includes hotels, restaurants and other leisure facilities, media production and services, and consumer retailing and services.", "MCD", "333399", simulation.getTotalDays(), 2000.0, 1300.0);
	       		
	    //new Security(5.5,0.0,"Silver Metal", "Silver", "SL", simulation);		
	    Security sec2 = secf.createSecurity(simulation, "Molson Energy Commodities Index", "The Molson Energy Commdities Index reflects the total return available through an investment basket of energy commodities such as crude oil, Brent crude, heating oil, natural gas and coal. The Index does not track nuclear power or renewable resources. Geopolitical events and natural disasters can create supply disruptions and have a noticeable impact on worldwide energy markets. Weather events can significantly affect energy demand by increasing heating or air conditioning use.", "ENR", "CC6633", simulation.getTotalDays(),1750.0, 2300.00);
	    
	    em.persist(sec1);
	    em.persist(sec2);
	    
	    System.out.println("get id for sec1:" + sec1.getId());
	    System.out.println("get id for sec2:" + sec2.getId());
	    
	    simulation.addSecurity(sec1);
	    simulation.addSecurity(sec2);
	    
	    
	    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    /*Removal Test

	    System.out.println("get ...");
	    Simulation simout = this.getSimulationById(140);
    	System.out.println("will merge sim ...");
    	System.out.println("and now remove...");
    	em.remove(simout);*/
	    
	    
	    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    
    	return simulation;    	
    	
    }
   
   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public Simulation getSimulationById(int id) {
	    //System.out.println("get simulation in facade..");
    	Query q = em.createNamedQuery("Simulation.findById");
        q.setParameter("id", id);
        Simulation sim = (Simulation) q.getSingleResult();
       // System.out.println("got.." + sim.getName());
        return sim;
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
   /*
	public Simulation createSimulation(String name, String description, Long durationInMinutes,	Integer totalDays, Integer totalSegments) {
		
		Simulation simulation = new Simulation();
		//simulation.setCurDayId(curDayId);
		//simulation.setCurSegmentId(curSegmentId);
		simulation.setDescription(description);
		simulation.setDurationInMinutes(durationInMinutes);
		simulation.setDurationInSeconds(durationInMinutes*60);
		simulation.setDurationInMilliseconds(durationInMinutes*60*1000);
		simulation.setElapsedDurationInMilliseconds(0L);
		simulation.setIsActive(false);
		simulation.setName(name);
		simulation.setTotalDays(totalDays);
		simulation.setTotalSegments(totalSegments);
		return simulation;
	}*/
	
	
	public int getDayProgress(int id) {
		////System.out.println("GET DAY PROGRESS... F");
		Simulation simulation = this.getSimulationById(id);
		
		int dayProgress = simulation.getDayProgress();
		////System.out.println("GET DAY PROGRESS... AMOUNT " + dayProgress);
		return dayProgress;
	}

	public int getSegmentProgress(int id) {
		Simulation simulation = this.getSimulationById(id);
		return simulation.getSegmentProgress();
	}
	
	public int getSimulationProgress(int id) {
		Simulation simulation = this.getSimulationById(id);
		return simulation.getSimulationProgress();
	} 
	
	public int getCurrentDayCountInSegment(int id) {
		Simulation simulation = this.getSimulationById(id);
		return simulation.getCurrentDayCountInSegment();
	}

	public int getCurrentSegmentCountInSimulation(int id) {
		Simulation simulation = this.getSimulationById(id);
		return simulation.getCurrentSegmentCountInSimulation();
	}
	
	public int getCurrentDayCountInSimulation(int id) {
		Simulation simulation = this.getSimulationById(id);
		return simulation.getCurrentDayCountInSimulation();
	}



	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incrementDuration(int selectedSimulationId, long incrementalDuration) {
		// TODO Auto-generated method stub to increment simulation
		
		Simulation simulation = this.getSimulationById(selectedSimulationId);
		simulation.incrementSimulation(incrementalDuration);
		em.persist(simulation);
		
		
	} 
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void setElapsedDuration(int selectedSimulationId, long elapsedDurationInMilliseconds) {
		// TODO Auto-generated method stub to increment simulation
		
		Simulation simulation = this.getSimulationById(selectedSimulationId);
		simulation.setElapsedDurationInMilliseconds(elapsedDurationInMilliseconds);
		em.persist(simulation);
		
		
	}




	public void updateNews(int simId) {
		// TODO Auto-generated method stub
		System.out.println("Current News!");
		getSimulationById(simId).setCurNewsItem("we just rock!" + Math.random()*100 + "!!!!!");
		
	} 
	


}
