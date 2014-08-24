/**
 * 
 */
package jmse.utils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import jmse.errors.exceptions.JMSEArrayConversionError;


/**
 * @author JF
 *
 */
public class Conversion {
	
	/**
	 * 
	 */
	public Conversion() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static String integerArrayListToString(ArrayList<Integer> arrayList) throws JMSEArrayConversionError {
		String string = "";	
		
		//set string to deep representation of an array
		string = Arrays.deepToString(arrayList.toArray());
        ////System.out.println("Before Converted String: " + string);
		
		//remove quotes to for integer values
		string = string.replaceAll("\"", " ");
        ////System.out.println("After Converted String: " + string);
				
		return string;		 
	}
	
	public static ArrayList<Integer> stringToIntegerArrayList(String string) throws JMSEArrayConversionError {
		ArrayList<String> stringArrayList;
		ArrayList<Integer> integerArrayList;		
		
        String convertedString = string.replace("[","");
        ////System.out.println("Converted String 1: " + convertedString);
        
        convertedString = convertedString.replace("]","");
        ////System.out.println("Converted String 2: " + convertedString);
        
        integerArrayList = new ArrayList<Integer>();
        
        stringArrayList = new ArrayList<String>(Arrays.asList(convertedString.split(", ")));
        
        ////System.out.println("Converted String Array List: " + Arrays.deepToString(stringArrayList.toArray()));
		
        for (String s : stringArrayList) {
        	integerArrayList.add(Integer.parseInt(s));
        }
		
        ////System.out.println("Converted Integer Array List: " + Arrays.deepToString(integerArrayList.toArray()));
        
		return integerArrayList;		
	}
	
	
	public static String doubleArrayListToString(ArrayList<Double> arrayList) throws JMSEArrayConversionError {
		String string = "";	
		
		//set string to deep representation of an array
		string = Arrays.deepToString(arrayList.toArray());		
        ////System.out.println("Before Converted String: " + string);
		
		//remove quotes to for integer values
		string = string.replaceAll("\"", " ");
        ////System.out.println("After Converted String: " + string);
				
		return string;		 
	}
	
	public static ArrayList<Double> stringToDoubleArrayList(String string) throws JMSEArrayConversionError {
		ArrayList<String> stringArrayList;
		ArrayList<Double> doubleArrayList;		
		
        String convertedString = string.replace("[","");
        ////System.out.println("Converted String 1: " + convertedString);
        
        convertedString = convertedString.replace("]","");
        ////System.out.println("Converted String 2: " + convertedString);
        
        doubleArrayList = new ArrayList<Double>();
        
        stringArrayList = new ArrayList<String>(Arrays.asList(convertedString.split(",")));
        
        ////System.out.println("Converted String Array List: " + Arrays.deepToString(stringArrayList.toArray()));
		
        for (String s : stringArrayList) {
        	doubleArrayList.add(Double.parseDouble(s));
        }
		
        ////System.out.println("Converted Double Array List: " + Arrays.deepToString(doubleArrayList.toArray()));
        
		return doubleArrayList;		
	}
	
	public static double roundTwoDecimals(double d) {
        	DecimalFormat twoDForm = new DecimalFormat("#.##");
        	return Double.valueOf(twoDForm.format(d));
	}
	
	
	
	public static void main(String[] args) {
		/*
		
		ArrayList<Double[]> doubleDArray = new ArrayList<Double[]>();
		
		Double[] double1 = new Double[] {1.0,2.0};
		Double[] double2 = new Double[] {100.0,200.0};
		
		doubleDArray.add(double1);
		doubleDArray.add(double2);
		
		
		
		System.out.println("String version:" + Arrays.deepToString(doubleDArray.toArray()));*/
	
        ////System.out.println("Lets do it!");	
		int totalLength = 10;
		ArrayList<Integer> integerArrayList = new ArrayList<Integer>();	
		ArrayList<Double> doubleArrayList = new ArrayList<Double>();
		String conversionString1 = "";
		String conversionString2 = "";
		
		//create Lists
		for (int i = 0; i < totalLength; i++) {
        	integerArrayList.add(new Integer((int)Math.ceil(Math.random()*100)));
			doubleArrayList.add(new Double(Math.random()*10));
        }
		
        ////System.out.println("Integer Array to String");
		System.out.println("Before: value = " + Arrays.deepToString(integerArrayList.toArray()));
		
		try {
			conversionString1 = Conversion.integerArrayListToString(integerArrayList);
		}
		
		catch(Exception e) {
			System.out.println("Exception in Part 1");
		}
		
		System.out.println("Integer Array to String Afterwards = " + conversionString1);
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
		
		
        ////System.out.println("String to Integer Array");
		System.out.println("Before: value = " + conversionString1);
		
		try {
			integerArrayList = Conversion.stringToIntegerArrayList(conversionString1);
		}
		
		catch(Exception e) {
			System.out.println("Exception in Part 2");
		}
		
		System.out.println("String to Integer Array Afterwards = " + Arrays.deepToString(integerArrayList.toArray()));
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
		
        ////System.out.println("Double Array to String");
		System.out.println("Before: value = " + Arrays.deepToString(integerArrayList.toArray()));
		try {
			conversionString2 = Conversion.doubleArrayListToString(doubleArrayList);
		}
		
		catch(Exception e) {
			System.out.println("Exception in Part 3");
		}

		System.out.println("Integer Array to String Afterwards = " + conversionString2);
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
		
		
		
		
        ////System.out.println("String to Double Array");
		System.out.println("Before: value = " + conversionString2);
		try {
			doubleArrayList = Conversion.stringToDoubleArrayList(conversionString2);
		}
		
		catch(Exception e) {
			System.out.println("Exception in Part 4");
		}
		System.out.println("String to Double Array Afterwards = " +  Arrays.deepToString(doubleArrayList.toArray()));
        ////System.out.println("/////////////////////////////////////////////////////////////////////////////////////");
        
	}
	
}
