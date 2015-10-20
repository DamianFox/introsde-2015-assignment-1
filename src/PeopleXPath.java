import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/*
 * @author Damiano Fossa
 */
public class PeopleXPath {
	Document doc;
    XPath xpath;
    
    /**
	 * Load the XML file
	 * @throws ParserConfigurationException, SAXException, IOException, XPathExpressionException
	 */
    public void loadXML() throws ParserConfigurationException, SAXException, IOException, 
    	XPathExpressionException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse("people.xml");

        //creating xpath object
        getXPathObj();
    }
    
    /**
	 * Create the XPathFactory object
	 * @throws ParserConfigurationException, SAXException, IOException, XPathExpressionException
	 */
    public XPath getXPathObj() throws ParserConfigurationException, SAXException,
    IOException, XPathExpressionException {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }
    
    /**
	 * Get all the people
	 * @throws XPathExpressionException
	 */
    public void getAllPeople() throws XPathExpressionException {
    	System.out.println("(using xpath = //person )");
        XPathExpression expr = xpath.compile("//person");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        
        for(int k=0; k<nodes.getLength(); k++){
			System.out.println(nodes.item(k).getTextContent());
		}
    }
    
    /**
	 * Find a person's health profile by ID
	 * @param id
	 * @return A String containing the person's health profile
	 * @throws XPathExpressionException
	 */
    public String getHealthProfileById(int id) throws XPathExpressionException {

    	System.out.println("(using xpath = //person[@id=" + id + "]/healthprofile");
        XPathExpression expr = xpath.compile("//person[@id=" + id + "]/healthprofile");
        NodeList nodelist = (NodeList) expr.evaluate(doc, XPathConstants.NODE);
        
        String weight="", height="", bmi="";
        for(int j=0; j<nodelist.getLength(); j++){
			Node name = nodelist.item(j);
			if(name.getNodeName().equals("weight")){
				weight = name.getTextContent(); 
			} else if (name.getNodeName().equals("height")){
				height = name.getTextContent();
			} else if (name.getNodeName().equals("bmi")){
				bmi = name.getTextContent();
			}
		}
        return "weight: "+weight+", height: "+height+", bmi: "+bmi;
    }
    
    /**
	 * Finds people's weight which satisfy the condition and it prints it
	 * @param number
	 * @throws XPathExpressionException
	 */
    public void getWeightByCondition(String number) throws XPathExpressionException {

    	System.out.println("/people/person[./healthprofile/weight " + number + "]");
        XPathExpression expr = xpath.compile("people/person[./healthprofile/weight " + number + "]");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        for(int k=0; k<nodes.getLength(); k++){
        	Node node = nodes.item(k);
			System.out.println(node.getTextContent());
		}

    }
    
    /**
	 * Execute a certain depending on the parameter specified
	 * @param args
	 * @throws XPathExpressionException, ParserConfigurationException, SAXException, IOException
	 */
    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, 
		SAXException, IOException {

		PeopleXPath people_xpath = new PeopleXPath();
		people_xpath.loadXML();

		if(args[1].equals("getAllPeople")){
			people_xpath.getAllPeople();
		} else if (args[1].equals("getPersonById")){
			int id = Integer.valueOf(args[2]);
			people_xpath.getHealthProfileById(id);
		} else if (args[1].equals("getWeightByCondition")){
			people_xpath.getWeightByCondition(args[2]);
		}
    	
    }
}
