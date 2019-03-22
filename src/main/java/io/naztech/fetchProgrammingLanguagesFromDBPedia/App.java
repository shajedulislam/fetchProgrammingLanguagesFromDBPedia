package io.naztech.fetchProgrammingLanguagesFromDBPedia;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class App 
{
    public static void main(String[] args) {
        ParameterizedSparqlString qs = new ParameterizedSparqlString(""
                + "prefix rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX dbo:     <http://dbpedia.org/ontology/>"
                + "\n"
                + "SELECT ?pl ?label \n" + 
                "    WHERE { \n" + 
               // "          ?pl dbo:abstract ?abstract .\n" + 
                "            ?pl rdfs:label ?label .\n" + 
               // "            ?pl dbo:influenced ?influenced .\n" + 
               // "            ?pl dbo:influencedBy ?influencedBy .\n" + 
                "            ?pl rdf:type dbo:ProgrammingLanguage .\n" + 
               // "            FILTER (LANG(?abstract) = 'en') .\n" + 
                                    "   FILTER (LANG(?label)='en')"+
                "         }");


        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = exec.execSelect();

        int count = 0;

        while (results.hasNext()) {

            System.out.println(results.next().get("label").toString()+"");
            //System.out.println(results.next().get("abstract").toString()+"\n\n");
            count++;
        }

        System.out.println(count);


        //ResultSetFormatter.out(results);
    }
}