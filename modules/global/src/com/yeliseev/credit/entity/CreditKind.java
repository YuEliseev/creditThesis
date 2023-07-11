/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "CREDIT_CREDIT_KIND")
@Entity(name = "credit$CreditKind")
@EnableRestore
@TrackEditScreenHistory
@NamePattern("%s|name")
public class CreditKind extends StandardEntity {

    private static final long serialVersionUID = -3228633681087285433L;

    @Column(name = "CODE", length = 50)
    protected String code;

    @Column(name = "NAME", length = 250)
    protected String name;

    @Column(name = "DESCRIPTION")
    @Lob
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}