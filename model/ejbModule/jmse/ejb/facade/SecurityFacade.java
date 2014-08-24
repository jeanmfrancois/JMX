package jmse.ejb.facade;

import java.io.Serializable;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jmse.persistence.model.Security;
import jmse.persistence.model.Simulation;
import jmse.utils.Conversion;
import jmse.utils.Graphing;
/**
 * Session Bean implementation class SecurityFacade
 */
@Stateless
@Named
public class SecurityFacade implements Serializable {
	
	
	@PersistenceContext(unitName="em")
    EntityManager em;
	
	@EJB
	SimulationFacade simf;

	private final double	MIN_PRICE_PER_DAY_DEFAULT = 0.0;

	private final double	MAX_PRICE_PER_DAY_DEFAULT = 1000.0;

	private final Integer	MIN_CONTRACTS_PER_DAY_DEFAULT = 0;

	private final Integer	MAX_CONTRACTS_PER_DAY_DEFAULT = 500;
	
	private final double	DEVIATION_DEFAULT = 50;
	
	/*
	private Array closePriceOnDayCollection;
	private Array contractsCountOnDayCollection;
	private Array highPriceOnDayCollection;
	private Array lowPriceOnDayCollection;
	private Array openPriceOnDayCollection;
	private Array projectedPlotPointsY;*/
	
	

    /**
     * Default constructor. 
     */
    public SecurityFacade() {
        // TODO Auto-generated constructor stub
    }
    
       

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Security getSecurityById(int id) {
     	Query q = em.createNamedQuery("Security.findById");
         q.setParameter("id", id);
         Security sec = (Security) q.getSingleResult();
         return sec;
     }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateSecurityValuesOnNewDay(int secId, double curPrice) {
     	Security sec = getSecurityById(secId);
     	em.merge(sec);
     	sec.setPrevPrice(sec.getCurPrice());
     	sec.setCurPrice(curPrice);
     	sec.setDiffPricePerDay(curPrice-sec.getPrevPrice());
     	em.persist(sec);     	
     }
    
