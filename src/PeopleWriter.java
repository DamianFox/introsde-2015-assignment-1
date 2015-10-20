import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import dao.PeopleStore;
import model.HealthProfile;
import model.Person;

/*
 * @author Damiano Fossa
 */
public class PeopleWriter {  	
	public static PeopleStore people = new PeopleStore();

	/**
	 * Creates several People and HealthProfile objects to add to the XML file
	 */
	public static void initializeDB() {
		Person person;
		HealthProfile healthprofile;
		
		healthprofile = new HealthProfile(90, 1.70);
		person = new Person(1L, "George R. R.", "Martin", "1984-09-20", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(80, 1.80);
		person = new Person(2L, "Dan", "Brown", "1965-11-19", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(80, 1.80);
		person = new Person(3L, "Alessandro", "Del Piero", "1960-02-27", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(75, 1.72);
		person = new Person(4L, "Matthew", "Bellamy", "1958-03-14", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(78, 1.80);
		person = new Person(5L, "Corey", "Taylor", "1945-08-31", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(82, 1.85);
		person = new Person(6L, "Brandon", "Boyd", "1977-01-29", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(75, 1.75);
		person = new Person(7L, "Tom", "Morello", "1954-09-22", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(90, 1.86);
		person = new Person(8L, "Serj", "Tankian", "1946-07-07", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(66, 1.81);
		person = new Person(9L, "Tim", "Ferriss", "1979-10-17", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(73, 1.81);
		person = new Person(10L, "Jon", "Snow", "1989-09-28", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(71, 1.89);
		person = new Person(11L, "Will", "Ferguson", "1971-02-20", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(67, 1.68);
		person = new Person(12L, "Wayne", "Rooney", "1992-08-13", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(91, 1.90);
		person = new Person(13L, "Christiano", "Ronaldo", "1981-05-25", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(56, 1.52);
		person = new Person(14L, "Sergio", "Romero", "1995-08-27", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(58, 1.63);
		person = new Person(15L, "Matteo", "Darmian", "1996-04-18", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(69, 1.86);
		person = new Person(16L, "Giuseppe", "Rossi", "1988-08-17", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(92, 1.96);
		person = new Person(17L, "Dominic", "Howard", "1984-03-10", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(67, 1.81);
		person = new Person(18L, "Jim", "Root", "1984-08-22", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(79, 1.88);
		person = new Person(19L, "Chris", "Cornell", "1979-11-29", healthprofile);
		people.getData().add(person);
		healthprofile = new HealthProfile(77, 1.78);
		person = new Person(20L, "Mick", "Thompson", "1946-03-16", healthprofile);
		people.getData().add(person);
	}	

	/**
	 * Marshalls several People and HealthProfile objects to a XML file
	 * @params args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("people.xml")); // marshalling into a file
        m.marshal(people, System.out);			  // marshalling into the system default output
    }
}
