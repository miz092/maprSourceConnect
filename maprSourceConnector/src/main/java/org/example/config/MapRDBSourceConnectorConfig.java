package org.example.config;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class MapRDBSourceConnectorConfig extends AbstractConfig {

    public static final String TOPIC_CONFIG = "topic";
    public static final String MAPR_DB_TABLE_PATH_CONFIG = "table.path";
//more config?
    public MapRDBSourceConnectorConfig(ConfigDef config, Map<String, String> props) {
        super(config, props);
    }

    public static ConfigDef configDef() {
        return new ConfigDef()
                .define(TOPIC_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH,
                        "The Kafka topic to which data will be written.")
                .define(MAPR_DB_TABLE_PATH_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH,
                        "The path of the MapR-DB table to read data from.");
        // more config?
    }
}
