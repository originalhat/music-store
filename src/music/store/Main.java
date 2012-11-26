package music.store;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    final static String MUSIC_STORE_URL = "jdbc:oracle:thin:@//Cncsidb01.msudenver.edu:1521/db01";
    final static String USERNAME = "bgill9";
    final static String PASSWORD = "W3lc0m3";

    public static void main(String[] args) {

        ArrayList<String[]> musicItems = null;

        try {
            musicItems = selectMusic("WHERE MUSIC_TITLE='ESGFILXD'");
            // System.out.println(musicItems.get(0)[0]); // testing / example
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Out of Bounds!: " + e);
        }
    }

    private static ArrayList<String[]> selectMusic(String condition) throws SQLException {

        java.sql.Connection connection = DriverManager.getConnection(MUSIC_STORE_URL, USERNAME, PASSWORD);

        /* turn commits off */
        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("SELECT * FROM music " + condition);

        /*
         * adding a 2 dimensional array(list); unlimited rows, fixed column (9)
         * width; holds the extracted contents from the 'music' table
         */
        ArrayList<String[]> musicItems = new ArrayList<String[]>();
        String[] rowAttr = new String[9];

        while (query.next()) {
            rowAttr[0] = query.getString("MUSIC_TITLE");
            rowAttr[1] = query.getString("MUSIC_ISBN");
            rowAttr[2] = query.getString("MUSIC_TYPE");
            rowAttr[3] = query.getString("PRODUCER");
            rowAttr[4] = query.getString("GENRE");
            rowAttr[5] = query.getString("YEAR");
            rowAttr[6] = query.getString("VENDOR_ID");
            rowAttr[7] = query.getString("PRICE");
            rowAttr[8] = query.getString("QUANTITY");

            musicItems.add(rowAttr);

        }

        statement.close();

        return musicItems;

    }
}