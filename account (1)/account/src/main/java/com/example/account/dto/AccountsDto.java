package com.example.account.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {


    private long accountNo;


    @NotEmpty(message = "accountType should not be null")
    private String accountType;

    @NotEmpty(message = "branchAddress should not be null")
    private String branchAddress;

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public @NotEmpty(message = "accountType should not be null") String getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotEmpty(message = "accountType should not be null") String accountType) {
        this.accountType = accountType;
    }

    public @NotEmpty(message = "branchAddress should not be null") String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(@NotEmpty(message = "branchAddress should not be null") String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
