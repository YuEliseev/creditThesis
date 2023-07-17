
/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.core.appfolders.creditapplication

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.UserSessionSource
import com.haulmont.thesis.core.appfolders.AppFolderCardsSelector

def userId = AppBeans.get(UserSessionSource.class).currentOrSubstitutedUserId()
def procStateMap = [:]

procStateMap << ['creditProcessing' : ['Proverka','Soglasovanie','Proverka_sluzhby_bezopasnosti',
                                       'Proverka_uridicheskogo_otdela','Pogashenie_kredita',]]

return AppFolderCardsSelector.get()
        .selectCardsByProcessAndState('credit$CreditApplication', userId, procStateMap)