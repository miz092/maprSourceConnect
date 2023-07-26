package org.example.connection;

import org.ojai.DocumentStream;
import org.ojai.store.Connection;
import org.ojai.store.DriverManager;
import org.ojai.store.Query;

public class MapRDBConnectionManager {

    private final Connection connection;

    public MapRDBConnectionManager(String maprDBCluster) {
        try {
            connection = DriverManager.getConnection(maprDBCluster);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish MapR-DB connection!");
        }
    }

    public DocumentStream executeQuery(String tableName) {

        final Query query = connection.newQuery()
                .select("_id", "ts")
                .orderBy("ts")
                .offset(2)
                .limit(1)
                .build();

        return  connection.getStore(tableName).find(query);

    }

    public void closeConnection() {
        connection.close();
    }
}
