/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.core.appfolders.creditapplication

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.UserSessionSource
import com.haulmont.thesis.core.appfolders.hazelcast.HazelcastCacheOperation
import com.haulmont.thesis.core.appfolders.predicates.ArchivePredicate
import com.haulmont.thesis.core.appfolders.predicates.MeetingDocStatusPredicate
import com.haulmont.thesis.core.appfolders.predicates.NotRegisteredDocPredicate
import com.haulmont.thesis.core.entity.hr.HrDoc
import com.haulmont.workflow.core.entity.Card
import com.hazelcast.query.Predicate
import com.hazelcast.query.Predicates

UUID userId = AppBeans.get(UserSessionSource.class).currentOrSubstitutedUserId()
List<Predicate> predicates = [
        new NotRegisteredDocPredicate(),
        Predicates.equal("substitutedCreatorId", userId),
        new ArchivePredicate(),
        new MeetingDocStatusPredicate(),
        Predicates.not(Predicates.instanceOf(HrDoc.class))
]
HazelcastCacheOperation<Card> hazelcastCacheOperation = AppBeans.getPrototype(HazelcastCacheOperation.NAME);
return hazelcastCacheOperation.cacheName("CardNewCache")
        .withAdditionalPredicates(predicates)
        .select()