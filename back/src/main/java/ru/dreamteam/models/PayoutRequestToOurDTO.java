package ru.dreamteam.models;

import lombok.Data;

@Data
public class PayoutRequestToOurDTO implements Json {
    private String cardNumber;
    private String amount;

    public boolean isEmpty(){
        return cardNumber == null ||
                amount == null ||
//                !amount.matches("^\\d+\\.\\d{2}$") ||
                //they said it would be only for cards
                !cardNumber.matches("^\\d{16}$");
    }
}
