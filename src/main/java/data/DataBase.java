package data;

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

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projetiedfinal?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Film getFilmInfoFromDb(String title) throws SQLException {
        Connection connection = DataBase.getConnexion();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM film f where f.titre = '" + title + "'");

        List<Film> films = new ArrayList<>();

        while(res.next()) {
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

        return films.size() > 0 ? films.get(0) : null;
    }

    public static void closeConnection() throws SQLException {
        conn.close();
    }

}
