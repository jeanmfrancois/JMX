package jmse.ejb.facade;

import java.util.ArrayList;

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
}
