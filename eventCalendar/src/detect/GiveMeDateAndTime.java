package detect;
//Autor:Popovici

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;

public class GiveMeDateAndTime {

	
	public ArrayList<String> DateAndTime(String words) {
	    Properties props = new Properties();
	    AnnotationPipeline pipeline = new AnnotationPipeline();
	    pipeline.addAnnotator(new TokenizerAnnotator(false));
	    pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
	    pipeline.addAnnotator(new POSTaggerAnnotator(false));
	    pipeline.addAnnotator(new TimeAnnotator("sutime", props));
	    ArrayList<String> DatesFound = new ArrayList<String>();
	      
	    Annotation annotation = new Annotation(words);
	    annotation.set(CoreAnnotations.DocDateAnnotation.class, "2015-05-05");
	    pipeline.annotate(annotation);
	    // System.out.println(annotation.get(CoreAnnotations.TextAnnotation.class));
	      List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);
	      for (CoreMap cm : timexAnnsAll) {
	        List<CoreLabel> tokens = cm.get(CoreAnnotations.TokensAnnotation.class);
	        DatesFound.add(cm.get(TimeExpression.Annotation.class).getTemporal().toString());//put all the dates found into an array
	        
	      
	    }
	      return DatesFound;
	}
	public String giveMeDate(String text) {
		int regexsNumber = 5;
		String[] results = new String[regexsNumber + 1];
		String[] dataExplicitRegex = new String[regexsNumber + 1];

		String sep, zi, luna, an;
		String bestDate;
		sep = "(([/]| |:|'|[.]|,|of|[-]|)*)";
		zi = "(([0-3]?)([0-9])(nd|th|st)?)";
		luna = "(january|JANUARY|January|jan|Jan|JAN|01|1|";
		luna = luna + "february|FEBRUARY|February|feb|Feb|FEB|02|2|";
		luna = luna + "march|MARCH|March|mar|Mar|MAR|03|3|";
		luna = luna + "april|APRIL|April|apr|Apr|APR|04|4|";
		luna = luna + "may|May|MAY|05|5|";
		luna = luna + "june|JUNE|June|jun|Jun|JUN|06|6|";
		luna = luna + "july|JULY|July|jul|Jul|JUL|07|7|";
		luna = luna + "august|AUGUST|August|aug|Aug|AUG|08|8|";
		luna = luna + "september|SEPTEMBER|September|sep|Sep|SEP|09|9|";
		luna = luna + "october|OCTOBER|October|oct|Oct|OCT|10|";
		luna = luna + "november|NOVEMBER|November|nov|Nov|NOV|11|";
		luna = luna + "december|DECEMBER|December|dec|Dec|DEC|12)";
		an = "(((2|1)([0-9])([0-9])([0-9]))|([1-9][0-9]))";
		dataExplicitRegex[1] = sep + zi + sep + luna + sep + an+sep;
		dataExplicitRegex[2] = sep + luna + sep + zi+sep;
		dataExplicitRegex[3] = sep + luna + sep + zi + sep + an+sep;
		dataExplicitRegex[4] = sep + zi + sep + luna+sep;
		dataExplicitRegex[5] = sep + luna + sep + zi+sep;
		for (int i = 1; i <= regexsNumber; i++) {
			dataExplicitRegex[i]=dataExplicitRegex[i]+"(((and|AND|And)"+dataExplicitRegex[i]+")?)";
		}
		Pattern r = Pattern.compile(dataExplicitRegex[1]);
		Matcher m = r.matcher(text);

		for (int i = 1; i <= regexsNumber; i++) {

			r = Pattern.compile(dataExplicitRegex[i]);
			m = r.matcher(text);
			if (m.find()) {
				results[i] = m.group(0);

			}

		}
		
		results = RemoveNullValue(results);
		if (results.length > 0)
			bestDate = maxString(results);
		else	
			bestDate="nu am gasit data";
		return bestDate;

	}

	public String giveMeTime(String text) {
		String time = null;
		int regexsNumber = 4;
		String[] results = new String[regexsNumber + 1];
		String[] timeRegex = new String[regexsNumber + 1];
		String sep = "(([/]| |:|'|[.]|,|of|-|)*)";
		//timeRegex[1] = "((((0|1)?)[0-9])|2[0-3])(:|.|/|,| )[0-5][0-9]";
		timeRegex[1] = "([01]?[0-9]|2[0-3])(:| |[.]|,)[0-5][0-9]((:| |[.]|,)[0-5][0-9])?";
		timeRegex[2] = "(((1|2|3|4|5|6|7|8|9)|(1[0-2]))(( )*)(am|pm))";
		timeRegex[3] = "((one|two|three|four|five|six|seven|eight|nine|ten|eleven|twelve|1|2|3|4|5|6|7|8|9|10|11|12)(( )*)(O.Clock|O.CLOCK|O.clock|o.clock))";
		timeRegex[4] = "((1[0-9])|(2[0-4])|([0-9]))(h|H)([0-6][0-9])"; 		
		for (int i = 1; i <= regexsNumber; i++) {
			timeRegex[i]=sep+timeRegex[i]+sep;
		}
		Pattern r = Pattern.compile(timeRegex[1]);
		Matcher m = r.matcher(text);

		for (int i = 1; i <= regexsNumber; i++) {

			r = Pattern.compile(timeRegex[i]);
			m = r.matcher(text);
			if (m.find()) {
				results[i] = m.group(0);

			}

		}
		results = RemoveNullValue(results);
		if (results.length > 0)
			time = maxString(results);
		else
			time ="nu am gasit timp";
		return time;
	}

	static String[] RemoveNullValue(String[] firstArray) {
		return firstArray = Arrays.stream(firstArray).filter(s -> (s != null && s.length() > 0)).toArray(String[]::new);

	}

	static String maxString(String array[]) {
		int index = 0;

		int elementLength = array[0].length();
		for (int i = 1; i < array.length; i++) {
			if (array[i].length() > elementLength) {
				index = i;
				elementLength = array[i].length();
			}
		}
		return array[index];

	}
}
