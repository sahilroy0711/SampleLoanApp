package com.sample.service;

import com.sample.datalayer.request.LoanCreationRequest;
import com.sample.datalayer.request.LoanStatusUpdateRequest;
import com.sample.datalayer.response.FetchLoanApiResponse;
import com.sample.datalayer.response.LoanCreationResponse;
import com.sample.model.LoanMasterData;
import com.sample.repository.LoanManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class LoanManagementService {
    @Autowired
    LoanManagerRepository loanManagerRepository;

    public LoanCreationResponse createLoanService(LoanCreationRequest loanCreationRequest) {
        LoanMasterData loanMasterData = createLoanMasterData(loanCreationRequest);
        LoanCreationResponse loanCreationResponse = new LoanCreationResponse();
        try {
            loanManagerRepository.save(loanMasterData);
            loanCreationResponse.setStatus("SUCCESS");
            loanCreationResponse.setMessage("Loan created successfully in database");
            log.info("[LoanManagementService.createLoanService] :: Loan created successfully for request {} ",loanCreationRequest);
        }catch (Exception e){
            log.info("[LoanManagementService.createLoanService] :: Error while creating loan in DB error : ",e);
            loanCreationResponse.setStatus("FAILURE");
            loanCreationResponse.setMessage("Loan creation unsuccessful");
        }

        return loanCreationResponse;
    }

    public void updateLoanStatus(LoanStatusUpdateRequest loanStatusUpdateRequest){
        String status = loanStatusUpdateRequest.getStatus();
        Long loanId = loanStatusUpdateRequest.getLoanId();
        LoanMasterData loanMasterData = null;

        try {
            loanMasterData = loanManagerRepository.findByLoanId(loanId);
        }catch (Exception e){
            log.error("[LoanManagementService.updateLoanStatus] :: Error while fetching data from Database");
        }

        if(loanMasterData==null){
            log.error("[LoanManagementService.updateLoanStatus] :: No data available for the given loan id.");
        }else{
            loanMasterData.setLoanStatus(status);
            loanManagerRepository.save(loanMasterData);
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
            loanMasterData = loanManagerRepository.findByLoanId(loanId);
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

    private LoanMasterData createLoanMasterData(LoanCreationRequest loanCreationRequest){
        log.info("creating loan data {}",loanCreationRequest);
        LoanMasterData loanMasterData = new LoanMasterData();
        loanMasterData.setLoanId(loanCreationRequest.getLoanId());
        loanMasterData.setLoanStatus(loanCreationRequest.getLoanStatus());
        loanMasterData.setName(loanCreationRequest.getName());
        loanMasterData.setAge(loanCreationRequest.getAge());
        loanMasterData.setPanNumber(loanCreationRequest.getPanNumber());
        loanMasterData.setDueDate(loanCreationRequest.getDueDate());
        loanMasterData.setRequestedAmount(loanCreationRequest.getRequestedAmount());
        loanMasterData.setRateOfInterest(loanCreationRequest.getRateOfInterest());
        loanMasterData.setCreatedAt(loanCreationRequest.getCreatedAt());
        loanMasterData.setUpdatedAt(new Date());
        log.info("loanMasterData {}",loanMasterData);
        return loanMasterData;
    }
}
