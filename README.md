<h1>introsde-2015-assignment-1</h1>

This is the first assignmment of the course "Introduction of Service Design and Engineering" of the university of Trento.
This project uses the following technologies: Java, XML/XSD, JSON, XPATH, JAXB/Jackson.

The whole project is based on one XML file and one XSD file: <code>people.xml</code> and <code>people.xsd</code>.

<h3>Project Structure</h3>

The project is divided in 3 packages:

<ul>
	<li><code>dao</code>: this package contains one Java class, <code>PeopleStore.java</code>, which stores and returns the list of People objects.</li>
	<li><code>model</code>: this package contains two Java classes, <code>HealthProfile.java</code> and <code>Person.java</code>, which are the 
	rappresentation of Person and HealthProfile objects.</li>
	<li><code>people</code>: this package contains two Java classes, <code>JAXBMarshaller.java</code> and <code>JAXBUnMarshaller.java</code>. The
	first one does the Marshalling from Java objects to a XML file (people.xml), the second one does the Un-Marshalling from <code>people.xml</code>, 
	to Java objects.</li>
	<li>The remaining Java classes are <code>PeopleXPath.java</code>, which handles several XPath operations; <code>PeopleWriter.java</code>, 
	which Marshalls Java objects to <code>people.xml</code>; <code>PeopleJson.java</code>, which Marshalls Java objects to <code>people.json</code>.</li>
</ul>

<h2>Information</h2>

<h4>Based on Lab 3</h4>

<ul>
	<li><p>Use xpath to implement methods like getWeight and getHeight.</p>
		<p>These methods were in <code>PeopleXPath.java</code>, in which all the heights and weights are printed.</p>
	</li>

	<li><p>Make a function that prints all people in the list with detail.</p>
		<p><code>getAllPeople</code> was implemented <code>PeopleXPath.java</code>, in which all people with details are printed with details.</p>
	</li>

	<li><p>A function that accepts id as parameter and prints the HealthProfile of the person with that id.</p>
		<p><code>getHealthProfileById</code> was implemented <code>PeopleXPath.java</code>, which takes the id of the person and prints the 
			health profile.</p>
	</li>

	<li><p>A function which accepts a weight and an operator (=, > , <) as parameters and prints people that fulfill that condition 
		(i.e., >80Kg, =75Kg, etc.).</p>
		<p><code>getWeightByCondition</code> was implemented <code>PeopleXPath.java</code>, which takes the condition (which is > and a number)
		 and prints the weights that satisfied that condition.</p>
	</li>
</ul>

<h4>Based on Lab 4</h4>

<ul>
	<li><p>Write a java application that does the marshalling and un-marshalling using JAXB and generated classes with JAXB XJC.</p>
		<p>This part is handled by <code>JAXBMarshaller.java</code> and <code>JAXBUnMarshaller.java</code>. <code>JAXBMarshaller.java</code> does 
			the marshalling from Java objects to <code>people.xml</code> and prints it on terminal. <code>JAXBUnMarshaller.java</code> does 
			the un-marshalling from <code>people.xml</code> to Java objects.</p>
	</li>

	<li><p>Make your java application to convert also JSON</p>
		<p>This part is handled by <code>PeopleJson.java</code>. This java class does 
			the marshalling from Java objects to <code>people.json</code> and prints it on terminal.</p>
	</li>
</ul>


<h2>How to run the code</h2>

The project contains <code>ant</code>, which is a Java library and command-line tool whose mission is to drive processes described in build files 
as targets and extension points dependent upon each other.

To run the project execute:

<code>ant execute.evaluation</code>, which download dependecies, creates java classes from the XSD file, populates <code>people.xml</code> from
 java objects and executes the targets described below:

<code>ant execute.getAllPeople</code>: prints all the people with details using the method <code>getAllPeople</code> of <code>PeopleXPath.java</code>.

<code>ant execute.getAllWeights</code>: prints all the weights using the method <code>getWeight</code> of 
	<code>PeopleXPath.java</code>.

<code>ant execute.getAllHeights</code>: prints all the heights using the method <code>getHeight</code> of 
	<code>PeopleXPath.java</code>.

<code>ant execute.getPersonById</code>: prints the health profile of a person using the method <code>getHealthProfileById</code> of 
	<code>PeopleXPath.java</code>, given an id as a parameter.

<code>ant execute.getWeightByCondition</code>: prints the weights that satisfied the condition using the method <code>getHealthProfileById</code> of 
	<code>PeopleXPath.java</code>, given an "> 75" as a parameter.

<code>ant execute.JAXBMarshaller</code>: does the marshalling from Java objects to <code>people.xml</code>.

<code>ant execute.JAXBUnMarshaller</code>: does the un-marshalling.

<code>ant execute.PeopleJson</code>: does the marshalling from Java objects to <code>people.json</code>
