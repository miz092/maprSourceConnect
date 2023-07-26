package org.example;

import org.apache.kafka.common.config.Config;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Connector;
import org.apache.kafka.connect.connector.Task;
import org.example.config.MapRDBSourceConnectorConfig;
import org.example.service.MapRDBSourceTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaprDbSourceConnector extends Connector {

    private Map<String, String> configProperties;

    @Override
    public void start(Map<String, String> props) {
        configProperties = props;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return MapRDBSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {

        List<Map<String, String>> taskConfigs = new ArrayList<>();
        for (int i = 0; i < maxTasks; i++) {
            taskConfigs.add(configProperties);
        }
        return taskConfigs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {

        return MapRDBSourceConnectorConfig.configDef();
    }

    @Override
    public Config validate(Map<String, String> connectorConfigs) {

        ConfigDef configDef = MapRDBSourceConnectorConfig.configDef();
        return (Config) configDef.validate(connectorConfigs);
    }

    @Override
    public String version() {

        return "0.0.1";
    }
}
