package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class
EmployeeDataDTO {

    @JsonProperty("employeeNumber")
    private int employeeNumber;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;


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
