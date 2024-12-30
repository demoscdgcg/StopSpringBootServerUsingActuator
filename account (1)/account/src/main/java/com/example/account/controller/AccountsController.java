package com.example.account.controller;
import com.example.account.IAccountService.AccountService;
import com.example.account.dto.AccountsContectInfoDto;
import com.example.account.dto.CustomerDto;
import com.example.account.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api" ,produces = (MediaType.APPLICATION_JSON_VALUE))
@Tag(name = "Accounts", description = "APIs for managing customer accounts.")
public class AccountsController {

    private static final Logger LOGGER= LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountsContectInfoDto accountsContectInfoDto;

    //url->http://localhost:8080/api/create/customer
    @Operation(
            summary = "Create a new customer",
            description = "This endpoint allows you to create a new customer by providing necessary details in the request body.",
            tags = {"Customer"}
    )
    @PostMapping("/create/customer")
    public Result createCustomer(@Valid @RequestBody CustomerDto customerDto){
        LOGGER.info("The Dto value:{}",customerDto);
        Result account = accountService.createAccount(customerDto);
        return account;
    }

    //url->http://localhost:8080/api/get/account
    @GetMapping("/get/account")
    public Result getAccountByMobileNo(@RequestParam("mobileNo")
                                           @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
                                           String mobileNo){
        Result accountByMobileNo = accountService.getAccountByMobileNo(mobileNo);
        return accountByMobileNo;
    }

    @PutMapping("/update/customer")
    public Result updateCustomer(@Valid @RequestBody CustomerDto customerDto,@RequestParam("id") long id){
     return accountService.updateCustomer(customerDto,id);
    }

    @DeleteMapping("/delete/customer")
    public Result deleteCustomer(@RequestParam("id") long id){
        return  accountService.deleteCustomer(id);
    }

    //http://localhost:9990/api/gets/profiles
   @GetMapping("/gets/profiles")
    public ResponseEntity<?> getProfile(){
        return new ResponseEntity<>(accountsContectInfoDto, HttpStatus.OK);
   }

}
