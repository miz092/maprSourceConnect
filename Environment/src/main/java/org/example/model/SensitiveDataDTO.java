package com.kafka.kafkaTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;


@ToString
@Builder
@Data
public class SensitiveDataDTO {


    private final String hashedId;

    @JsonProperty("employeeNumber")
    private final int employeeNumber;
    @JsonProperty("lastName")
    private final String lastName;
    @JsonProperty("firstName")
    private final String firstName;


}
