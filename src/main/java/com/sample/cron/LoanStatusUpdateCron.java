package com.sample.cron;

import com.sample.model.LoanMasterData;
import com.sample.repository.LoanCreationRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class LoanStatusUpdateCron {
    @Autowired
    LoanCreationRepository loanCreationRepository;

    @Scheduled(cron = "00 00 00 * * *")
    public void runCronJob(){
        Date currentDate = new Date();
        Date expiryThreshold = DateUtils.addMonths(currentDate,6);

        List<LoanMasterData> loanMasterDataList = new ArrayList<>();

        try{
//            loanMasterDataList = loanCreationRepository.findByDueDate();
        }catch (Exception e){
            log.error("[LoanStatusUpdateCron.runCronJob] :: error while fetching data from DB");
        }

        for(LoanMasterData loanMasterData : loanMasterDataList){
//            loanMasterData.setLoanStatus();
            loanCreationRepository.save(loanMasterData);
        }
    }
}
