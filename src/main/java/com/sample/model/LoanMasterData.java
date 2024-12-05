package com.sample.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "loan_master_data")
public class LoanMasterData {
    @Id
    @Column(name = "loan_id")
    Long loanId;
    @Column
    String name;
    @Column
    Integer age;
    @Column(name = "`requested-amount`")
    Double requestedAmount;
    @Column(name = "`pan-number`")
    String panNumber;
    @Column(name = "`rate-of-interest`")
    String rateOfInterest;
    @Column(name = "`disbursed-amount`")
    Double disbursedAmount;
    @Column(name = "`loan-status`")
    String loanStatus;
    @Column(name = "`days-past-due`")
    Long daysPastDue;
    @Column(name = "`due-date`")
    Date dueDate;
    @Column(name = "`created-at`")
    Date createdAt;
    @Column(name = "`updated-at`")
    Date updatedAt;

}
