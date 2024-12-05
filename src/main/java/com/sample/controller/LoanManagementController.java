package com.sample.controller;

import com.sample.datalayer.request.LoanCreationRequest;
import com.sample.datalayer.request.LoanStatusUpdateRequest;
import com.sample.datalayer.response.FetchLoanApiResponse;
import com.sample.datalayer.response.LoanCreationResponse;
import com.sample.service.LoanManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class LoanManagementController {
    @Autowired
    LoanManagementService loanManagementService;

    @PostMapping("/createLoan")
    public ResponseEntity<LoanCreationResponse> createUserLoan(@Valid @RequestBody LoanCreationRequest loanCreationRequest){
        log.info("[LoanManagementController.createUserLoan] :: hitting api with request {}",loanCreationRequest);
       LoanCreationResponse loanCreationResponse = loanManagementService.createLoanService(loanCreationRequest);
       return new ResponseEntity<>(loanCreationResponse,
                HttpStatus.OK);
    }

    @PutMapping("/updateLoanStatus")
    public void updateLoanStatus(@RequestBody LoanStatusUpdateRequest loanStatusUpdateRequest){
        log.info("[LoanManagementController.updateLoanStatus] :: hitting api");
        loanManagementService.updateLoanStatus(loanStatusUpdateRequest);
    }
    @GetMapping("/fetchLoanData")
    public ResponseEntity<FetchLoanApiResponse> fetchLoanData(@RequestParam Map<String,String> allRequestParams){
        log.info("[LoanManagementController.fetchLoanData] :: hitting api with request paramas {}",allRequestParams);
        FetchLoanApiResponse fetchLoanApiResponse = loanManagementService.fetchLoanData(allRequestParams);
        return new ResponseEntity<>(fetchLoanApiResponse,HttpStatus.OK);
    }
}
