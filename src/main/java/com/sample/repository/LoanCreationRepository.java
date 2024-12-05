package com.sample.repository;

import com.sample.model.LoanMasterData;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanCreationRepository extends CrudRepository<LoanMasterData, Integer> {

    @NonNull
    LoanMasterData findByLoanId(Long loanId);

    @NonNull
    List<LoanMasterData> findByDueDate(Long dueDate);
}
