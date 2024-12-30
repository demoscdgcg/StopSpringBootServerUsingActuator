package com.example.card.controller;
import com.example.card.ICardService.ICardService;
import com.example.card.dto.AccountsContectInfoDto;
import com.example.card.dto.CardDto;
import com.example.card.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/card", produces = (MediaType.APPLICATION_JSON_VALUE))
@Tag(name = "Card", description = "APIs for managing card.")
public class CardController {

    @Autowired
    private static final Logger LOGGER= LoggerFactory.getLogger(CardController.class);

    @Autowired
    private ICardService iCardService;

   @Autowired
   private AccountsContectInfoDto  accountsContectInfoDto;

    @Operation(
            summary = "Create a new Card",
            description = "This endpoint allows you to create a new loan by providing necessary details in the requstparam.",
            tags = {"Card"}
    )
    //http://localhost:8887/api/card/create
    @PostMapping("/create")
    public Result craeteCard(@RequestParam("mobileno") String mobileNo){
        Result card = iCardService.createCard(mobileNo);
        return card;
    }

    @Operation(
            summary = "Fetch a new Card",
            description = "This endpoint allows you to fetch a new card by providing necessary details in the requstparam.",
            tags = {"Card"}
    )
    //http://localhost:8887/api/card/get
    @GetMapping("/get")
    public Result getCard(@RequestParam("mobileNo") String mobileNo){
        Result result = iCardService.fetchedCard(mobileNo);
        return result;
    }

    @Operation(
            summary = "Fetch a new Card",
            description = "This endpoint allows you to update a new card by providing necessary details in the requstparam.",
            tags = {"Card"}
    )
    //http://localhost:8887/api/card/update
    @PutMapping("/update")
    public Result updateCard(@RequestBody CardDto cardDto){
        Result result = iCardService.updateCard(cardDto);
        return result;
    }

    @Operation(
            summary = "Fetch a new Card",
            description = "This endpoint allows you to delete a new card by providing necessary details in the requstparam.",
            tags = {"Card"}
    )
    //http://localhost:8887/api/card/delete
    @DeleteMapping("/delete")
    public Result deleteCard(@RequestParam("mobileNo")String mobileNO){
        Result result = iCardService.deleteCard(mobileNO);
        return result;
    }

    //http://localhost/9098/api/card/get/profiles
    @GetMapping("/get/profiles")
    public ResponseEntity<?> getProfiles(){
        return new ResponseEntity<>(accountsContectInfoDto, HttpStatus.OK);
    }

}
