package project1;

public class probability {
	public double calprobablilty;
	public double condprobability;
	public double noOfWords = 0.0;
	Words w = new Words();

	public double calculateProbablity(String s, String p) throws Exception {
		double i = w.getwordcount(s, p);
		calprobablilty = i / 100;
		return calprobablilty;
	}

	public double calculateConditionalProbability(String s, String[] p) {
		double i = w.getjointwordcount(s, p);
		condprobability = i / 100;
		return condprobability;
	}

	public double numberOfWords(String s) {
		for (String p : w.NewsgroupSet) {
			noOfWords += w.getwordcount(p, s);
		}
		return noOfWords;
	}
}
