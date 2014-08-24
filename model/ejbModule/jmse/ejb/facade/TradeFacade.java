package jmse.ejb.facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jmse.persistence.model.Day;
import jmse.persistence.model.Simulation;
import jmse.persistence.model.Trade;

/**
 * Session Bean implementation class TradeFacade
 */
@Stateless
@Named
/*
 * Not needed as default exposes this interface when none is declared RemoteBean or Local Bean
   @LocalBean

*/

	public class TradeFacade {
	
	@PersistenceContext(unitName="em")
    private EntityManager em;
	
	@EJB
	DayFacade dayf;
	
	@EJB
	SimulationFacade simf;

    /**
     * Default constructor. 
     */
    public TradeFacade() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    
    /*@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    ArrayList<Trade> getAllTrades() {
    	ArrayList<Trade> trades = new ArrayList<Trade>();
    	Query q = em.createNamedQuery("Trade.findAll");
    	for (Object trade :q.getResultList()) {
    		trades.add((Trade)trade);
    	}
    	return trades;
    }*/
    
    public void createTrade() {
    	System.out.println("test 202");
    	Trade trade = new Trade();
    	//trade.setId(1);
    	trade.setQuantity(10001);
    	
    	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("em");    
    	//EntityManager em = emf.createEntityManager();
    	
    	
    	em.persist(trade);
    	em.flush();
    	System.out.println("Executed!");
    }
    
    
    
    //Add transaction annotation here after creation validation
    public Trade createTrade(int secId, int buyerUserId, int sellerUserId, Integer quantity, Double pricePerShare, Double totalTradeCost) {//, Simulation sim) {
    	
    	Trade curTrade = new Trade();
    	
    	/*int dayCount = simf.getCurrentDayCountInSimulation(sim.getId());
    	int dayIndex = dayCount-1;
    	sim.getSegments();*/
    	
    	//System.out.println("Current Sim:" + simf.getCurrentDayCountInSimulation(sim.getId()));
    	
    	
    	
    	
    	em.persist(curTrade);
    	
    	curTrade.setSecurityValueId(secId);
    	curTrade.setSellerUserId(sellerUserId);
    	curTrade.setBuyerUserId(buyerUserId);
    	curTrade.setQuantity(quantity);
    	curTrade.setPricePerShare(pricePerShare);
    	curTrade.setTotalTradeCost(totalTradeCost);
    	
    	//create a timestamp
    	System.out.println("Value of new date..");
    	Calendar calendar = Calendar.getInstance();
    	Date now = calendar.getTime();
    	java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(now.getTime());
    	Date curTimestamp = currentTimeStamp;
    	System.out.println(curTimestamp.toString() + " a trade was created...");
    	
    	curTrade.setCreationDate(curTimestamp);
    	
    	return curTrade; 	
    	
    	
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrayList<Trade> getAllTrades() {
     	ArrayList<Trade> trades = new ArrayList<Trade>();
     	Query q = em.createNamedQuery("Trade.findAll");
     	for (Object trade :q.getResultList()) {
     		trades.add((Trade)trade);
     	}
     	
     	//Simulation newSim = createSimulation(1, 1, "My First Simulation", 240, 14400, false, "1st Simulation", 240, 4, 0, 0, 0, null, null, null, null);
     	return trades;
    }
    
    
    
    
    public static void main(String[] args) {
    	System.out.println("Test of date");
    	
    	System.out.println("Value of new date..");
    	Calendar calendar = Calendar.getInstance();
    	Date now = calendar.getTime();
    	java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(now.getTime());
    	Date date = currentTimeStamp;
    	System.out.println(date.toString());
    	
    }
}
