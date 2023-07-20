/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.haulmont.thesis.core.entity.Individual;
import com.yeliseev.credit.entity.CreditApplication;

import java.util.UUID;

public interface BorrowerService {
    String NAME = "credit_BorrowerService";

    long getCountCreditApplication(CreditApplication creditApplication);

    long getTotalCreditApplication(UUID borrower);
}