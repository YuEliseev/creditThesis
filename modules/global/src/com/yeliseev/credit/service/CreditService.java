/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.yeliseev.credit.entity.Credit;

import java.math.BigDecimal;

public interface CreditService {
    String NAME = "credit_CreditService";

    void roundAmountCeiling(Credit credit);

    BigDecimal getTotalCreditAmount(Credit credit);

    void changeCreditAmount(Credit credit, BigDecimal newAmount);
}