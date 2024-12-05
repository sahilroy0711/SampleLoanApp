package com.sample.datalayer.request;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class LoanCreationRequest {
    @NonNull
    private Long loanId;
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NonNull
    private Integer age;
    @NotBlank(message = "PanNumber can not be blank")
    private String panNumber;
    @NonNull
    private Double requestedAmount;
    private Double eligibleAmount;
    private Date dueDate;
    private Date createdAt;
    private String loanStatus;
    private String rateOfInterest;
}
