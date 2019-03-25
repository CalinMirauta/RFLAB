package lab4_pack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils 
{
	private static final String inputFileValuesSeparator = " ";
	private static final String outputFileValuesSeparator = ",";
	
	
	protected static double[][] readLearningSetFromFile(String fileName) throws USVInputFileCustomException
	{
		//Start with an ArrayList<ArrayList<Double>>
		List<ArrayList<Double>> learningSet = new ArrayList<ArrayList<Double>>();
		// read file into stream, try-with-resources
		try  {
			Stream<String> stream = Files.lines(Paths.get(fileName));
			learningSet = stream.map(FileUtils::convertLineToLearningSetRow).collect(Collectors.toList());
		} 
		catch (FileNotFoundException fnfe)
		{
			throw new USVInputFileCustomException(" We cannot find the scepicified file on USV computer");
		}	
		catch (IOException ioe) {
			throw new USVInputFileCustomException(" We encountered some errors while trying to read the specified file: " + ioe.getMessage());
		}
		catch (Exception e) {
			throw new USVInputFileCustomException(" Other errors: " + e.getMessage());
		}	
		//  convert ArrayList<ArrayList<Double>> to double[][] for performance
		return convertToBiDimensionalArray(learningSet);
	}
	
	private static double[][] convertToBiDimensionalArray(List<ArrayList<Double>> learningSet) {
		
		double[][] learningSetArray = new double[learningSet.size()][];
		
		for (int n = 0; n < learningSet.size(); n++) {
			ArrayList<Double> rowListEntry = learningSet.get(n);
			
			// get each row double values
			double[] rowArray = new double[learningSet.get(n).size()];
			
			for (int p = 0; p < learningSet.get(n).size(); p++) 
			{				
				rowArray[p] = rowListEntry.get(p);
			}
			learningSetArray[n] = rowArray;
			
		}
		return learningSetArray;
	}
	
	private static ArrayList<Double> convertLineToLearningSetRow(String line)
	{
		ArrayList<Double> learningSetRow = new ArrayList<Double>();
		String[] stringValues = line.split(inputFileValuesSeparator);
		//we need to convert from string to double
		for (int p = 0; p < stringValues.length; p++)
		{
			learningSetRow.add(Double.valueOf(stringValues[p]));
		}
		return learningSetRow;
	}
	
	protected static void calculateEuclidianDistance(double[][] learningSet)
	{
		double []xFeature=new double[learningSet.length];
		double []yFeature=new double[learningSet.length];
		double []distance=new double[learningSet.length];
		for(int i=0;i<learningSet.length;i++)
		{
			for(int j=0;j<learningSet.length;j++)
			{
				if(j==0)
				{
					xFeature[i]=learningSet[i][j];
				}
				if(j==1)
				{
					yFeature[i]=learningSet[i][j];
				}
			}
		}
		
		for(int k=0;k<learningSet.length;k++)
		{
			distance[k]=Math.sqrt(Math.pow((xFeature[0]-xFeature[k]),2)+Math.pow((yFeature[0]-yFeature[k]),2));
		}
		
		for(int i=1;i<distance.length;i++)
		{
			System.out.println(String.format("Distanta intre prima caracteristica si caracteristica %s este %s",i+1,distance[i]));
		}
	}
	
	protected static void calculateCebisevDistance(double[][] learningSet)
	{
		double []xFeature=new double[learningSet.length]; 
		double []yFeature=new double[learningSet.length];
		double []distance=new double[learningSet.length];
		for(int i=0;i<learningSet.length;i++)
		{
			for(int j=0;j<learningSet.length;j++)
			{
				if(j==0)
				{
					xFeature[i]=learningSet[i][j];
				}
				if(j==1)
				{
					yFeature[i]=learningSet[i][j];
				}
			}
		}
		
		for(int k=0;k<learningSet.length;k++)
		{
			double max=Math.abs(xFeature[k]-yFeature[k]);
			if(max>distance[k])
				distance[k]=max;
		}
		
		for(int k=0;k<learningSet.length;k++) {
		System.out.println(String.format("Distanta Cebisev este: %s",distance[k]));
		}
		
	}
	protected static void calculateCityBlock(double[][] learningSet)
	{
		double []xFeature=new double[learningSet.length];
		double []yFeature=new double[learningSet.length];
		double distance=0;
		for(int i=0;i<learningSet.length;i++)
		{
			for(int j=0;j<learningSet.length;j++)
			{
				if(j==0)
				{
					xFeature[i]=learningSet[i][j];
				}
				if(j==1)
				{
					yFeature[i]=learningSet[i][j];
				}
			}
		}
		
		for(int k=0;k<learningSet.length;k++)
		{
			double value=Math.abs(xFeature[k]-yFeature[k]);
			distance+=value;
			
		}
		
		
		System.out.println(String.format("Distanta CityBlock este: %s",distance));
		
	}
	
	protected static void calculateMahalanobis(double[][] learningSet,double numberofpattern)
	{
		double []xFeature=new double[learningSet.length];
		double []yFeature=new double[learningSet.length];
		double distance=0;
		
		for(int i=0;i<learningSet.length;i++)
		{
			for(int j=0;j<learningSet.length;j++)
			{
				if(j==0)
				{
					xFeature[i]=learningSet[i][j];
				}
				if(j==1)
				{
					yFeature[i]=learningSet[i][j];
				}
			}
		}
		
		for(int k=0;k<learningSet.length;k++)
		{
			double value=(Math.pow((xFeature[k]-yFeature[k]),numberofpattern));
			distance+=value;
			
		}
		
		
		System.out.println(String.format("Distanta Mahalanobis este: %s",Math.pow(distance,1/numberofpattern)));
		
	}
	protected static void writeLearningSetToFile(String fileName, double[][] normalizedSet)
	{
		// first create the byte array to be written
		StringBuilder stringBuilder = new StringBuilder();
		for(int n = 0; n < normalizedSet.length; n++) //for each row
		{
			//for each column
			 for(int p = 0; p < normalizedSet[n].length; p++) 
			 {
				//append to the output string
				 stringBuilder.append(normalizedSet[n][p]+"");
				 //if this is not the last row element
			      if(p < normalizedSet[n].length - 1)
			      {
			    	  //then add separator
			    	  stringBuilder.append(outputFileValuesSeparator);
			      }
			 }
			//append new line at the end of the row
			 stringBuilder.append("\n"); 
		}
		try {
			Files.write(Paths.get(fileName), stringBuilder.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
