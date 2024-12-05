package com.sample.service;

import com.sample.datalayer.request.LoanCreationRequest;
import com.sample.datalayer.request.LoanStatusUpdateRequest;
import com.sample.datalayer.response.FetchLoanApiResponse;
import com.sample.datalayer.response.LoanCreationResponse;
import com.sample.model.LoanMasterData;
import com.sample.repository.LoanCreationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class LoanManagementService {
    @Autowired
    LoanCreationRepository loanCreationRepository;

    public LoanCreationResponse createLoanService(LoanCreationRequest loanCreationRequest) {
        Long loanId = loanCreationRequest.getLoanId();

        LoanMasterData loanMasterData = new LoanMasterData();
        loanMasterData.setLoanId(loanId);

        LoanCreationResponse loanCreationResponse = new LoanCreationResponse();
        try {
            loanCreationRepository.save(loanMasterData);
            loanCreationResponse.setStatus("SUCCESS");
            loanCreationResponse.setMessage("Loan created successfully in database");
        }catch (Exception e){
            log.info("Error while creating loan in DB");
            loanCreationResponse.setStatus("FAILURE");
            loanCreationResponse.setMessage("Loan creation unsuccessful");
        }

        return loanCreationResponse;

    }

    public void updateLoanStatus(LoanStatusUpdateRequest loanStatusUpdateRequest){
        Integer status = loanStatusUpdateRequest.getStatus();
        Long loanId = loanStatusUpdateRequest.getLoanId();
        LoanMasterData loanMasterData = null;

        try {
            loanMasterData = loanCreationRepository.findByLoanId(loanId);
        }catch (Exception e){
            log.error("[LoanManagementService.updateLoanStatus] :: Error while fetching data from Database");
        }

        if(loanMasterData==null){
            log.error("[LoanManagementService.updateLoanStatus] :: No data available for the given loan id.");
        }else{
            loanMasterData.setLoanStatus(status);
            loanCreationRepository.save(loanMasterData);
            log.info("[LoanManagementService.updateLoanStatus] :: Loan status successfully updated");
        }
    }

    public FetchLoanApiResponse fetchLoanData(Map<String,String> allRequestParams){
        FetchLoanApiResponse fetchLoanApiResponse = new FetchLoanApiResponse();

        if(allRequestParams.get("loanId")==null){
            fetchLoanApiResponse.setErrorMessage("Validation failed, loanId not defined");
            return fetchLoanApiResponse;
        }

        Long loanId = Long.parseLong(allRequestParams.get("loanId"));
        LoanMasterData loanMasterData = null;

        try {
            loanMasterData = loanCreationRepository.findByLoanId(loanId);
        }catch (Exception e){
            log.info("[LoanManagementService.fetchLoanData] :: Error while fetching data from Database: ",e);
        }

        if(loanMasterData==null){
            fetchLoanApiResponse.setErrorMessage("Data not found in DB");
            return fetchLoanApiResponse;
        }

        fetchLoanApiResponse.setLoanId(loanMasterData.getLoanId());
        fetchLoanApiResponse.setAge(loanMasterData.getAge());
        fetchLoanApiResponse.setName(loanMasterData.getName());
        fetchLoanApiResponse.setDueDate(loanMasterData.getDueDate());
        fetchLoanApiResponse.setDisbursedAmount(loanMasterData.getDisbursedAmount());
        fetchLoanApiResponse.setStatus(loanMasterData.getLoanStatus());
        fetchLoanApiResponse.setRateOfInterest(loanMasterData.getRateOfInterest());
        fetchLoanApiResponse.setPanNumber(loanMasterData.getPanNumber());
        fetchLoanApiResponse.setRequestedAmount(loanMasterData.getRequestedAmount());

        return fetchLoanApiResponse;
    }
}
