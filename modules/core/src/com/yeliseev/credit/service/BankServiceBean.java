/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.service;

import com.yeliseev.credit.core.BankBean;
import com.yeliseev.credit.entity.Credit;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service(BankService.NAME)
public class BankServiceBean implements BankService {
    @Inject
    BankBean bankBean;

    @Override
    public BigDecimal getTotalBankAmount(Credit credit) {

        return bankBean.getTotalAmount(credit.getBank().getId());
    }
}