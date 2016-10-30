package project1;



public class probability {
	public double calprobablilty;
	public double condprobability;
	public double noOfWords=0.0;
	public double calculateProbablity(String s,String p) throws Exception{
		Words w=new Words();
		double i=w.getwordcount(s,p);
		calprobablilty= i/100;
		return calprobablilty;
	}
	
	public double calculateConditionalProbability(String s,String[] p)
	{
		Words w=new Words();
		double i=w.getjointwordcount(s,p);
		condprobability=i/100;
		return condprobability;
	}
	
	public double numberOfWords(String s){
		Words w=new Words();
		for(String p:w.NewsgroupSet){
			noOfWords+=w.getwordcount(p, s);
		}
			return noOfWords;
	}
		
	
		
	}
	
	

