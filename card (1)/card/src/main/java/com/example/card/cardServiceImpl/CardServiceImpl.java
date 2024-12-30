package com.example.card.cardServiceImpl;
import com.example.card.ICardService.ICardService;
import com.example.card.basicConfig.BsicUtil;
import com.example.card.constants.CardsConstants;
import com.example.card.dto.CardDto;
import com.example.card.dto.Result;
import com.example.card.exception.CardNotFoundException;
import com.example.card.exception.RescourceNotFoundException;
import com.example.card.model.Card;
import com.example.card.repository.CardRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceImpl extends BsicUtil implements ICardService {

    @Autowired
    private static final Logger LOGGER= LoggerFactory.getLogger(CardServiceImpl.class);

    @Autowired
    private CardRepo cardRepo;


    /**
     *
     * @param mobileNo
     * @return
     */
    @Override
    public Result createCard(String mobileNo) {
        Optional<Card> cardDetailsBasedOnMobileNo = cardRepo.findByMobileNo(mobileNo);
        if(cardDetailsBasedOnMobileNo.isPresent()){
            throw new CardNotFoundException("There is a problem with the mobileNo",mobileNo);
        }
        Card card = cardRepo.save(createNewCard(mobileNo));
        return prepareResponseObject("001","Card Created Successfully",card);
    }

    /**
     *
     * @param mobileNumber
     * @return
     */
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNo(Long.toString(randomCardNumber));
        newCard.setMobileNo(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     *
     * @param mobileNo
     * @return
     */
    @Override
    public Result fetchedCard(String mobileNo) {
        Optional<Card> byMobileNo = cardRepo.findByMobileNo(mobileNo);
        if(!byMobileNo.isPresent()){
            throw new CardNotFoundException("There is a problem with the mobileNo",mobileNo);
        }
//        Optional<Card> get = cardRepo.findById(byMobileNo.get().getCardId());
        return prepareResponseObject("002","Record fetched successfully",byMobileNo);
    }

    /**
     *
     * @param cardDto
     * @return
     */
    @Override
    public Result updateCard(CardDto cardDto) {
        Optional<Card> byMobileNo = cardRepo.findByMobileNo(cardDto.getMobileNo());
        if(!byMobileNo.isPresent()){
            throw new CardNotFoundException("There is a problem with mobileno",cardDto.getMobileNo());
        }
        Optional<Card> byId = cardRepo.findById(byMobileNo.get().getCardId());
        if(!byId.isPresent()){
           throw new RescourceNotFoundException("There is a problem","with mobileNo",cardDto.getMobileNo());
        }
        Card card = byId.get();
        card.setCardType(cardDto.getCardType());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setTotalLimit(cardDto.getTotalLimit());
        card.setMobileNo(cardDto.getMobileNo());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setCardNo(cardDto.getCardNumber());
        Card save = cardRepo.save(card);
        return prepareResponseObject("003","Records Updated SuccessFully",save);
    }

    /**
     *
     * @param mobileNo
     * @return
     */
    @Override
    public Result deleteCard(String mobileNo) {
        Optional<Card> byMobileNo = cardRepo.findByMobileNo(mobileNo);
        if(!byMobileNo.isPresent()){
            throw new CardNotFoundException("There is a problem mobile",mobileNo);
        }
        cardRepo.deleteById(byMobileNo.get().getCardId());
        return prepareResponseObject("005","Record deleted successfully",null);
    }
}
