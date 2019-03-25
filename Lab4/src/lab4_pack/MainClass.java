package lab4_pack;



public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			double [][]m=new double [learningSet.length][learningSet.length];
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			
				 m=DistanceUtils.calculateEuclidianDistanceMatrice(learningSet);
				 for(int i=0;i<learningSet.length;i++)
					{
						for (int j=0;j<learningSet.length;j++)
						{
							System.out.print(m[i][j]+"  ");
						} 
						System.out.print("\n");
					}
				/*System.out.println(String.format("The Cebesiv distance between line 1 and and line %s is %s ", i+1, DistanceUtils.calculateCebesivDistance(learningSet[0],learningSet[i])));
				System.out.println(String.format("The City Block distance between line 1 and and line %s is %s ", i+1, DistanceUtils.calculateCityBlockDistance(learningSet[0],learningSet[i])));
				System.out.println(String.format("The Mahalanobis distance between line 1 and and line %s is %s ", i+1, DistanceUtils.calculateMahalanobisDistance(learningSet[0],learningSet[i],numberOfPatterns)));*/
			
			System.out.println(String.format("\nThe learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}
