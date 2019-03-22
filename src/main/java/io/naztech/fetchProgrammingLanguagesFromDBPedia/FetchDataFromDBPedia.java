package io.naztech.fetchProgrammingLanguagesFromDBPedia;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
//import org.apache.jena.query.ResultSetFormatter;

public class FetchDataFromDBPedia {
	
	public ResultSet fetchdata(String query)
	{
		ParameterizedSparqlString qs = new ParameterizedSparqlString(query);

        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = exec.execSelect();

        return results;
	}

}
