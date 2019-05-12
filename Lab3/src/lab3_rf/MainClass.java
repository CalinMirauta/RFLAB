package lab3_rf;

public class MainClass {
	
	
	public static void main(String[] args) {
		double[][] learningSet;
		try {
			learningSet = FileUtils.readLearningSetFromFile("in.txt");
			int numberOfPatterns = learningSet.length;
			int numberOfFeatures = learningSet[0].length;
			System.out.println(String.format("The learning set has %s patters and %s features", numberOfPatterns, numberOfFeatures));
			for (int patternNumber = 1; patternNumber < numberOfPatterns; patternNumber++) {
				double euclidianDistance = DistanceUtils.CalculateEuclidianDistance(learningSet[0], learningSet[patternNumber]);
				System.out.println("The euclidian distance between first pattern and pattern " + patternNumber + " is: " + euclidianDistance);
				double cebisevDistance = DistanceUtils.calculateCebesivDistance(learningSet[0], learningSet[patternNumber]);
				System.out.println("The cebisev distance between first pattern and pattern " + patternNumber + " is: " + cebisevDistance);
				double cityDistance = DistanceUtils.calculateCityDistance(learningSet[0], learningSet[patternNumber]);
				System.out.println("The city distance between first pattern and pattern " + patternNumber + " is: " + cityDistance);
				double mahalanobisDistance = DistanceUtils.calculateMahalanobisDistance(learningSet[0], learningSet[patternNumber], numberOfPatterns);
				System.out.println("The mahalanobis distance between first pattern and pattern " + patternNumber + " is: " + mahalanobisDistance);
			}
		} catch (USVInputFileCustomException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Finished learning set operations");
		}
	}

}

