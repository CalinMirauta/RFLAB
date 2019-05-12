package lab3_rf;

public class DistanceUtils {
protected static double CalculateEuclidianDistance(double firstLine[], double nextLine[]) {
	return Math.sqrt(Math.pow(firstLine[0] - nextLine[0], 2) + Math.pow(firstLine[1] - nextLine[1],2));
}

protected static double calculateCebesivDistance(double firstLine[], double nextLine[]) {
	double max= 0.0;
	for(int i=0; i < firstLine.length; i++)
	{
		double result=Math.abs(firstLine[i] - nextLine[i]);
		if(result>max) max=result;
	}
	
	return max;
}

protected static double calculateCityDistance(double firstLine[], double nextLine[]) {
	double dist= 0.0;
	for(int i=0; i < firstLine.length; i++)
	{
		dist +=Math.abs(firstLine[i] - nextLine[i]);
	}
	
	return dist;
}
protected static double calculateMahalanobisDistance(double firstLine[], double nextLine[], int n) {
 double result=0.0;
	for(int i=0; i < firstLine.length; i++)
	{
		result +=Math.pow((firstLine[i] - nextLine[i]),n);
	}
 return Math.pow(result,1.0/n);
	
}/*

protected static double CalculateCityBlockDistance(double firstLine[], double nextLine[]) {
	return 
}*/

	
}
