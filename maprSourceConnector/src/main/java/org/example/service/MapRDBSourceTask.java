package org.example.service;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.example.config.MapRDBSourceConnectorConfig;
import org.example.connection.MapRDBConnectionManager;
import org.ojai.Document;
import org.ojai.DocumentStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapRDBSourceTask extends SourceTask {

    private String topic;
    private String maprDBTablePath;

    private MapRDBConnectionManager connectionManager;

    @Override
    public String version() {
        return "1.0";
    }


    ////checkelni, ha több topic van, hogyan kell ezt intézni
    @Override
    public void start(Map<String, String> props) {
        MapRDBSourceConnectorConfig config = new MapRDBSourceConnectorConfig(MapRDBSourceConnectorConfig.configDef(), props);
        topic = config.getString(MapRDBSourceConnectorConfig.TOPIC_CONFIG);
        maprDBTablePath = config.getString(MapRDBSourceConnectorConfig.MAPR_DB_TABLE_PATH_CONFIG);

        String maprDBCluster = "mapr-cluster-url"; // Replace with the correct MapR cluster URL
        connectionManager = new MapRDBConnectionManager(maprDBCluster);
    }
//    offset.storage.file.filename=/tmp/connect.offsets
//    offset.flush.interval.ms=10000
    //offset mentési helye, kérdéses, hogy a kafkára marad-e ez a feladat


    //// recordokon iterálni, kategorizálni őket. kérdéses, hogy a topic-ok hogyan jönnek létre. több topic, egy topic, több partíció?
    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        List<SourceRecord> records = new ArrayList<>();

        try (DocumentStream stream = connectionManager.executeQuery(maprDBTablePath)) {
            for (Document document : stream) {
                String key = document.getString("_id");
                String value = document.asJsonString();
                SourceRecord record = new SourceRecord(null, null, this.topic, null, null, key, null, value);
                records.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    @Override
    public void stop() {
        if (connectionManager != null) {
            connectionManager.closeConnection();
        }
    }
}
