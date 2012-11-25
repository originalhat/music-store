package music.store;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    final static String MUSIC_STORE_URL = "jdbc:oracle:thin:@//Cncsidb01.msudenver.edu:1521/db01";
    final static String USERNAME = "bgill9";
    final static String PASSWORD = "W3lc0m3";

    public static void main(String[] args) {
        try {
            selectAllMusic();
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
    }

    private static void selectAllMusic() throws SQLException {

        java.sql.Connection connection = DriverManager.getConnection(MUSIC_STORE_URL, USERNAME, PASSWORD);

        /* turn commits off */
        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM music");

        while (query.next()) {
            System.out.println(query.getString("TITLE"));
        }

        statement.close();

    }
}