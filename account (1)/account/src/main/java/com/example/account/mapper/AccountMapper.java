package com.example.account.mapper;


import com.example.account.Model.Accounts;
import com.example.account.dto.AccountsDto;

public class AccountMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto){
        accountsDto.setAccountNo(accounts.getAccountNo());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto,Accounts accounts){
        accounts.setAccountNo(accountsDto.getAccountNo());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
