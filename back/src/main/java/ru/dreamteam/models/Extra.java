package ru.dreamteam.models;

import lombok.Data;

@Data
public class Extra implements Json{
    private String cardHolder;
    private String cardExpMonth;
    private String cardExpYear;
    private String senderName;
    private String receiverPhone;
    private String countryCode;
    private String clearingNumber;
    private String beneficiaryName;
    private String bankName;
    private String bic;
    private String beneficiaryStreet;
    private String beneficiaryZip;
    private String beneficiaryCity;
    private String beneficiaryState;
    private String beneficiaryCountry;
    private String branchName;
    private String branchAddress;
    private String branchCode;
    private String mobile;
    private String financialInstitutionNumber;
    private String transitNumber;
    private String paymentTransactionId;
    private String accountType;
    private String routingNumber;
    private String accountNumber;
    private String ssn;
    private String securityQuestion;
    private String securityAnswer;

}
