package detect;


import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class GiveMeLocationOP {

	public static LinkedHashMap<String, LinkedHashSet<String>> map;

	public GiveMeLocationOP() {
		map = new<String, LinkedHashSet<String>> LinkedHashMap();
	}

	public void getNER(String text, String model) {
		String serializedClassifier = model;
		System.out.println(serializedClassifier);
		CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		List<List<CoreLabel>> classify = classifier.classify(text);
		for (List<CoreLabel> coreLabels : classify) {
			for (CoreLabel coreLabel : coreLabels) {

				String word = coreLabel.word();
				String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
				if (!"O".equals(category)) {
					if (map.containsKey(category)) {
						map.get(category).add(word);
					} else {
						LinkedHashSet<String> temp = new LinkedHashSet<String>();
						temp.add(word);
						map.put(category, temp);
					}
					// System.out.println(word + ":" + category);
				}
			}
		}
	}

	public  String giveMeLocation() {
		if(map.get("LOCATION")!=null)
			return map.get("LOCATION").toString();
		else
			return null;
	}

	public  String giveMePersons() {
		if(map.get("PERSON")!=null)
			return map.get("PERSON").toString();
		else 	
			return null;
	}

	public  String giveMeOrg() {

		if(map.get("ORGANIZATION")!=null)
			return map.get("ORGANIZATION").toString();
		else 	
			return null;
	}

}
