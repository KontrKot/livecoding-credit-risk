package ru.livecoding.creditrisktask.models.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Входная заявка на кредит
 */
@Data
public class CreditApplication {
    private String applicationId;
    private String userId;
    private int age;
    private BigDecimal monthlyIncome;
    private BigDecimal monthlyDebt;
    private String country;
    private boolean vipClient;
}
