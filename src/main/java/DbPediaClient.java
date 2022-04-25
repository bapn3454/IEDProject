import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;

import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
public class DbPediaClient {

    public void getDbPediaData(String title) {
        String queryStr =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX foaf: <http://xmlns.com/foaf/0.1/> PREFIX dbo: <http://dbpedia.org/ontology/> SELECT ?f WHERE { ?f rdf:type dbo:Film; foaf:name \""+title+"\"@en. }";
        Query query = QueryFactory.create(queryStr);

        // Remote execution.
        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            // Set the DBpedia specific timeout.
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            // Execute.
            ResultSet rs = qexec.execSelect();
            ResultSetFormatter.out(System.out, rs, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void db() {

//        String sparqlQueryString = "select distinct ?Concept where {[] a ?Concept } LIMIT 2";
//
        String sparqlQueryString = "PREFIX lm: <http://jena.hpl.hp.com/2004/08/location-mapping#>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
                + "SELECT ?f WHERE {"
                + "?f rdf:type"
                + "foaf:name \"Ed Wood\"@en."
                + "}";


//        String sparqlQueryString = "PREFIX lm: <http://jena.hpl.hp.com/2004/08/location-mapping#>"
//                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
//                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
//                +"SELECT ?f WHERE {\n" +
//                "foaf:name \"Titanic\"@en.\n" +
//                "}";

        Query query = QueryFactory.create(sparqlQueryString);

        QueryExecution qexec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", query);


        try {
            ResultSet results = qexec.execSelect();
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution() ;
                String x = soln.get("Concept").toString();
                System.out.print(x +"\n");
            }

        }
        finally { qexec.close() ; }

    }


    private void getDbPediaData() {
//        String SOURCE = "http://www.opentox.org/api/1.1";
//        String NS = SOURCE + "#";
        String SOURCE = " http://dbpedia.org/";
        String NS = SOURCE;
//        Query q = QueryFactory.create("PREFIX lm: <http://jena.hpl.hp.com/2004/08/location-mapping#>"
//                + "SELECT * WHERE {"
//                + "[] lm:mapping ?e ."
//                + "?e lm:name ?name ; lm:altName ?alt ."
//                + "OPTIONAL { ?e lm:media ?media . } "
//                + "}");
        Query q = QueryFactory.create("PREFIX lm: <http://jena.hpl.hp.com/2004/08/location-mapping#>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
                + "SELECT ?f WHERE {"
                + "?f rdf:type"
                + "foaf:name \"Ed Wood\"@en."
                + "}");
        OntModel model1 = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
        model1.read( SOURCE, "RDF/XML" );
        //prints out the RDF/XML structure
//        qe.close();
        System.out.println(" ");
//        String q =
//                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
//                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+
//                        "select ?uri "+
//                        "where { "+
//                        "?uri rdfs:subClassOf <http://www.opentox.org/api/1.1#Feature>  "+
//                        "} \n ";
        Query query = QueryFactory.create(q);

        System.out.println("----------------------");

        System.out.println("Query Result Sheet");

        System.out.println("----------------------");

        System.out.println("Direct&Indirect Descendants (model1)");

        System.out.println("-------------------");


        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model1);
        ResultSet results =  qe.execSelect();

        // Output query results
        ResultSetFormatter.out(System.out, results, query);

        qe.close();

//        System.out.println("----------------------");
//        System.out.println("Only Direct Descendants");
//        System.out.println("----------------------");
//
//        // Execute the query and obtain results
//        qe = QueryExecutionFactory.create(query, model2);
//        results =  qe.execSelect();
//
//        // Output query results
//        ResultSetFormatter.out(System.out, results, query);
//        qe.close();
    }

}
