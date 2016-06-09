package detect;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class DomXmlParser {

	String path = "allEventTypes.xml";

	public void parse() {
		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				System.out.println(eventType.getNodeName());
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println("	" + eventField.getNodeName());
						NodeList nodeList = eventField.getChildNodes();
						if (eventField.getNodeName().equals("name")) {
							System.out.println("		" + eventField.getTextContent());
						}
						for (int k = 0; k < nodeList.getLength(); k++) {
							Node node = nodeList.item(k);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								System.out.println("		" + node.getNodeName());
								// if(node.getNodeName().equals("name")){
								System.out.println("			" + node.getTextContent());
								// }
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

	public boolean isSetDateBetween(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allDates")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("between")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetDateOptional(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allDates")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("optional")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetDateMultiple(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allDates")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("multiple")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetDateMandatory(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allDates")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("mandatory")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getDate(String eventName) {
		String[] ret = new String[2];
		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allDates")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getNodeName().equals("finishDate")) {
												ret[1] = dateFields.item(l).getTextContent();
											} 
											if (dateFields.item(l).getNodeName().equals("startDate")) {
												ret[0] = dateFields.item(l).getTextContent();
											} 
										}
										return ret;
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public boolean isSetTimeBetween(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allTimes")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("between")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetTimeOptional(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allTimes")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("optional")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetTimeMultiple(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allTimes")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("multiple")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetTimeMandatory(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allTimes")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("mandatory")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getTime(String eventName) {
		String[] ret = new String[2];
		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allTimes")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getNodeName().equals("finishHour")) {
												ret[1] = dateFields.item(l).getTextContent();
											} 
											if (dateFields.item(l).getNodeName().equals("startHour")) {
												ret[0] = dateFields.item(l).getTextContent();
											}
										}
										return ret;
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public boolean isSetNameMandatory(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allNames")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("mandatory")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetNameMultiple(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allNames")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("multiple")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetNameOptional(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allNames")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("optional")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getNames(String eventName) {
		String[] ret = null;
		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allNames")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getNodeName().equals("listOfNames")) {
												ret = dateFields.item(l).getTextContent().split(",");
												return ret;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public boolean isSetOrganizationMandatory(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allOrgs")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("mandatory")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetOrganizationMultiple(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allOrgs")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("multiple")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetOrganizationOptional(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allOrgs")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("optional")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getOrgs(String eventName) {
		String[] ret = null;
		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allOrgs")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getNodeName().equals("listOfOrg")) {
												ret = dateFields.item(l).getTextContent().split(",");
												return ret;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public boolean isSetLocationMandatory(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allLocations")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("mandatory")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetLocationMultiple(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allLocations")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("multiple")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isSetLocationOptional(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allLocations")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getTextContent().equals("optional")) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getLocations(String eventName) {
		String[] ret = null;
		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allLocations")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getNodeName().equals("listOfLocation")) {
												ret = dateFields.item(l).getTextContent().split(",");
												return ret;
											}
										}

									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public boolean isSetMandatoryWords(String eventName) {

		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allMandatoryWords")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (!dateFields.item(l).getTextContent().isEmpty()) {
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String[] getMandatoryWords(String eventName) {
		File inputFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList eventTypes = doc.getElementsByTagName("eventType");

			for (int i = 0; i < eventTypes.getLength(); i++) {
				Node eventType = eventTypes.item(i);
				NodeList eventFields = eventType.getChildNodes();
				for (int j = 0; j < eventFields.getLength(); j++) {
					Node eventField = eventFields.item(j);
					if (eventField.getNodeType() == Node.ELEMENT_NODE) {
						if (eventField.getNodeName().equals("name")) {
							if (eventField.getTextContent().equals(eventName)) {
								for (int k = 0; k < eventFields.getLength(); k++) {
									Node node = eventFields.item(k);
									if (node.getNodeName().equals("allMandatoryWords")) {
										NodeList dateFields = node.getChildNodes();
										for (int l = 0; l < dateFields.getLength(); l++) {
											if (dateFields.item(l).getNodeName().equals("listOfWords")) {
												String[] ret = new String[dateFields.item(l).getTextContent()
														.split(",").length + 1];
												String[] temp = dateFields.item(l).getTextContent().split(",");
												for (int m = 0; m < temp.length; m++) {
													ret[m] = temp[m];
												}
												ret[temp.length] = dateFields.item(l).getTextContent();
												return ret;
											}
										}

									}
								}
							}
						}
					}
				}
				
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return null;
	}
/*
	public static void main(String[] args) {
		DomXmlParser myXml = new DomXmlParser();
		String[] res=myXml.getDate("Theatre");
		System.out.println("*"+res[0]+"*");
		System.out.println("*"+res[1]+"*");
	}
*/
}