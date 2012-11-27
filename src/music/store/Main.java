package music.store;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    final private static String MUSIC_STORE_URL = "jdbc:oracle:thin:@//Cncsidb01.msudenver.edu:1521/db01";
    final private static String USERNAME = "bgill9";
    final private static String PASSWORD = "W3lc0m3";

    public static void main(String[] args) {

        try {
            displayQueryResults(queryDatabase("SELECT price FROM music WHERE price < 10"));
        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Out of Bounds!: " + e);
        }
    }

    private static void displayQueryResults(ArrayList<HashMap<String, String>> musicTable) {

        System.out.println(musicTable.size());

        for (int rowNumber = 0; rowNumber < musicTable.size(); rowNumber++) {
            System.out.println(musicTable.get(rowNumber));
        }
    }

    private static ArrayList<HashMap<String, String>> queryDatabase(String query) throws SQLException {

        Statement statement = getConnection();
        ResultSet queryResult = statement.executeQuery(query);
        ResultSetMetaData queryMetaData = getQueryMetaData(queryResult);

        ArrayList<HashMap<String, String>> musicItems = new ArrayList<HashMap<String, String>>();

        // row level
        while (queryResult.next()) {

            HashMap<String, String> musicTableRow = new HashMap<String, String>();

            // column level
            for (int i = 1; i <= queryMetaData.getColumnCount(); i++) {

                String columnName = queryMetaData.getColumnName(i);

                if (columnName != null) {
                    musicTableRow.put(columnName, queryResult.getString(columnName));
                }
            }

            musicItems.add(musicTableRow);

        }

        statement.close();

        return musicItems;

    }

    private static ResultSetMetaData getQueryMetaData(ResultSet queryResult) throws SQLException {
        return queryResult.getMetaData();
    }

    private static Statement getConnection() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(MUSIC_STORE_URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        return statement;
    }
}