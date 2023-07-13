/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.core;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

@Component(BankBean.NAME)
public class BankBean {
    @Inject
    private Persistence persistence;

    public static final String NAME = "credit_BankBean";

    public BigDecimal getTotalAmount(UUID bankId) {
        BigDecimal result;

        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();

            Query query = entityManager.createQuery("select sum(c.amount) from credit$Credit c " +
                    "join c.bank b where b.id = :bankId");
            query.setParameter("bankId", bankId);
            result = (BigDecimal) query.getFirstResult();

            transaction.commit();
        }

        return result != null ? result : BigDecimal.ZERO;
    }
}