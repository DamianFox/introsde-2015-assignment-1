package people;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import people.generated.PeopleType;
import people.generated.PersonType;

/*
 * @author Damiano Fossa
 */
public class JAXBUnMarshaller {
	
	/**
	 * Un-marshalls People from a XML file and prints the list of People object, based on a XML schema
	 * @param xmlDocument
	 * @throws SAXException, ParseException
	 */
	public void unMarshall(File xmlDocument) throws SAXException, ParseException {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance("people.generated");

			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = schemaFactory.newSchema(new File("people.xsd"));
			unMarshaller.setSchema(schema);
			CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
			unMarshaller.setEventHandler(validationEventHandler);

			@SuppressWarnings("unchecked")
			JAXBElement<PeopleType> peopleElement = (JAXBElement<PeopleType>) unMarshaller
					.unmarshal(xmlDocument);

			PeopleType people = peopleElement.getValue();

			List<PersonType> personList = people.getPerson();

			SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

			for (int i = 0; i < personList.size(); i++) {

				PersonType person = (PersonType) personList.get(i);
				String birthdate_format = "";
				String updatedate_format = "";

					birthdate_format = date_format.format(date_format.parse(person.getBirthdate().toString()));
					updatedate_format = date_format.format(date_format.parse(person.getHealthprofile().getLastupdate().toString()));
					
					System.out.println("Person Id: " + person.getId());
					System.out.println("Firname: " + person.getFirstname());
					System.out.println("Lastname: " + person.getLastname());
					System.out.println("Birthdate: " + birthdate_format);
					System.out.println("Last update: " + updatedate_format);
					System.out.println("Weight: " + person.getHealthprofile().getWeight());
					System.out.println("Height: " + person.getHealthprofile().getHeight());
					System.out.println("BMI: " + person.getHealthprofile().getBmi());

			}
		} catch (JAXBException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] argv) throws SAXException, ParseException {
		File xmlDocument = new File("people.xml");
		JAXBUnMarshaller jaxbUnmarshaller = new JAXBUnMarshaller();

		jaxbUnmarshaller.unMarshall(xmlDocument);

	}

	class CustomValidationEventHandler implements ValidationEventHandler {
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() == ValidationEvent.WARNING) {
				return true;
			}
			if ((event.getSeverity() == ValidationEvent.ERROR)
					|| (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

				System.out.println("Validation Error:" + event.getMessage());

				ValidationEventLocator locator = event.getLocator();
				System.out.println("at line number:" + locator.getLineNumber());
				System.out.println("Unmarshalling Terminated");
				return false;
			}
			return true;
		}

	}
}

