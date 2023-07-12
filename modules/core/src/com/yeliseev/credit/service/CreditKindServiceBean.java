/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.yeliseev.credit.core.CreditKindBean;
import com.yeliseev.credit.entity.Credit;
import com.yeliseev.credit.entity.CreditKind;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service(CreditKindService.NAME)
public class CreditKindServiceBean implements CreditKindService {
    @Inject
    private CreditKindBean creditKindBean;

    @Override
    public BigDecimal getTotalCreditAmount(CreditKind creditKind) {

        return creditKindBean.getTotalAmount(creditKind.getId());
    }

    @Override
    public void changeCreditAmount(CreditKind creditKind, BigDecimal newAmount) {

        creditKindBean.changeAmount(creditKind.getId(), newAmount);
    }
}