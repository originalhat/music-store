package music.store;

import java.sql.SQLException;

public class Main {

    private static String query = "SELECT * FROM music";

    public static void main(String[] args) {
        queryDatabase();
    }

    private static void queryDatabase() {
        try {

            MusicStoreQuery newQuery = new MusicStoreQuery();

            newQuery.setCredentials("bgill9", "W3lc0m3");
            newQuery.displayQueryResults(newQuery.queryDatabase(query));

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Out of Bounds!: " + e);
        }
    }
}