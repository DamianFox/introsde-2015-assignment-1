package people;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import people.generated.HealthProfileType;
import people.generated.PeopleType;
import people.generated.PersonType;

/*
 * @author Damiano Fossa
 */
public class JAXBMarshaller {
	
	private Marshaller marshaller; 
	private people.generated.ObjectFactory factory;
	private PeopleType people;
	
	/**
	 * Creates three People object and three HealthProfile object, then it marshalls them into an XML file
	 * @param xmlDocument
	 * @throws DatatypeConfigurationException, FileNotFoundException
	 */
	public void generateXMLDocument(File xmlDocument) throws DatatypeConfigurationException, FileNotFoundException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance("people.generated");
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			factory = new people.generated.ObjectFactory();
	
			people = factory.createPeopleType();

			PersonType person1 = factory.createPersonType();
			person1.setId(new BigInteger("0001"));
			person1.setFirstname("Tom");
			person1.setLastname("Cruise");
			person1.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(1970, 6, 3)));
	
			HealthProfileType healthProfile1 = factory.createHealthProfileType();
			healthProfile1.setHeight(new Float("1.72"));
			healthProfile1.setWeight(new Float("80"));
			healthProfile1.setBmi(new Float("26.78"));
			healthProfile1.setLastupdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(2015, 3, 7, 11, 43, 00, 0, 0));
			person1.setHealthprofile(healthProfile1);
			
			PersonType person2 = factory.createPersonType();
			person2.setId(new BigInteger("0002"));
			person2.setFirstname("Daniel");
			person2.setLastname("Craig");
			person2.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(1970, 6, 3)));
	
			HealthProfileType healthProfile2 = factory.createHealthProfileType();
			healthProfile2.setHeight(new Float("1.84"));
			healthProfile2.setWeight(new Float("85"));
			healthProfile2.setBmi(new Float("36.78"));
			healthProfile2.setLastupdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(2013, 3, 6, 12, 43, 00, 0, 0));
			person2.setHealthprofile(healthProfile2);
			
			PersonType person3 = factory.createPersonType();
			person3.setId(new BigInteger("0003"));
			person3.setFirstname("Angelina");
			person3.setLastname("Jolie");
			person3.setBirthdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(1970, 6, 3)));
	
			HealthProfileType healthProfile3 = factory.createHealthProfileType();
			healthProfile3.setHeight(new Float("1.65"));
			healthProfile3.setWeight(new Float("65"));
			healthProfile3.setBmi(new Float("43.56"));
			healthProfile3.setLastupdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(2011, 11, 9, 12, 41, 00, 0, 0));
			person3.setHealthprofile(healthProfile3);
	
			people.getPerson().add(person1);
			people.getPerson().add(person2);
			people.getPerson().add(person3);
			
			JAXBElement<PeopleType> peopleElement = factory
					.createPeople(people);
			System.out.println(peopleElement);
			marshaller.marshal(peopleElement,
					new FileOutputStream(xmlDocument));

		} catch (JAXBException e) {
			System.out.println(e.toString());
		}

	}

	public static void main(String[] argv) throws DatatypeConfigurationException, FileNotFoundException {
		String xmlDocument = "people.xml";
		JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
		jaxbMarshaller.generateXMLDocument(new File(xmlDocument));
	}
}
