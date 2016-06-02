package ro.oho.rest.utils;

public class XssParse {
	public static String parseXSS(String message) {

		StringBuilder messageXSS = new StringBuilder();

		for (int iterator = 0; iterator < message.length(); iterator++) {
			switch (message.charAt(iterator)) {
			case '\"':
				messageXSS.append("&#34;");
				break;
			case '#':
				messageXSS.append("&#35;");
			case '&':
				messageXSS.append("&#38;");
				break;
			case '\'':
				messageXSS.append("&#39;");
				break;
			case '(':
				messageXSS.append("&#40;");
				break;
			case ')':
				messageXSS.append("&#41;");
				break;	
			case '/':
				messageXSS.append("&#47;");
				break;
			case ';':
				messageXSS.append("&#59;");
				break;
			case '<':
				messageXSS.append("&#60;");
				break;
			case '>':
				messageXSS.append("&#62;");
				break;
			default:
				messageXSS.append(message.charAt(iterator));
			}
		}
		return messageXSS.toString();
	}
}
