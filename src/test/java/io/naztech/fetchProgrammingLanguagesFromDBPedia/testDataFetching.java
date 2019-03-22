package io.naztech.fetchProgrammingLanguagesFromDBPedia;

//import static org.junit.Assert.*;
import org.apache.jena.query.ResultSet;
//import org.apache.jena.query.ResultSetFormatter;
import org.junit.Test;

public class testDataFetching {
	
	FetchDataFromDBPedia fetch = new FetchDataFromDBPedia();

	@Test
	public void testLabelFetching() {
		
		String q = "prefix rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                 + "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n"
                 + "PREFIX dbo:     <http://dbpedia.org/ontology/>\n"
                 + "SELECT ?pl ?label \n" 
                 + "WHERE { \n"  
                
                 			+"?pl rdfs:label ?label .\n" 
                 			+"?pl rdf:type dbo:ProgrammingLanguage .\n" 
                            +"FILTER (LANG(?label)='en')"
 
                 +		 "}";
		
		
		ResultSet results = fetch.fetchdata(q);
		
		while (results.hasNext()) 
		{
            System.out.println(results.next().get("label").toString()+"");  
        }
		
	    //ResultSetFormatter.out(results);
		
	}

}
