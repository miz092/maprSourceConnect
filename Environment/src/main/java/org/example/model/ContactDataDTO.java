package com.kafka.kafkaTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ContactDataDTO {

    private String hashedId;

    @JsonProperty("extension")
    private String extension;

    @JsonProperty("email")
    private String email;

    @JsonProperty("officeCode")
    private String officeCode;

    @JsonProperty("reportsTo")
    private int reportsTo;

    @JsonProperty("jobTitle")
    private String jobTitle;
}
