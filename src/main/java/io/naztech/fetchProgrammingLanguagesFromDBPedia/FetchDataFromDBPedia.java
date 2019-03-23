package io.naztech.fetchProgrammingLanguagesFromDBPedia;

//import org.apache.jena.query.ParameterizedSparqlString;
//import org.apache.jena.query.QueryExecution;
//import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
//import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class FetchDataFromDBPedia {
	
	private QueryEngineHTTP qe;

	public ResultSet fetchdata(String query)
	{
		//ParameterizedSparqlString qs = new ParameterizedSparqlString(query);

        //QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());
        
        qe = new QueryEngineHTTP("http://dbpedia.org/sparql", query);
        ResultSet results = qe.execSelect();

        return results;
	}

}
