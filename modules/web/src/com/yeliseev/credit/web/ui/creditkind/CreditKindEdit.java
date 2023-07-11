/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.web.ui.creditkind;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.TextField;
import com.yeliseev.credit.entity.CreditKind;

import javax.inject.Inject;
import java.util.Map;


public class CreditKindEdit<T extends CreditKind> extends AbstractEditor<T> {
    @Inject
    TextField code;
    @Inject
    UniqueNumbersService uniqueNumbersService;
    @Inject
    TimeSource timeSource;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        if (PersistenceHelper.isNew(item)){
            initCreditKindCode();
        }

    }

    private void initCreditKindCode(){

            code.setValue("CreditKind_" + (uniqueNumbersService.getNextNumber("creditKind")));
            code.setEditable(false);
    }
}