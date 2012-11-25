package music.store;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    final static String MUSIC_STORE_URL = "jdbc:oracle:thin:@//Cncsidb01.msudenver.edu:1521/db01";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        java.sql.Connection conn = DriverManager.getConnection(MUSIC_STORE_URL, "<username>", "<password>");

        conn.setAutoCommit(false);

        Statement stmt = conn.createStatement();
        ResultSet connectionInfo = stmt.executeQuery("SELECT BANNER FROM SYS.V_$VERSION");

        while (connectionInfo.next()) {
            System.out.println(connectionInfo.getString(1));
        }

        stmt.close();

    }
}