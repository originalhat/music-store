package music.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MusicStoreQuery {

    final private String MUSIC_STORE_URL = "jdbc:oracle:thin:@//Cncsidb01.msudenver.edu:1521/db01";
    private String USERNAME;
    private String PASSWORD;

    public void updateQuery(String query) throws SQLException {
        Statement statement = getConnectionStatement();
        statement.executeUpdate(query);
    }

    public ArrayList<HashMap<String, String>> queryDatabase(String query) throws SQLException {

        Statement statement = getConnectionStatement();
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

    public void displayQueryResults(ArrayList<HashMap<String, String>> musicTable) {
        for (int rowNumber = 0; rowNumber < musicTable.size(); rowNumber++) {
            System.out.println(musicTable.get(rowNumber));
        }
    }

    private ResultSetMetaData getQueryMetaData(ResultSet queryResult) throws SQLException {
        return queryResult.getMetaData();
    }

    public Statement getConnectionStatement() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(MUSIC_STORE_URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        return statement;
    }

    public Connection getConnection() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection(MUSIC_STORE_URL, USERNAME, PASSWORD);
        return connection;
    }

    public boolean testConnecton() throws SQLException {
        ResultSet connectionInfo = getConnectionStatement().executeQuery("SELECT BANNER FROM SYS.V_$VERSION");

        if (connectionInfo.next())
            return true;
        else
            return false;
    }

    public void setCredentials(String username, String password) {
        USERNAME = username;
        PASSWORD = password;
    }
}
