/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.thesis.core.app.defaultprocactors.DefaultProcessActorAssignmentStrategy;
import com.haulmont.thesis.core.entity.defaultactor.TsDefaultProcActor;
import com.haulmont.workflow.core.entity.Card;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;

@Component(LegalOfficerActorStrategy.NAME)
public class LegalOfficerActorStrategy implements DefaultProcessActorAssignmentStrategy {

    public static final String NAME = "credit_LegalOfficerActorStrategy";
    @Inject
    protected DataManager dataManager;

    @Override
    public String getId() {
        return NAME;
    }

    @Nullable
    @Override
    public User getUser(TsDefaultProcActor defaultProcActor, Card card) {
        return dataManager.load(User.class)
                .query("select u from sec$User u join u.userRole ur where ur.role.name = :nameRole")
                .parameter("nameRole", "legalOfficer")
                .view(View.MINIMAL)
                .one();
    }

    @Override
    public String getCaption() {
        return "legOffPlaceHolder";
    }
}
