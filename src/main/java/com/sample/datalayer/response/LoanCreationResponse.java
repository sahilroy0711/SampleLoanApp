package com.sample.datalayer.response;

import lombok.Data;

@Data
public class LoanCreationResponse {
    String message;
    String errorMessage;
    String status;

}
