package lab4_pack;

public class DistanceUtils {

	public static double calculateEuclidianDistance(double p1[],double p2[],int noOfF) {
		
		double suma=0.0; 
		for(int i=0;i<noOfF;i++)
		{
			double r=p1[i]-p2[i];
			r=Math.pow(r,2);
			suma+=r;
		}
		
		return Math.round(Math.sqrt(suma)*100.0)/100.0;
	}
	
	public static double [][]calculateEuclidianDistanceMatrice(double [][]learningSet)
	{
		double [][]mat=new double [learningSet.length][learningSet.length];
		for(int i=0;i<learningSet.length;i++)
		{
			for(int j=0;j<learningSet.length;j++)
			{
				mat[i][j]=calculateEuclidianDistance(learningSet[i],learningSet[j],learningSet[0].length);
			}
		}
		for(int j=0;j<learningSet.length;j++)
		{
			for (int i=0;i<j;i++)

			{
				mat[j][i]=mat[j][i];
			}
		}
		return mat;
	}
	
	public static double calculateCebesivDistance(double p1[],double p2[]) {
		double max=0.0;
		for(int i=0;i<p1.length;i++) {
			double r=p1[i]-p2[i];
			if(r<0) r*=-1;
			if(r>max) max = r;
		}
		return max;
	}
	public static double calculateCityBlockDistance(double p1[],double p2[]) {
		double sum=0.0;
		for(int i=0;i<p1.length;i++) {
			double r=p1[i]-p2[i];
			if(r<0) r*=-1;
			sum+= r;
		}
		return sum;
	}
	public static double calculateMahalanobisDistance(double p1[], double p2[], int units) {
		double sum=0.0;
		for(int i=0;i<p1.length;i++) {
			double r=p1[i]-p2[i];
			Math.pow(r,units);
			sum+= r;
		}
	
		return Math.pow(sum, (double)1/units);
	}
	
}
