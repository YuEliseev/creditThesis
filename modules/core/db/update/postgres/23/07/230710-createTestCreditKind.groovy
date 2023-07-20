/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

import com.haulmont.cuba.core.EntityManager
import com.haulmont.cuba.core.Persistence
import com.haulmont.cuba.core.Transaction
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Metadata
import com.yeliseev.credit.entity.CreditKind

postUpdate.add({
    Persistence persistence = AppBeans.get(Persistence.NAME)
    Metadata metadata = AppBeans.get(Metadata.class)
    Transaction tx = persistence.createTransaction()

    try{
        EntityManager em = persistence.getEntityManager()

        CreditKind creditKind1 = metadata.create(CreditKind.class)
        CreditKind creditKind2 = metadata.create(CreditKind.class)
        CreditKind creditKind3 = metadata.create(CreditKind.class)
        CreditKind creditKind4 = metadata.create(CreditKind.class)
        CreditKind creditKind5 = metadata.create(CreditKind.class)
        CreditKind creditKind6 = metadata.create(CreditKind.class)

        creditKind1.name = "Ипотека"
        creditKind1.code = "CreditKind_1"
        creditKind2.name = "Кредит под залог недвижимости"
        creditKind2.code = "CreditKind_2"
        creditKind3.name = "Автокредит"
        creditKind3.code = "CreditKind_3"
        creditKind4.name = "Потребительские"
        creditKind4.code = "CreditKind_4"
        creditKind5.name = "Рефинансирование"
        creditKind5.code = "CreditKind_5"
        creditKind6.name = "Реструктуризация"
        creditKind6.code = "CreditKind_6"

        em.persist(creditKind1)
        em.persist(creditKind2)
        em.persist(creditKind3)
        em.persist(creditKind4)
        em.persist(creditKind5)
        em.persist(creditKind6)

        tx.commit()
    }finally{
        tx.end()
    }
})
