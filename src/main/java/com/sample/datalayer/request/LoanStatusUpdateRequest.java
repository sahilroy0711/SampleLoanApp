package com.sample.datalayer.request;

import lombok.Data;

@Data
public class LoanStatusUpdateRequest {
    private Long loanId;
    private String status;
}
