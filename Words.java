package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Words {
	Words() {
		wordstask();
		CalculateFrequency();
	}

	Set<String> NewsgroupSet = new HashSet<String>();
	List<String> list3 = new ArrayList<String>();
	HashMap<String, Set<String>> inner = new HashMap<String, Set<String>>();
	List<String> NewsgroupList = new ArrayList<String>();
	List<String> l = new ArrayList<String>();
	public Map<String, Integer> FrequencyMap = new HashMap<String, Integer>();
	public Set<String> d = new HashSet<String>();

	public void wordstask() {
		File file = new File("dataset.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] details = line.split("	");
				NewsgroupList.add(details[0]);
				NewsgroupSet.add(details[0]);
				Map<String, Integer> tmap = new HashMap<String, Integer>();
				for (String temp : NewsgroupList) {
					Integer occurrences = tmap.get(temp);
					tmap.put(temp, (occurrences == null) ? 1 : occurrences + 1);
				}

				String temp = details[1];
				String[] data = temp.split(" ");
				d.addAll(Arrays.asList(data));
				l.addAll(Arrays.asList(data));
				int p = tmap.get(details[0]);
				inner.put(details[0] + p, d);

			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public double getwordcount(String NGname, String word) {
		double count = 0;

		for (int i = 1; i <= 100; i++) {
			String s = NGname + i;
			Set<String> t = inner.get(s);
			if (!t.isEmpty()) {
				if (t.contains(word.trim())) {
					count++;
				}
			}
		}
		return count;
	}

	public double getjointwordcount(String NGname, String[] words) {
		double count = 0;
		Set<String> t = null;
		for (int i = 1; i <= 100; i++) {
			String s = NGname + i;

			t = inner.get(s);
			if (!t.isEmpty()) {
				if (t.contains(words[0].trim())) {
					if (t.contains(words[1].trim())) {
						count++;
					}

				}
			}
		}
		return count;

	}

	public void CalculateFrequency() {

		for (String s : l) {

			if (FrequencyMap.containsKey(s)) {
				Integer I = FrequencyMap.get(s);
				I++;
				FrequencyMap.put(s, I);
			} else {
				FrequencyMap.put(s, 1);
			}
		}

	}

}
