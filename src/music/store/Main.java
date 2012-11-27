package music.store;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    final private static String MUSIC_STORE_URL = "jdbc:oracle:thin:@//Cncsidb01.msudenver.edu:1521/db01";
    final private static String USERNAME = "bgill9";
    final private static String PASSWORD = "W3lc0m3";

    public static void main(String[] args) {

        ArrayList<HashMap<String, String>> musicTable = new ArrayList<HashMap<String, String>>();

        try {

            // query music table; with or without a condition
            musicTable = queryMusicInformation("");

            // testing
            for (int rowNumber = 0; rowNumber < musicTable.size(); rowNumber++) {
                System.out.println(musicTable.get(rowNumber).get("PRICE"));
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Out of Bounds!: " + e);
        }
    }

    private static ArrayList<HashMap<String, String>> queryMusicInformation(String condition) throws SQLException {

        java.sql.Connection connection = DriverManager.getConnection(MUSIC_STORE_URL, USERNAME, PASSWORD);

        /* turn commits off */
        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM music " + condition);

        ArrayList<HashMap<String, String>> musicItems = new ArrayList<HashMap<String, String>>();

        while (query.next()) {

            HashMap<String, String> musicTableRow = new HashMap<String, String>();

            musicTableRow.put("MUSIC_TITLE", query.getString("MUSIC_TITLE"));
            musicTableRow.put("MUSIC_ISBN", query.getString("MUSIC_ISBN"));
            musicTableRow.put("PRODUCER", query.getString("PRODUCER"));
            musicTableRow.put("GENRE", query.getString("GENRE"));
            musicTableRow.put("YEAR", query.getString("YEAR"));
            musicTableRow.put("VENDOR_ID", query.getString("VENDOR_ID"));
            musicTableRow.put("PRICE", query.getString("PRICE"));
            musicTableRow.put("QUANTITY", query.getString("QUANTITY"));

            musicItems.add(musicTableRow);

        }

        statement.close();
        return musicItems;

    }
}