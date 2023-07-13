/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.yeliseev.credit.entity.Credit;

import java.math.BigDecimal;

public interface BankService {
    String NAME = "credit_BankService";

    BigDecimal getTotalBankAmount(Credit credit);
}