package com.rent.rentitnowfrontend.apis.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long transactionId;
    private LocalDateTime transactionDateAndTime;
    private Long userId;
    private BigDecimal transactionValue;
    private boolean isTransactionPayed;
    private TransactionType transactionType;
}
