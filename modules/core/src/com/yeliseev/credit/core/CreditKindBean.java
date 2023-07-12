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

@Component(CreditKindBean.NAME)
public class CreditKindBean {
    @Inject
    private Persistence persistence;
    public static final String NAME = "credit_CreditKindBean";

    public BigDecimal getTotalAmount(UUID creditKindId) {
        BigDecimal result;

        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();

            Query query = entityManager.createQuery("select sum(c.amount) from credit$Credit c " +
                    "join c.creditKind k where k.id = :creditKindId");
            query.setParameter("creditKindId", creditKindId);
            result = (BigDecimal) query.getFirstResult();

            transaction.commit();
        }

        return result != null ? result : BigDecimal.ZERO;
    }
    public void changeAmount(UUID creditKindId, BigDecimal newAmount){

        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();

            Query query = entityManager
                    .createQuery("update credit$Credit c set c.amount = :newAmount where c.id = :creditId");
            query.setParameter("creditId", creditKindId);
            query.setParameter("newAmount", newAmount);

            query.executeUpdate();
            transaction.commit();
        }
    }
}