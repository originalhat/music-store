package music.store;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    final static String MUSIC_STORE_URL = "jdbc:oracle:thin:@//Cncsidb01.msudenver.edu:1521/db01";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        java.sql.Connection musicStoreConnection;
        musicStoreConnection = DriverManager.getConnection(MUSIC_STORE_URL, "bgill9", "W3lc0m3");

        /* turn commits off */
        musicStoreConnection.setAutoCommit(false);

        Statement statement = musicStoreConnection.createStatement();

        ResultSet connectionInfo = statement.executeQuery("SELECT BANNER FROM SYS.V_$VERSION");
        while (connectionInfo.next()) {
            System.out.println(connectionInfo.getString(1));
        }

        ResultSet contactsQuery = statement.executeQuery("SELECT name FROM contact");
        while (contactsQuery.next()) {
            String contactName = contactsQuery.getString("NAME");
            /* do things with 'contactName', or other fields */
        }

        statement.close();

    }
}