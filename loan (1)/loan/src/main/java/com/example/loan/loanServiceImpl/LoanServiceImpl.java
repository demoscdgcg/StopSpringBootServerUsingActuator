package com.example.loan.loanServiceImpl;
import com.example.loan.basicConfig.BsicUtil;
import com.example.loan.constants.LoansConstants;
import com.example.loan.dto.LoanDto;
import com.example.loan.dto.Result;
import com.example.loan.exception.LoanAllreadyExistWithTheGivenMobileNo;
import com.example.loan.exception.RescourceNotFoundException;
import com.example.loan.iLoanService.ILoanService;
import com.example.loan.mapper.LoanMapper;
import com.example.loan.model.Loan;
import com.example.loan.repository.LoanRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl extends BsicUtil implements ILoanService {

    @Autowired
    private static final Logger LOGGER= LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    private LoanRepo loanRepo;

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public Result createLoan(String mobileNumber) {
        Optional<Loan> loanWithMobileNO = loanRepo.findByMobileNumber(mobileNumber);
        if(loanWithMobileNO.isPresent()){
            throw new LoanAllreadyExistWithTheGivenMobileNo("Problrm with the Mobileno",mobileNumber);
        }
        Loan loan = loanRepo.save(createNewLoan(mobileNumber));
        return prepareResponseObject("001","Loan Created SuccessFully",loan);
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutStandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public Result featchLoan(String mobileNumber) {
        Optional<Loan> byMobileNumber = loanRepo.findByMobileNumber(mobileNumber);
        if(!byMobileNumber.isPresent()){
            throw new LoanAllreadyExistWithTheGivenMobileNo("Problem with mobileno",mobileNumber);
        }
        return prepareResponseObject("002","Record fetched successfly with that given mobile no",byMobileNumber);
    }

    /**
     *
     * @param loanDto
     * @param mobileno
     * @return
     */
    @Override
    public Result updateLoan(LoanDto loanDto, String mobileno) {
        Optional<Loan> loan = loanRepo.findByMobileNumber(mobileno);
        if(!loan.isPresent()){
            throw new RescourceNotFoundException("There is a problem","with the id",mobileno);
        }
        Loan loan2 = loan.get();
        Loan loan1 = LoanMapper.mapToLoan(loanDto);
        loan2.setTotalLoan(loan1.getTotalLoan());
        loan2.setLoanType(loan1.getLoanType());
        loan2.setLoanNumber(loan1.getLoanNumber());
        loan2.setAmountPaid(loan1.getAmountPaid());
        loan2.setOutStandingAmount(loan1.getOutStandingAmount());
        loan2.setMobileNumber(loan1.getMobileNumber());

        loanRepo.save(loan2);
        LoanDto updatedLoanDto = LoanMapper.mpaToLoanDto(loan2);
        return prepareResponseObject("003","Loan Upadated successFully",updatedLoanDto);
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    @Override
    public Result deleteLoan(String mobileNumber) {
        Optional<Loan> byMobileNumber = loanRepo.findByMobileNumber(mobileNumber);
        if(!byMobileNumber.isPresent()){
            throw new RescourceNotFoundException("There is a problem with","id","id");
        }
        loanRepo.deleteById(byMobileNumber.get().getLoanId());
        return prepareResponseObject("004","Loan deleted successfully",null);
    }
}
