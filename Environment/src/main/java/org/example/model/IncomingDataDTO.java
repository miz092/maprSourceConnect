package com.kafka.kafkaTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingDataDTO {

    @JsonProperty("database")
    private String database;

    @JsonProperty("table")
    private String table;
    @JsonProperty("type")
    private String type;
    @JsonProperty("ts")
    private Long ts;
    @JsonProperty("xid")
    private Long xid;
    @JsonProperty("commit")
    private Boolean commit;

    @JsonProperty("data")
    private EmployeeDataDTO data;

    @JsonProperty("old")
    private Map<String, Object> old;
}