    public void updateSecurityValues(int id, double curPrice) {
     	Security sec = getSecurityById( id);
     	em.merge(sec);
     	sec.setCurPrice(curPrice);
     	em.persist(sec);
     }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateSecurityDayStart(int id) {
    	
    	Security sec = this.getSecurityById(id);
    	System.out.println("Updating security:" + sec.getName());
    	sec.setCurPrice(sec.getCurPrice() + 10.0);
    	//sec.getOpenPriceOnDayCollection().
    	em.persist(sec);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateSecurityDayPercent(int id) {
    	Security sec = this.getSecurityById(id);
    	//sec.setCurPrice(sec.getCurPrice() + 10.0);
    	em.persist(sec);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateSecurityDayEnd(int id) {
    	Security sec = this.getSecurityById(id);
    	//sec.setCurPrice(sec.getCurPrice() + 10.0);
    	em.persist(sec);
    }
    
    
    
    
    //@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Security createSecurity(Simulation simulation, String name, String description, String symbol, String color, int totalDays, double startPrice, double endPrice) {
    	
    	//set supplied values
    	Security sec = new Security();
    	sec.setName(name);
    	sec.setDescription(description);
    	sec.setSymbol(symbol);
    	sec.setColor(color);

    	//initialise arrays 

    	ArrayList<Double> actualPriceXCollection = new ArrayList<Double>();
    	ArrayList<Double> actualPriceYCollection = new ArrayList<Double>();
    	ArrayList<Double> closePriceOnDayCollection = new ArrayList<Double>();
    	ArrayList<Double> openPriceOnDayCollection = new ArrayList<Double>();
    	ArrayList<Double> highPriceOnDayCollection = new ArrayList<Double>();
    	ArrayList<Double> lowPriceOnDayCollection = new ArrayList<Double>();
    	ArrayList<Double> avgPriceOnDayCollection = new ArrayList<Double>();
    	ArrayList<Double> projectedPlotPointsYCollection = new ArrayList<Double>();
    	ArrayList<Integer> contractsCountOnDayCollection = new ArrayList<Integer>();
    	
    	
    	//set values for arrays
    	for (int i = 0; i < totalDays; i++) {
    		closePriceOnDayCollection.add(0.0);
    		avgPriceOnDayCollection.add(0.0);
    		openPriceOnDayCollection.add(0.0);
        	highPriceOnDayCollection.add(0.0);
        	lowPriceOnDayCollection.add(0.0);
        	projectedPlotPointsYCollection.add(0.0);
        	contractsCountOnDayCollection.add(0);        	
    	}
    	
    	//start the current price
    	actualPriceXCollection.add(0.0);
    	actualPriceYCollection.add(startPrice);
    	
    	//add specific values for open and close actual points
    	openPriceOnDayCollection.set(0, startPrice);
    	openPriceOnDayCollection.set(openPriceOnDayCollection.size()-1, endPrice);  
    	
    	//add specific values for open and close projected points
    	projectedPlotPointsYCollection.set(0, startPrice);
    	projectedPlotPointsYCollection.set(openPriceOnDayCollection.size()-1, endPrice); 
    	
    	
    	Double[][] expectedValues = Graphing.getNextPoint(new Double[] {0.0, startPrice}, new Double[] {1.0 * totalDays, endPrice}, totalDays);
    	
    	//set values for arrays
    	for (int i = 0; i < totalDays; i++) {
    		
        	projectedPlotPointsYCollection.set(i,expectedValues[i][1]);    	
    	}
    	
    	
    	//register the arrays   	
    	try {
    		sec.setActualPriceXCollection(Conversion.doubleArrayListToString(actualPriceXCollection));
    		sec.setActualPriceYCollection(Conversion.doubleArrayListToString(actualPriceYCollection));
    		sec.setAvgPriceOnDayCollection(Conversion.doubleArrayListToString(avgPriceOnDayCollection));
    		sec.setClosePriceOnDayCollection(Conversion.doubleArrayListToString(closePriceOnDayCollection));
	    	sec.setOpenPriceOnDayCollection(Conversion.doubleArrayListToString(openPriceOnDayCollection));
	    	sec.setHighPriceOnDayCollection(Conversion.doubleArrayListToString(highPriceOnDayCollection));
	    	sec.setLowPriceOnDayCollection(Conversion.doubleArrayListToString(lowPriceOnDayCollection));
	    	sec.setProjectedPlotPointsYCollection(Conversion.doubleArrayListToString(projectedPlotPointsYCollection));
	    	sec.setContractsCountOnDayCollection(Conversion.integerArrayListToString(contractsCountOnDayCollection));  
    	} catch (Exception e) {
    		System.out.println("Exception in converting array when setting a security value");
    	}
    	
    	//set single day value stores
    	sec.setCurPrice(startPrice);
    	sec.setPrevPrice(startPrice);
    	sec.setMinContractsPerDay(0);
    	sec.setMaxContractsPerDay(500);
    	sec.setMinPricePerDay(startPrice);
    	sec.setMaxPricePerDay(startPrice);    
    	sec.setDeviation(DEVIATION_DEFAULT);    
    	
    	//setup min and max values
    	sec.setMinPricePerDay(MIN_PRICE_PER_DAY_DEFAULT);
    	sec.setMaxPricePerDay(MAX_PRICE_PER_DAY_DEFAULT);
    	sec.setMinContractsPerDay(MIN_CONTRACTS_PER_DAY_DEFAULT);
    	sec.setMaxContractsPerDay(MAX_CONTRACTS_PER_DAY_DEFAULT);
    	    	  
	   	//persist simulation to invoke id generation
	    em.persist(sec);
	    
	    //add to simulation
	    //simulation.addSecurity(sec);
	    sec.setSimulation(simulation);	    
	    
	    return sec;
    	
    }   
    

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ArrayList<Double> getClosePriceOnDayCollection(Integer securityId) {
		Security sec = getSecurityById(securityId);
		ArrayList<Double> arrayList = new ArrayList<Double>();
		try {
			arrayList = Conversion.stringToDoubleArrayList(sec.getClosePriceOnDayCollection());
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for getClosePriceOnDayCollection()");
		}
		return arrayList;
	}
	
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void setClosePriceOnDayCollection(Integer securityId, ArrayList<Double> closePriceOnDayCollection) {
		Security sec = getSecurityById(securityId);
		try {
	    	sec.setClosePriceOnDayCollection(Conversion.doubleArrayListToString(closePriceOnDayCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for setClosePriceOnDayCollection()");
		}
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ArrayList<Double> getOpenPriceOnDayCollection(Integer securityId) {
		Security sec = getSecurityById(securityId);
		ArrayList<Double> arrayList = new ArrayList<Double>();
		try {
			arrayList = Conversion.stringToDoubleArrayList(sec.getOpenPriceOnDayCollection());
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for getOpenPriceOnDayCollection()");
		}
		return arrayList;
	}
	
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void setOpenPriceOnDayCollection(Integer securityId, ArrayList<Double> openPriceOnDayCollection) {
		Security sec = getSecurityById(securityId);
		try {
	    	sec.setOpenPriceOnDayCollection(Conversion.doubleArrayListToString(openPriceOnDayCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for setOpenPriceOnDayCollection()");
		}
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ArrayList<Double> getHighPriceOnDayCollection(Integer securityId) {
		Security sec = getSecurityById(securityId);
		ArrayList<Double> arrayList = new ArrayList<Double>();
		try {
			arrayList = Conversion.stringToDoubleArrayList(sec.getHighPriceOnDayCollection());
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for getHighPriceOnDayCollection()");
		}
		return arrayList;
	}
	
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void setHighPriceOnDayCollection(Integer securityId, ArrayList<Double> highPriceOnDayCollection) {
		Security sec = getSecurityById(securityId);
		try {
	    	sec.setHighPriceOnDayCollection(Conversion.doubleArrayListToString(highPriceOnDayCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for setHighPriceOnDayCollection()");
		}
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ArrayList<Double> getLowPriceOnDayCollection(Integer securityId) {
		Security sec = getSecurityById(securityId);
		ArrayList<Double> arrayList = new ArrayList<Double>();
		try {
			arrayList = Conversion.stringToDoubleArrayList(sec.getClosePriceOnDayCollection());
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for getLowPriceOnDayCollection()");
		}
		return arrayList;
	}
	
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void setLowPriceOnDayCollection(Integer securityId, ArrayList<Double> lowPriceOnDayCollection) {
		Security sec = getSecurityById(securityId);
		try {
	    	sec.setLowPriceOnDayCollection(Conversion.doubleArrayListToString(lowPriceOnDayCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for setLowPriceOnDayCollection()");
		}
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ArrayList<Integer> getContractsCountOnDayCollection(Integer securityId) {
		Security sec = getSecurityById(securityId);
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		try {
			arrayList = Conversion.stringToIntegerArrayList(sec.getContractsCountOnDayCollection());
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for getContractsCountOnDayCollection()");
		}
		return arrayList;
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)	
	public void setContractsCountOnDayCollection(Integer securityId, ArrayList<Integer> contractsCountOnDayCollection) {
		Security sec = getSecurityById(securityId);
		try {
	    	sec.setContractsCountOnDayCollection(Conversion.integerArrayListToString(contractsCountOnDayCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for setContractsCountOnDayCollection()");
		}
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ArrayList<Double> getProjectedPlotPointsYCollection(Integer securityId) {
		Security sec = getSecurityById(securityId);
		ArrayList<Double> arrayList = new ArrayList<Double>();
		try {
			arrayList = Conversion.stringToDoubleArrayList(sec.getProjectedPlotPointsYCollection());
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for getProjectedPlotPointsYCollection()");
		}
		return arrayList;
	}
	
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void setProjectedPlotPointsYCollection(Integer securityId,  ArrayList<Double> projectedPlotPointsYCollection) {
		Security sec = getSecurityById(securityId);
		try {
	    	sec.setProjectedPlotPointsYCollection(Conversion.doubleArrayListToString(projectedPlotPointsYCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for setProjectedPlotPointsYCollection()");
		}
	}
        
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ArrayList<Double> getAvgPriceOnDayCollection(Integer securityId) {
		Security sec = getSecurityById(securityId);
		ArrayList<Double> arrayList = new ArrayList<Double>();
		try {
			arrayList = Conversion.stringToDoubleArrayList(sec.getAvgPriceOnDayCollection());
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for getAvgPriceOnDayCollection()");
		}
		return arrayList;
	}
	
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void setAvgPriceOnDayCollection(Integer securityId, ArrayList<Double> avgPriceOnDayCollection) {
		Security sec = getSecurityById(securityId);
		try {
	    	sec.setAvgPriceOnDayCollection(Conversion.doubleArrayListToString(avgPriceOnDayCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array from database for setAvgPriceOnDayCollection()");
		}
	} 
    
    

    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   	public ArrayList<Double> getActualPriceXCollection(Integer securityId) {
   		Security sec = getSecurityById(securityId);
   		ArrayList<Double> arrayList = new ArrayList<Double>();
   		try {
   			arrayList = Conversion.stringToDoubleArrayList(sec.getActualPriceXCollection());
   		} 
   		catch(Exception e) {
   			System.out.println("Error processing array from database for getActualPriceXCollection()");
   		}
   		return arrayList;
   	}
   	
       @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   	public void setActualPriceXCollection(Integer securityId, ArrayList<Double> actualPriceXCollection) {
   		Security sec = getSecurityById(securityId);
   		try {
   	    	sec.setActualPriceXCollection(Conversion.doubleArrayListToString(actualPriceXCollection));
   		} 
   		catch(Exception e) {
   			System.out.println("Error processing array from database for setActualPriceXCollection()");
   		}
   	}
       
       @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   	public ArrayList<Double> getActualPriceYCollection(Integer securityId) {
   		Security sec = getSecurityById(securityId);
   		ArrayList<Double> arrayList = new ArrayList<Double>();
   		try {
   			arrayList = Conversion.stringToDoubleArrayList(sec.getActualPriceYCollection());
   		} 
   		catch(Exception e) {
   			System.out.println("Error processing array from database for getActualPriceYCollection()");
   		}
   		return arrayList;
   	}
   	
       @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   	public void setActualPriceYCollection(Integer securityId, ArrayList<Double> actualPriceYCollection) {
   		Security sec = getSecurityById(securityId);
   		try {
   	    	sec.setActualPriceYCollection(Conversion.doubleArrayListToString(actualPriceYCollection));
   		} 
   		catch(Exception e) {
   			System.out.println("Error processing array from database for setActualPriceYCollection()");
   		}
   	}
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void addActualPointDeviated(int securityId, int day, double dayPercent, double yValue) {
    	Security sec;
    	ArrayList<Double> actualPriceXCollection = new ArrayList<Double>();
    	ArrayList<Double> actualPriceYCollection = new ArrayList<Double>();
    	
    	try {
    		//set the security
    		sec = getSecurityById(securityId);
    		
    		//populate the stored values
    		actualPriceXCollection = Conversion.stringToDoubleArrayList(sec.getActualPriceXCollection());
    		actualPriceYCollection = Conversion.stringToDoubleArrayList(sec.getActualPriceYCollection());    	
    		
    		//random YValue Deviation
    		double randomCalculation = Math.random();
    		
    		//negative or positive reflection
    		if (randomCalculation > .5 ) {
    			yValue += (randomCalculation * sec.getDeviation());
    		} else {
    			yValue -= (randomCalculation * sec.getDeviation());
    		}
    		
    		double xElapsedDayVar = Conversion.roundTwoDecimals(day + dayPercent/100);
    		double yValueVar = Conversion.roundTwoDecimals(yValue);
    		
    		System.out.println("Current Day X: " +  xElapsedDayVar + " DAY: " + day + " PERCENT: " + dayPercent);
    		
    		//add the new value		
        	actualPriceXCollection.add(xElapsedDayVar);
        	actualPriceYCollection.add(yValueVar);
    		
    		//write the values
			sec.setActualPriceXCollection(Conversion.doubleArrayListToString(actualPriceXCollection));
			sec.setActualPriceYCollection(Conversion.doubleArrayListToString(actualPriceYCollection));
			
			System.out.println("Heres the collection X: " + Conversion.doubleArrayListToString(actualPriceXCollection));
			System.out.println("Heres the collection Y: " + Conversion.doubleArrayListToString(actualPriceYCollection));
		} 
		catch(Exception e) {
			System.out.println("Error processing array to/from database for setActualPriceX/YCollection()");
			e.printStackTrace();
		}
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}