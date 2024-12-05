package com.sample.datalayer.response;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class FetchLoanApiResponse {
    Integer id;
    Long loanId;
    String name;
    Integer age;
    Double requestedAmount;
    String panNumber;
    String rateOfInterest;
    Double disbursedAmount;
    String status;
    Date dueDate;
    String errorMessage;
}
