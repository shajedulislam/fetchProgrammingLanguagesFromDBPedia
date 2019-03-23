package io.naztech.fetchProgrammingLanguagesFromDBPedia;

import org.apache.jena.query.QuerySolution;
//import static org.junit.Assert.*;
import org.apache.jena.query.ResultSet;
//import org.apache.jena.query.ResultSetFormatter;
import org.junit.Test;

public class testDataFetching {
	
	FetchDataFromDBPedia fetch = new FetchDataFromDBPedia();

	@Test
	public void testLabelFetching() {
		
		String q = 	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"+
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
					"PREFIX dbo: <http://dbpedia.org/ontology/>\n"+
					
					"SELECT ?pl ?pl_label \n" +
					
					
					"WHERE 	{\n" +
					
					"	   	?pl rdf:type dbo:ProgrammingLanguage .\n" + 
						
					"		?pl rdfs:label ?pl_label .\n" + 
						
					"		FILTER ( LANG ( ?pl_label ) = 'en' ) .\n" + 
					
					"	   	}"+
					
					"		GROUP BY ?pl ?pl_label";
		
		
		ResultSet results = fetch.fetchdata(q);	
		
		int count = 0;
		
		while (results.hasNext()) 
		{
			QuerySolution qs =  results.next();
            System.out.println("NAME-->\n"+qs.get("pl_label").toString()+"\n"); 
            System.out.println("\n\n");
           
            count++;
        }
		
		System.out.println(count);
		
	    //ResultSetFormatter.out(results);
		
	}
	
	
	@Test
	public void testLabelandAbstractFetching() {
		
		String q = 	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"+
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
					"PREFIX dbo: <http://dbpedia.org/ontology/>\n"+
					
					"SELECT ?pl ?pl_label ?abstract\n" +
					
					
					"WHERE 	{\n" +
					
					"	   	?pl rdf:type dbo:ProgrammingLanguage .\n" + 
					
					"      	?pl dbo:abstract ?abstract .\n" + 
						
					"		FILTER ( LANG ( ?abstract ) = 'en' ) .\n" + 
						
					"		?pl rdfs:label ?pl_label .\n" + 
						
					"		FILTER ( LANG ( ?pl_label ) = 'en' ) .\n" + 
					
					"	   	}"+
					
					"		";
		
		
		ResultSet results = fetch.fetchdata(q);	
		
		int count = 0;
		
		while (results.hasNext()) 
		{
			QuerySolution qs =  results.next();
            System.out.println("NAME-->\n"+qs.get("pl_label").toString()+"\n"); 
            System.out.println("ABSTRACT-->\n"+qs.get("abstract").toString()+"\n"); 
            System.out.println("\n\n");
           
            count++;
        }
		
		System.out.println(count);
		
	    //ResultSetFormatter.out(results);
		
	}
	
	
	
	@Test
	public void testAllDataFetching() {
		
		String q = 	"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"+
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
					"PREFIX dbo: <http://dbpedia.org/ontology/>\n"+
					
					"SELECT ?pl ?pl_label \n" +
					"( Group_concat ( ?_influenced_label; separator= \", \")   AS ?influenced ) \n" + 
					"( Group_concat ( ?_influencedBy_label; separator= \", \") AS ?influencedBy ) \n" + 
					"?abstract \n" +
					
					"WHERE 	{\n" +
					
					"	   	?pl rdf:type dbo:ProgrammingLanguage .\n" + 
					
					"      	?pl dbo:abstract ?abstract .\n" + 
						
					"		FILTER ( LANG ( ?abstract ) = 'en' ) .\n" + 
						
					"		?pl rdfs:label ?pl_label .\n" + 
						
					"		FILTER ( LANG ( ?pl_label ) = 'en' ) .\n" + 
						
					"		?pl dbo:influenced ?_influenced .\n" + 
						
					"		?_influenced rdfs:label ?_influenced_label .\n" + 
						
					"		FILTER ( LANG ( ?_influenced_label ) = 'en' ) .\n" + 
						
					"		?pl dbo:influencedBy  ?_influencedBy .\n" + 
						
					"		?_influencedBy  rdfs:label ?_influencedBy_label .\n" + 
						
					"		FILTER ( LANG ( ?_influencedBy_label ) = 'en' ) .\n" + 
					
					"	   	}"+
					
					"		GROUP BY ?pl ?pl_label ?abstract";
		
		
		ResultSet results = fetch.fetchdata(q);	
		
		int count = 0;
		
		while (results.hasNext()) 
		{
			QuerySolution qs =  results.next();
            System.out.println("NAME-->\n"+qs.get("pl_label").toString()+"\n"); 
            System.out.println("INFLUENCED-->\n"+qs.get("influenced").toString()+"\n"); 
            System.out.println("INFLUENCED BY-->\n"+qs.get("influencedBy").toString()+"\n"); 
            System.out.println("ABSTRACT-->\n"+qs.get("abstract").toString()+"\n"); 
            System.out.println("\n\n");
           
            count++;
        }
		
		System.out.println(count);
		
	    //ResultSetFormatter.out(results);
		
	}

}
