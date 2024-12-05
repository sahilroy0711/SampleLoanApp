package com.sample.repository;

import com.sample.model.LoanMasterData;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoanManagerRepository extends CrudRepository<LoanMasterData, Long> {

    @NonNull
    LoanMasterData findByLoanId(Long loanId);

    @NonNull
    List<LoanMasterData> findByDueDateBefore(Date currentDate);
}
