package music.store;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try {

            MusicStoreQuery newQuery = new MusicStoreQuery();

            newQuery.displayQueryResults(newQuery.queryDatabase("SELECT price FROM music WHERE price < 10"));

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index Out of Bounds!: " + e);
        }
    }

}