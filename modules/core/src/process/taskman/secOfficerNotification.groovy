package process.taskman
/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

import com.haulmont.cuba.core.EntityManager
import com.haulmont.cuba.core.Persistence
import com.haulmont.cuba.core.Query
import com.haulmont.cuba.core.Transaction
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.security.entity.User
import com.haulmont.thesis.core.entity.SimpleDoc
import  com.haulmont.workflow.core.entity.CardInfo
import com.haulmont.cuba.core.global.Metadata

SimpleDoc card = card
Persistence persistence = persistence()
Transaction tx = transaction()
final procCode = "Служба Безопасности"
Metadata metadata=AppBeans.get(Metadata.NAME)
CardInfo ci = metadata.create(CardInfo.class)

try {
    EntityManager em = persistence.getEntityManager()
    card = em.reload(card, "edit")
    Query query = em.createQuery("select cr.user from wf\$CardRole cr where cr.card.id = :cardId and cr.code = :code")
            .setParameter("cardId", card.id)
            .setParameter("code",procCode)
    User user = query.getFirstResult() as User
    if (user != null) {
        user = em.reload(user, "generic-lookup")
        card.getDescription()
        ci.setType(5)
        ci.setCard(card)
        ci.setUser(user)
        ci.setDescription(card.getDescription()+" - Обратите внимание")
        em.persist(ci)
    }
    tx.commit()
} finally {
    tx.end()
}