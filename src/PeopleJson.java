
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

/*
 * @author Damiano Fossa
 */
public class PeopleJson {  	
	public static PeopleStore people = new PeopleStore();

	/**
	 * Creates three People object and three HealthProfile object
	 */
	public static void initializeDB() {
		HealthProfile hp_first = new HealthProfile(80.0, 1.82);
		Person walter = new Person(new Long(1), "Walter", "White", "1974-06-21", hp_first);
		HealthProfile hp_second = new HealthProfile(68.0, 1.72);
		Person skyler = new Person(new Long(2), "Skyler", "White", "1975-03-20", hp_second);
		HealthProfile hp_third = new HealthProfile(70.0, 1.78);
		Person barney = new Person(new Long(3), "Barney", "Stinson", "1980-03-20", hp_third);
		
		people.getData().add(walter);
		people.getData().add(skyler);
		people.getData().add(barney);
	}	

	/**
	 * Marshall the data into a Json file
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
    }
}
