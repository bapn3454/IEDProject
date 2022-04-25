import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.library.leviathan.log;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, SQLException {
//        new Scrapper().retrieveTheNumbersData();
//        launchMenu();
//        getDbPediaData();
//        new DbPediaClient().getDbPediaData("Harry Potter");
//        new OpenMovieClient().getFilmData("tail");
        searchInfos("Avatar");
    }


    private static void launchMenu() throws SQLException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {
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
                    searchInfos("Avatar");
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



    private static void searchInfos(String title) throws SQLException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {
//        System.out.println(title);
        List<Film> filmDb = DataBase.getFilmInfoFromDb(title);
        Film filmOpenMovie = new OpenMovieClient().getFilmData(title);

    }

    private static void searchMovies(){
        System.out.println("movies");
    }
}
