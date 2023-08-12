package com.yupi.springbootinit.model.dto.card;

import com.yupi.springbootinit.model.enums.CardType;
import lombok.Data;

@Data
public class CardInfoAddOrUpdateRequest {
    private Long id;
    private Long userId;
    private String cardName;
    private String cardNumber;
    private CardType cardTypeSelect;
}
