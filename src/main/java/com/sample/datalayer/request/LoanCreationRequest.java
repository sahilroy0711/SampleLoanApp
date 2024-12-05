package com.sample.datalayer.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
public class LoanCreationRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Age can not be blank")
    private Integer age;
    @NotBlank(message = "PanNumber can not be blank")
    private String panNumber;
    @NotBlank(message = "Requested amount can not be blank")
    private Double requestedAmount;
    @NotBlank(message = "Eligible amount can not be blank")
    private Double eligibleAmount;
    @NotBlank(message = "LoanId can not be blank")
    private Long loanId;
}
