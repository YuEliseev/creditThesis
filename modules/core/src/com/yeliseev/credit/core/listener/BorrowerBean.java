/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.core.listener;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.UUID;


@Component(BorrowerBean.NAME)
public class BorrowerBean {
    public static final String NAME = "credit_BorrowerBean";

    @Inject
    private Persistence persistence;

    public long getCountCreditApplication(UUID borrowerId, UUID bankId){
        Long result;

        try(Transaction transaction = persistence.createTransaction()){
            EntityManager entityManager = persistence.getEntityManager();

            Query query = entityManager
                    .createQuery("select count(ca) from credit$CreditApplication ca " +
                            "join ca.borrower bor " +
                            "join ca.credit.bank bank " +
                            "where bor.id = :borrowerId " +
                            "and bank.id = :bankId");
            query.setParameter("borrowerId", borrowerId);
            query.setParameter("bankId", bankId);
            result = (Long) query.getFirstResult();

            transaction.commit();
        }
        return result != null ? result : 0;
    }
}