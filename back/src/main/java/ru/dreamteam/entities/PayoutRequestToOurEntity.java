package ru.dreamteam.entities;

import lombok.Data;

@Data
public class PayoutRequestToOurEntity implements Json {
    private String cardNumber;
    private String amount;

    public boolean isEmpty(){
        return cardNumber == null ||
                amount == null ||
                !amount.matches("^\\d+\\.\\d{2}$") ||
                //they said it would be only for cards
                !cardNumber.matches("^\\d{16}$");
    }
}
