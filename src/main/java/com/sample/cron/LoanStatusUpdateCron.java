package com.sample.cron;

import com.sample.model.LoanMasterData;
import com.sample.repository.LoanManagerRepository;
import lombok.extern.slf4j.Slf4j;
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
    LoanManagerRepository loanManagerRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void runCronJob(){
        Date currentDate = new Date();
        List<LoanMasterData> loanMasterDataList = new ArrayList<>();

        try{
            loanMasterDataList = loanManagerRepository.findByDueDateBefore(currentDate);
        }catch (Exception e){
            log.error("[LoanStatusUpdateCron.runCronJob] :: error while fetching data from DB");
        }

        for(LoanMasterData loanMasterData : loanMasterDataList){
            long daysOverDue = currentDate.getTime()-loanMasterData.getDueDate().getTime()/24*60*60*1000;
            if(daysOverDue>0) {
                loanMasterData.setLoanStatus("OVERDUE");
                loanMasterData.setDaysPastDue(daysOverDue);
            }
            loanManagerRepository.save(loanMasterData);
        }
    }
}
