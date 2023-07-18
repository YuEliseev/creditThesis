/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.core.appfolders.creditapplication

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.UserSessionSource
import com.haulmont.thesis.core.appfolders.hazelcast.HazelcastCacheOperation

def userId = AppBeans.get(UserSessionSource.class).currentOrSubstitutedUserId()

def cardsWithAssignmentsCnt = 0
def cardsWithCardInfoCnt = 0
def procStateMap = [:]

procStateMap << ['creditProcessing' : ['Proverka','Soglasovanie','Proverka_sluzhby_bezopasnosti',
                                       'Proverka_uridicheskogo_otdela','Pogashenie_kredita',]]

HazelcastCacheOperation operation = AppBeans.get(HazelcastCacheOperation.class)
counterObject = operation.cardMetaClass('credit$CreditApplication')
        .userInCurrentActors(userId)
        .processCodesAndStates(procStateMap)
        .withHasCardInfoPredicate(userId)
        .submitToCount()

cardsWithAssignmentsCnt = counterObject.assignmentsQuantity
cardsWithCardInfoCnt = counterObject.cardInfoQuantity
counterObject.folder = folder

style = (cardsWithCardInfoCnt > 0) ? 'cardremind' : null
return  cardsWithAssignmentsCnt