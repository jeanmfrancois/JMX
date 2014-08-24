package jmse.utils;

import java.util.Map;

import javax.faces.context.FacesContext;


public class Params {
	
	public Params() {
		// TODO Auto-generated constructor stub
	}
	

	public static Integer initIntParameter(String passedVar){   
    	Integer integerVar = 0;;
  		try{
  			FacesContext facesContext = FacesContext.getCurrentInstance();
  			Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap(); 
  			integerVar = Integer.parseInt(params.get(passedVar));
  			System.out.println(passedVar + " is being passed " + integerVar);
  		}
  		catch(Exception e) {
  			System.out.println("Invalid Integer parameter passed: " + passedVar);
  		}   
  		
  		if (integerVar == 0) {
  			System.out.println("Warning - Passed Integer value is 0: " + passedVar);
  		}
  		
  		return integerVar;
  	}
	
	public Double initDoubleParameter(String passedVar){   
    	Double doubleVar = 0.0;
  		try{
  			FacesContext facesContext = FacesContext.getCurrentInstance();
  			Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap(); 
  			doubleVar = Double.parseDouble(params.get(passedVar));
  			System.out.println(passedVar + " is being passed");
  			
  		}
  		catch(Exception e) {
  			System.out.println("Invalid Double parameter passed: " + passedVar);
  		}   
  		
  		if (doubleVar == 0) {
  			System.out.println("Warning - Passed Double value is 0: " + passedVar);
  		}
  		
  		return doubleVar;
  	}
	
	public String initStringParameter(String passedVar){   
    	String stringVar = "";
  		try{
  			FacesContext facesContext = FacesContext.getCurrentInstance();
  			Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap(); 
  			stringVar = params.get(passedVar);
  			System.out.println(passedVar + " is being passed");
  			
  		}
  		catch(Exception e) {
  			System.out.println("Invalid String parameter passed: " + passedVar);
  		}   
  		
  		if (stringVar == "" || stringVar == " " || stringVar == null) {
  			System.out.println("Warning - Passed string value empty or null: " + passedVar);
  		}
  		
  		return stringVar;
  	}
	
	
	
}
