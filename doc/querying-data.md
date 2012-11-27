# MusicStoreQuery

HOW-TO: Accessing data from the "Music Store".

## Issuing a Query

```java
MusicStoreQuery newQuery = new MusicStoreQuery();

queryArrayList = newQuery.queryDatabase("SELECT price FROM music WHERE price < 10")
```

## Viewing results (debug / basic)

```java
newQuery.displayQueryResults(queryArrayList);
```

## Data structure Layout

Two-dimmensional-ish array...

- `ArrayList` is for columns
  - labeled numerically
  - e.g. `musicTable.get(0)` retrieves row 1
- `HashMap` is for rows
  - each row attribute is mapped to a hash key
  - e.g. `{MUSIC_TILE=Oops I Did It Again}`

## Accessing Specific Data

NOTE: try to push any of the data restriction / limitations functionality into the query, rather than the result.

This example shows how to retrieve the `PRICE` column for all rows.

```java
HashMap<String, String> musicTableRow = new HashMap<String, String>();

for (int i = 1; i <= queryMetaData.getColumnCount(); i++) {

    String columnName = queryMetaData.getColumnName(i);

    if (columnName != null) {
        musicTableRow.put(columnName, queryResult.getString(columnName).get("PRICE"));
    }
}
```