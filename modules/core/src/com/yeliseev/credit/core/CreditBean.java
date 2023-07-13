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
import java.math.RoundingMode;
import java.util.UUID;

@Component(CreditBean.NAME)
public class CreditBean {
    public static final String NAME = "credit_CreditBean";
    @Inject
    private Persistence persistence;

    public void roundAmountCeiling(UUID creditId, BigDecimal amount) {

        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();

            Query query = entityManager
                    .createQuery("update credit$Credit c set c.amount = :newAmount where c.id = :creditId");
            query.setParameter("newAmount", amount.setScale(0, RoundingMode.CEILING));
            query.setParameter("creditId", creditId);

            query.executeUpdate();
            transaction.commit();
        }
    }
    public void changeAmount(UUID creditId, BigDecimal newAmount){

        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();

            Query query = entityManager
                    .createQuery("update credit$Credit c set c.amount = :newAmount where c.id = :creditId");
            query.setParameter("creditId", creditId);
            query.setParameter("newAmount", newAmount);

            query.executeUpdate();
            transaction.commit();
        }
    }
}
