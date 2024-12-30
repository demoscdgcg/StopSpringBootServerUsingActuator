package com.example.loan.iLoanService;

import com.example.loan.dto.LoanDto;
import com.example.loan.dto.Result;

public interface ILoanService {

    /**
     *
     * @param mobileNumber
     * @return
     */
    public Result createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber
     * @return
     */
    public Result featchLoan(String mobileNumber);


    /**
     *
     * @param loanDto
     * @return
     */
    public Result updateLoan(LoanDto loanDto, String mobileno);


    /**
     *
     * @param mobileNumber
     * @return
     */
    public Result deleteLoan(String mobileNumber);
}
