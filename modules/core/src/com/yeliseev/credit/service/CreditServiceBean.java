/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.yeliseev.credit.core.CreditBean;
import com.yeliseev.credit.entity.Credit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service(CreditService.NAME)
public class CreditServiceBean implements CreditService {
    @Inject
    CreditBean creditBean;

    @Override
    public void roundAmountCeiling(Credit credit) {

        if (!(credit.getCreditKind() == null)) {
            if (credit.getCreditKind().getName().equals("Реструктуризация")) {
                creditBean.roundAmountCeiling(credit.getId(), credit.getAmount());
            }
        }
    }

    @Override
    public BigDecimal getTotalCreditAmount(Credit credit) {

        return creditBean.getTotalAmount(credit.getCreditKind().getId());
    }

    @Override
    public void changeCreditAmount(Credit credit, BigDecimal newAmount) {

        creditBean.changeAmount(credit.getId(), newAmount);
    }
}