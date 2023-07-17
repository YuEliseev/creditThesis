/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.web.ui.resolution;

import com.drew.metadata.Metadata;
import com.haulmont.cuba.gui.components.ActionsHolder;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.workflow.gui.app.base.ResolutionForm;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.Map;

public class ExtResolutionForm extends ResolutionForm {
    @Inject
    protected TextField<String> repaymentAmount;

    @Override
    public void init(final Map<String, Object> params) {
        super.init(params);

        if(StringUtils.equals((String) params.get("activity"), "Pogashenie_kredita")){
            ActionsHolder actionsHolder = this.frame;

            initRepaymentAmountField(actionsHolder);
            repaymentAmount.addValueChangeListener(valueChangeEvent -> enableCommitButtonByRepaymentAmount(actionsHolder));
        }
    }

    protected void initRepaymentAmountField(ActionsHolder actionsHolder) {

        repaymentAmount.setEditable(true);
        actionsHolder.getAction("windowCommit").setEnabled(false);
    }

    protected void enableCommitButtonByRepaymentAmount(ActionsHolder actionsHolder){
        if (repaymentAmount.getValue() != null && !repaymentAmount.getValue().equals("0")){

            actionsHolder.getAction("windowCommit").setEnabled(true);
        }
    }
}
