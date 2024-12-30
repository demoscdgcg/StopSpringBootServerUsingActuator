package com.example.card.mapper;

import com.example.card.dto.CardDto;
import com.example.card.model.Card;

public class CardMapper {

    public static Card mapToCard(CardDto cardDto){
        Card card=new Card();
        card.setCardType(cardDto.getCardType());
        card.setCardNo(cardDto.getCardNumber());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setMobileNo(cardDto.getMobileNo());
        card.setTotalLimit(cardDto.getTotalLimit());
        return card;
    }

    public static CardDto mapToCardDto(Card  card){
        CardDto cardDto=new CardDto();
        cardDto.setCardNumber(card.getCardNo());
        cardDto.setCardType(card.getCardType());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setMobileNo(card.getMobileNo());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setAvailableAmount(cardDto.getAvailableAmount());
        return cardDto;
    }
}
