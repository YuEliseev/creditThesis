/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.yeliseev.credit.entity.Credit;
import com.yeliseev.credit.entity.CreditKind;

import java.math.BigDecimal;

public interface CreditKindService {
    String NAME = "credit_CreditKindService";

    BigDecimal getTotalCreditAmount(CreditKind creditKind);

    void changeCreditAmount(CreditKind creditKind, BigDecimal newAmount);
}