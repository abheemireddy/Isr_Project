package project1;
import java.io.File;
import java.lang.Math;
import java.util.*;

public class operations {

	public static void main(String[] args) {

		File file = new File("C:/Users/Vinod Chokkula/Desktop/input.txt");
		Scanner sc;

		try {
			sc = new Scanner(file);
			
			String[] details = null;
			Integer temp = null;
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				details = line.split(", ");
				temp = Integer.parseInt(details[0]);
				
				System.out.println("Command "+temp+" result..");
				// for entropy
				String[] words = details[2].split(" ");
				List<String> list3 = new ArrayList<String>();
				if (words.length > 1) {
					for (int i = 0; i < words.length; i++) {
						list3.add(words[i].trim());
						//System.out.println("www"+words[i]);
					}
				} else {
					list3.add(details[2]);
				}
				Words w = new Words();
				Set<String> str = w.NewsgroupSet;
				// System.out.println("*****"+list3.toString());

				// ENTROPY
				if (temp == 1) {
					String s=details[1].trim();
					double Entropy = 0.0;
					// System.out.println("SSSSS"+s);
					switch (s) {
					case "all": {
						// if(s.trim()=="all"){
						// System.out.println("In ALL loop");
						for (String d : str) {
							Entropy = 0.0;
							for (int j = 0; j < list3.size(); j++) {
								String q = (String) list3.get(j);
								// System.out.println("val"+q);
								probability p = new probability();
								double i = p.calculateProbablity(d, q);
								
								Entropy += (i == 0.0) ? 0.0 : -i * (Math.log(i) / Math.log(2));
							}
							if (Double.isNaN(Entropy)) {
								System.out.println("Entropy cannot be calculated for " + d);
							} else
								System.out.println("Entropy for newsgroup " + d + " is " + Entropy);
						}
						// }
						break;
					}
					default: {
						if (str.contains(s)) {
							for (int j = 0; j < list3.size(); j++) {
								String q = (String) list3.get(j);
								// System.out.println("val"+q);
								probability p = new probability();
								double i = p.calculateProbablity(s, q);
								Entropy += (i==0) ? 0.0 : i * (Math.log(i) / Math.log(2));
							}
							Entropy *= -1;
							System.out.println("Entropy for given words in the newsgroup "+s+" is " + Entropy);
							break;
						} else if (!str.contains(s)) {
							System.out.println("Invalid Command...Check NewsGroup name");
							break;
						}
					}

					}

				}

				if (temp == 2) {
					String s = details[1].trim();
					// Words w=new Words();

					String[] stt = new String[2];
					switch (s) {
					case "all": {
						System.out.println("In ALL loop");
						for (String d : str) {
							double conditionalEntropy = 0.0;
							for (int j = 1; j < list3.size(); j++) {
								stt[0] = list3.get(0);
								stt[1] = list3.get(j);
								probability p = new probability();
								double i = p.calculateConditionalProbability(d, stt);
								double e = p.calculateProbablity(d, stt[1]);
								double l = e / i;
								//System.out.println("i " + i + " e " + e + " l " + l);
								// conditionalEntropy += (i == 0.0)? 0.0 : i *
								// (Math.log(l)/Math.log(2));
								conditionalEntropy += (i == 0.0 || l == 0) ? 0.0 : i * (Math.log(l) / Math.log(2));
							}
							if (Double.isNaN(conditionalEntropy)) {
								System.out.println("Coniditional Entropy cannot be calculated");
							} else
								System.out
										.println("Conditional Entropy for newsgroup " + d + " is" + conditionalEntropy);
						}
						break;
					}

					default: {
						if (str.contains(s)) {
							double conditionalEntropy = 0.0;
							for (int j = 1; j < list3.size(); j++) {

								// String q = list3.get(j);
								//System.out.println("$$$" + j + " size " + list3.size());
								stt[0] = list3.get(0);
								stt[1] = list3.get(j);
								probability p = new probability();
								// System.out.println("val"+stt[j]);
								double i = p.calculateConditionalProbability(s, stt);
								// double e=p.calculateProbablity(s,stt[0]);
								double e = p.calculateProbablity(s, stt[1]);

								double l = e / i;
								//System.out.println("i " + i + " e " + e + " l " + l);
								conditionalEntropy += (i == 0.0) ? 0.0 : i * (Math.log(l) / Math.log(2));
							}
							System.out.println(
									"Conditional Entropy of given words in newsgroup " + s + " is " + conditionalEntropy);
							break;
						} else {
							System.out.println("Invalid Command...Check cNewsGroup name");
							break;
						}
					}

					}
				}

				if (temp == 3) {
					String s = details[1].trim();
					if (list3.size() > 2) {
						System.out.println("Only 2 keys are allowed for Mutual Information "+list3.size());
						break;

					}
					switch (s) {
					case "all": {
						double X = 0.0, Y = 0.0, XY = 0.0;
						String[] st = new String[2];
						st[0] = list3.get(0);
						st[1] = list3.get(1);
						probability p = new probability();
						for (String t : str) {

							X += p.calculateProbablity(t, st[0]);
							Y += p.calculateProbablity(t, st[1]);
							XY += p.calculateConditionalProbability(t, st);

						}
						// System.out.println("XXXX"+X+"YYYY"+Y+"ZZZZ"+XY);
						double mutualInformation = 0.0;
						/*
						 * String x = list3.get(0); String y = list3.get(1);
						 */
						double l = ((X * Y) / XY);
						mutualInformation += XY * (Math.log(l) / Math.log(2));
						System.out.println("Mutual Information of given words is " + mutualInformation);
						break;
					}

					default: {
						double mutualInformation = 0.0;
						String x = list3.get(0);
						String y = list3.get(1);
						probability p = new probability();
						double X = p.calculateProbablity(details[1].trim(), x);
						double Y = p.calculateProbablity(details[1].trim(), y);
						String[] xy = new String[2];
						xy[0] = x;
						xy[1] = y;
						double XY = p.calculateConditionalProbability(details[1].trim(), xy);
						double Z = X * Y;
						// System.out.println("XXXX"+X+"YYYY"+Y+"ZZZZ"+XY);
						double l = (Z != 0.0 && XY != 0.0) ? (Z / XY) : 0.0;
						// System.out.println("XY"+l);
						mutualInformation += (l != 0) ? XY * (Math.log(l) / Math.log(2)) : 0.0;
						System.out.println("Mutual Information " + mutualInformation);
						
					}
					}
				}
				
				if (temp == 4) {
					String s = details[1].trim();
					if (list3.size() > 2) {
						System.out.println("Only 2 keys are allowed to calculate for KL Divergence");
						break;
					}
					switch (s) {
					case "all": {
						double KLDivergence = 0.0;
						double X = 0.0, Y = 0.0, G = 0.0, La = 0.0;
						Words p = new Words();
						String x = list3.get(0);
						String y = list3.get(1);
						probability t = new probability();

						G = t.numberOfWords(x);
						La += t.numberOfWords(y);

						for (String n : str) {
							X = p.getwordcount(n, x) / G;
							Y = p.getwordcount(n, y) / La;
							// System.out.println(x +" "+y+" "+X+" "+Y);
							double l = (X != 0.0 && Y != 0.0) ? (X / Y) : 0.0;
							KLDivergence += (l != 0) ? X * (Math.log(l) / Math.log(2)) : 0;

						}
						System.out.println("KL Divergence for the words " + x + " and " + y + ":" + KLDivergence);
						break;
					}
					default: {
						System.out.println("Invalid command for KL");
						break;
					}
					}
				}
						
			}
			sc.close();
			
		} catch (Exception e1) {

			System.out.println("Input.txt file missing ");
			// e1.printStackTrace();
		}

	}

}
