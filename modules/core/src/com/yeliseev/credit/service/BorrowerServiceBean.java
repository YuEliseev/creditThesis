/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.haulmont.thesis.core.entity.Bank;
import com.haulmont.thesis.core.entity.Contractor;
import com.yeliseev.credit.core.listener.BorrowerBean;
import com.yeliseev.credit.entity.CreditApplication;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(BorrowerService.NAME)
public class BorrowerServiceBean implements BorrowerService {

    @Inject
    private BorrowerBean borrowerBean;

    @Override
    public long getCountCreditApplication(CreditApplication creditApplication) {
        long result;
        Contractor borrower = creditApplication.getBorrower();
        Bank bank = creditApplication.getCredit().getBank();

        result = borrowerBean.getCountCreditApplication(borrower.getId(), bank.getId());

        return result;
    }
}