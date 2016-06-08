import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class NER {
	public static LinkedHashMap<String, LinkedHashSet<String>> identifyNER(
			String text, String model) {
		LinkedHashMap<String, LinkedHashSet<String>> map = new<String, LinkedHashSet<String>> LinkedHashMap();
		String serializedClassifier = model;
		System.out.println(serializedClassifier);
		CRFClassifier<CoreLabel> classifier = CRFClassifier
				.getClassifierNoExceptions(serializedClassifier);
		List<List<CoreLabel>> classify = classifier.classify(text);
		for (List<CoreLabel> coreLabels : classify) {
			for (CoreLabel coreLabel : coreLabels) {

				String word = coreLabel.word();
				String category = coreLabel
						.get(CoreAnnotations.AnswerAnnotation.class);
				if (!"O".equals(category)) {
					if (map.containsKey(category)) {
						map.get(category).add(word);
					} else {
						LinkedHashSet<String> temp = new LinkedHashSet<String>();
						temp.add(word);
						map.put(category, temp);
					}
					System.out.println(word + ":" + category);
				}

			}

		}
		return map;
	}

	public static void main(String args[]) {
		String content = "Mergem maine cu Ionel la Bucharest cu Tarom.";

		System.out
				.println(identifyNER(
						content,
						"NER\\classifiers\\english.all.3class.distsim.crf.ser")
						.toString());
	}

}