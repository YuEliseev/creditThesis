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
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.thesis.core.entity.Bank;
import com.yeliseev.credit.entity.BankStatistic;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component(BankBean.NAME)
public class BankBean {
    @Inject
    protected Persistence persistence;
    @Inject
    protected DataManager dataManager;
    @Inject
    protected Metadata metadata;

    public static final String NAME = "credit_BankBean";

    public BigDecimal getTotalAmount(UUID bankId) {
        BigDecimal result;

        try (Transaction transaction = persistence.createTransaction()) {
            EntityManager entityManager = persistence.getEntityManager();

            Query query = entityManager.createQuery("select sum(c.amount) from credit$Credit c " +
                    "join c.bankName b where b.id = :bankId");
            query.setParameter("bankId", bankId);
            result = (BigDecimal) query.getFirstResult();

            transaction.commit();
        }

        return result != null ? result : BigDecimal.ZERO;
    }

    public List<BankStatistic> getBankStatistic(){
        List<BankStatistic> resultList = new ArrayList<>();

            try(Transaction transaction = persistence.createTransaction()){
                EntityManager entityManager = persistence.getEntityManager();

                Query query = entityManager.createQuery("select c.bank from credit$Credit c", Bank.class);

                List<Bank> banks = query.getResultList();

                for (Bank bank : banks) {
                    BankStatistic bankStatistic = metadata.create(BankStatistic.class);

                    bankStatistic.setBankName(bankStatistic.getBankName());
                    bankStatistic.setCountCredit(getTotalAmount(bank.getId()));
                    resultList.add(bankStatistic);
                }

                transaction.commit();
            }

        return resultList;
    }
}