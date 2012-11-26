package music.store;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
            System.out.println("ERROR: " + e);
        }
    }

    private static void selectAllMusic() throws SQLException {

        java.sql.Connection connection = DriverManager.getConnection(MUSIC_STORE_URL, USERNAME, PASSWORD);

        /* turn commits off */
        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM music");

        ResultSetMetaData queryMetaData = query.getMetaData();

        int numberOfColumns = queryMetaData.getColumnCount();

        System.out.println("TABLE COLUMNS:");
        for (int i = 1; i <= numberOfColumns; i++) {
            System.out.println(">>" + queryMetaData.getColumnName(i));
        }

        while (query.next()) {
            String music_TITLE = query.getString("MUSIC_TITLE");
            String music_ISBN = query.getString("MUSIC_ISBN");
            String music_type = query.getString("MUSIC_TYPE");
            String producer = query.getString("PRODUCER");
            String genre = query.getString("GENRE");
            String year = query.getString("YEAR");
            String vendor_ID = query.getString("VENDOR_ID");
            String price = query.getString("PRICE");
            String quanity = query.getString("QUANTITY");

            System.out.print("| " + music_TITLE + " | ");
            System.out.print(music_ISBN + " | ");
            System.out.print(music_type + " | ");
            System.out.print(producer + " | ");
            System.out.print(genre + " | ");
            System.out.print(year + " | ");
            System.out.print(vendor_ID + " | ");
            System.out.print(price + " | ");
            System.out.print(quanity + " |\n");

            // break;

        }

        statement.close();

    }
}