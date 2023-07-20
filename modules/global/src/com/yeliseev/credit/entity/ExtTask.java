/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.thesis.core.entity.Task;

import javax.persistence.*;

@javax.persistence.DiscriminatorValue("2200")
@Table(name = "TM_TASK")
@Entity(name = "credit$ExtTask")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "ID")
@Extends(Task.class)
public class ExtTask extends Task {
    private static final long serialVersionUID = -7606126521749843350L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_APPLICATION_ID")
    protected CreditApplication creditApplication;

    public CreditApplication getCreditApplication() {
        return creditApplication;
    }

    public void setCreditApplication(CreditApplication creditApplication) {
        this.creditApplication = creditApplication;
    }
}