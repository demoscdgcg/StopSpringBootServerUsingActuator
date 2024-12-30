package com.example.card.ICardService;

import com.example.card.dto.CardDto;
import com.example.card.dto.Result;

public interface ICardService {

    /**
     *
     * @param mobileNo
     * @return
     */
    public Result createCard(String mobileNo);

    /**
     *
     * @param mobileNo
     * @return
     */
    public Result fetchedCard(String mobileNo);

    /**
     *
     * @param cardDto
     * @return
     */
    public Result updateCard(CardDto cardDto);

    /**
     *
     * @param mobileNo
     * @return
     */
    public Result deleteCard(String mobileNo);
}
