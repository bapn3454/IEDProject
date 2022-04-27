package data;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ResourceFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DbPediaClient {

    public Film getFilm(String title) {

        List<String> acteurs = new ArrayList<String>();
        List<String> producers = new ArrayList<String>();
        List<String> directors = new ArrayList<String>();

        ParameterizedSparqlString qs = new ParameterizedSparqlString(""
                + "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX dbo:     <http://dbpedia.org/ontology/>"
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
                + ""
                + "\n"
                + "select distinct ?directeur_name ?producteur_name ?acteur_name where {\n"
                + "  ?film a dbo:data.Film;\n"
                + "      foaf:name ?nom;\n"
                + "     dbo:director ?d;\n"
                + "        dbo:producer ?p;\n"
                + "        dbo:starring ?a.\n"
                + "  ?d foaf:name ?directeur_name.\n"
                + "  ?p foaf:name ?producteur_name.\n"
                + "  ?a foaf:name ?acteur_name.\n"
                + "}");

        Literal title1 = ResourceFactory.createLangLiteral(title, "en");
        qs.setParam("nom", title1);

        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = exec.execSelect();

        while (results.hasNext()) {
            QuerySolution s = results.nextSolution();

            acteurs.add(s.get("acteur_name").toString().split("@")[0]);
            producers.add(s.get("producteur_name").toString().split("@")[0]);
            directors.add(s.get("directeur_name").toString().split("@")[0]);

        }
        List<String> acteursDist = new ArrayList<String>(new HashSet<String>(acteurs));
        List<String> prosucteursDist = new ArrayList<String>(new HashSet<String>(producers));
        List<String> dirDist = new ArrayList<String>(new HashSet<String>(directors));

        return new Film(title, dirDist, acteursDist, prosucteursDist );

    }

    public List<Film> getActor(String acteur) {

        String header = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dbo: <http://dbpedia.org/ontology/> \n";

        List<String> films = new ArrayList<String>();

        String queryString = header + "select distinct ?film_title where {\n"
                + "  ?film a dbo:Film;\n"
                + "   foaf:name ?film_title;\n"
                + "   dbo:starring ?a.\n"
                + "  ?a foaf:name \""+ acteur +"\"@en.\n"
                + "}";

        Query query = QueryFactory.create(queryString);
        QueryExecution qExe = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql/", query);
        ResultSet results = qExe.execSelect();

        List<Film> filmList = new ArrayList<>();

        while (results.hasNext()) {
            QuerySolution s = results.nextSolution();
            films.add(s.get("film_title").toString().split("@")[0]);
        }

        List<String> filmDist = new ArrayList<String>(new HashSet<String>(films));

        for ( String title : filmDist ) {
            filmList.add(new Film(title));
        }

        return filmList;
    }

}
