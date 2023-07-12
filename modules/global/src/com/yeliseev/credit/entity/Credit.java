/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import com.haulmont.cuba.core.global.DdlGeneration;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.entity.Bank;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.core.entity.TsCard;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@DdlGeneration(unmappedColumns = {"CREATE_TS"},
        unmappedConstraints = {"IDX_CREDIT_CREDIT_CREATE_TS"})
@DiscriminatorValue("2000")
@Table(name = "CREDIT_CREDIT")
@EnableRestore
@TrackEditScreenHistory
@Entity(name = "credit$Credit")
@Listeners("credit_CreditListener")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@NamePattern("%s|creditKind")
public class Credit extends TsCard {
    private static final long serialVersionUID = 723129079835793532L;

    @Column(name = "NUMBER_", length = 50)
    protected String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_MANAGER_ID")
    protected User bankManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDI_KIND_ID")
    protected CreditKind creditKind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    protected Bank bank;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_")
    protected Date date;

    @Column(name = "AMOUNT")
    protected BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GUARANTOR_ID")
    protected Individual guarantor;

    public User getBankManager() {
        return bankManager;
    }

    public void setBankManager(User bankManager) {
        this.bankManager = bankManager;
    }

    public Individual getGuarantor() {
        return guarantor;
    }

    public void setGuarantor(Individual guarantor) {
        this.guarantor = guarantor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public CreditKind getCreditKind() {
        return creditKind;
    }

    public void setCreditKind(CreditKind creditKind) {
        this.creditKind = creditKind;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}