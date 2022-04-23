import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.library.leviathan.log;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
//        new Scrapper().retrieveTheNumbersData();
//        launchMenu();
//        getDbPediaData();
//        new DbPediaClient().db();
        new OpenMovieClient().getFilmData("tail");
    }


    private static void launchMenu() {
        Scanner scanner = new Scanner(System.in);
        int n = 1;
        while ( n != 99 ) {
            System.out.println("Indiquer votre choix");
            System.out.println("1 - Etant donné un titre de film, afficher toutes les informations disponibles sur le film");
            System.out.println("2 - Etant donné un nom d’acteur, afficher la liste des films où il a joué");
            System.out.println("99 - Quitter le programme");
            n = scanner.nextInt();

            switch (n) {

                case 1:
                    searchInfos();
                    break;
                case 2:
                    searchMovies();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Merci de choisir une option entre 1 et 2 !");
            }
        }
        System.out.println("FIN DU PROGRAMME !");
    }

    private static void searchInfos() {
        System.out.println("infos");
    }

    private static void searchMovies(){
        System.out.println("movies");
    }
}
