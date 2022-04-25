import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static Connection conn;

    public static Connection getConnexion() {

        if (conn != null)
            return conn;
        initConnexion();
        return conn;
    }

    private static void initConnexion() {
        try
        {
            //étape 1: charger la classe de driver
            Class.forName("com.mysql.jdbc.Driver");
            //étape 2: créer l'objet de connexion
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projetiedfinal?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "");
            //étape 3: créer l'objet statement

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static List<Film> getFilmInfoFromDb(String title) throws SQLException {
        Connection connection = DataBase.getConnexion();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM film f where f.titre = '" + title + "'");
//        ResultSet res = stmt.executeQuery("SELECT * FROM film where titre = \"Avatar\"");
        List<Film> films = new ArrayList<>();
        //étape 4: exécuter la requête
        while(res.next()) {
            System.out.println(res.getString(1)+"  "+res.getString(2)
                    +"  "+res.getString(3));
            Film film = new Film(res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7),
                    null);
            films.add(film);
        }
        //étape 5: fermez l'objet de connexion
        connection.close();
        return films;
    }

}
