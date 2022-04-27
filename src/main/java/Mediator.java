import data.DataBase;
import data.DbPediaClient;
import data.Film;
import data.OpenMovieClient;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mediator {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, SQLException {
        launchMenu();
    }

    private static void launchMenu() throws SQLException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        int n = 1;
        while ( n != 99 ) {
            String data;
            System.out.println("Indiquer votre choix");
            System.out.println("1 - Etant donné un titre de film, afficher toutes les informations disponibles sur le film");
            System.out.println("2 - Etant donné un nom d’acteur, afficher la liste des films où il a joué");
            System.out.println("99 - Quitter le programme");
            n = scanner.nextInt();
            scanner.nextLine();

            switch (n) {

                case 1:
                    System.out.println("Merci d'entrer le titre d'un film");
                    data = scanner.nextLine();
                    getMovieByTitle(data);
                    break;
                case 2:
                    System.out.println("Merci d'entrer le nom d'un acteur");
                    data = scanner.nextLine();
                    getMoviesByActor(data);
                    break;
                case 99:
                    DataBase.closeConnection();
                    System.out.println("FIN DU PROGRAMME !");
                    break;
                default:
                    System.out.println("Merci de choisir une option entre 1 et 2 !");
            }
        }

    }

    private static void getMovieByTitle(String title) throws SQLException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {

        Film filmDb = DataBase.getFilmInfoFromDb(title.replace("'","\\'"));
        Film filmOpenMovie = new OpenMovieClient().getFilmData(title);
        Film filmDbPedia = new DbPediaClient().getFilm(title);

        if ( filmDb != null ) {
            if ( filmDb.getTitre().equals( filmOpenMovie.getTitre() )
                    && filmDb.getReleaseDate().indexOf( filmOpenMovie.getReleaseDate() ) >= 0 ) {
                filmDb.setResume( filmOpenMovie.getResume() );
            }
                filmDb.setRealisators( filmDbPedia.getRealisators() );
                filmDb.setActors( filmDbPedia.getActors() );
                filmDb.setProductors( filmDbPedia.getProductors() );

            System.out.println(filmDb.toString());

        } else {
            System.out.println("Ce film est introuvable !");
        }
    }

    private static void getMoviesByActor(String actor) throws SQLException {

        List<Film> filmDbPedia = new DbPediaClient().getActor(actor);
        List<Film> filmDbPediaUpdated = new ArrayList<>();
        for ( Film film : filmDbPedia ) {
            Film infos = new DbPediaClient().getFilm(film.getTitre());
            Film filmDb = DataBase.getFilmInfoFromDb( film.getTitre().replace("'","\\'") );
            if ( filmDb != null ) {
                filmDb.setRealisators( infos.getRealisators() );
                filmDb.setActors( infos.getActors() );
                filmDb.setProductors( infos.getProductors() );

                filmDbPediaUpdated.add(filmDb);
            }

        }

        for ( Film film : filmDbPediaUpdated ) {
            System.out.println(film.toStringRequest2());
        }

    }
}
