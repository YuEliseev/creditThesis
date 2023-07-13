/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.web.ui.creditkind;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogActions;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogOutcome;
import com.haulmont.cuba.gui.app.core.inputdialog.InputParameter;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.yeliseev.credit.entity.CreditKind;
import com.yeliseev.credit.service.CreditKindService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;

public class CreditKindBrowse extends AbstractLookup {
    @Inject
    private CreditKindService creditKindService;
    @Inject
    private Dialogs dialogs;
    @Inject
    private Table<CreditKind> creditKindsTable;

    @Override
    public void init(Map<String, Object> params) {
    }

    @Subscribe("changeCreditAmountBtn")
    public void onChangeCreditAmountBtnClick(Button.ClickEvent event) {
        dialogs.createInputDialog(this)
                .withCaption(getMessage("changeCreditAmount"))
                .withParameters(
                        InputParameter
                                .doubleParameter("amount")
                                .withCaption("msg://Credit.amount"))
                .withActions(
                        DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent ->{
                    if (closeEvent.closedWith(DialogOutcome.OK)){
                        BigDecimal value = BigDecimal.valueOf((Double) closeEvent.getValue("amount"));

                        CreditKind selectedItem = creditKindsTable.getSingleSelected();
                        if (!(selectedItem == null)){

                            creditKindService.changeCreditAmount(selectedItem, value);
                        }
                    }
                }).show();
    }
}