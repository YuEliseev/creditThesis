/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.math.BigDecimal;

@MetaClass(name = "credit$BankStatistic")
@NamePattern("%s|bankName")
public class BankStatistic extends BaseUuidEntity {
    private static final long serialVersionUID = 725922166169381791L;

    @MetaProperty
    protected String bankName;

    @MetaProperty
    protected BigDecimal countCredit;

    public BankStatistic(String bankName, BigDecimal countCredit) {
        this.bankName = bankName;
        this.countCredit = countCredit;
    }

    public void setCountCredit(BigDecimal countCredit) {
        this.countCredit = countCredit;
    }

    public BigDecimal getCountCredit() {
        return countCredit;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}