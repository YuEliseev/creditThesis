/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.web.ui.credit;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogActions;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogOutcome;
import com.haulmont.cuba.gui.app.core.inputdialog.InputParameter;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.thesis.core.entity.TsCard;
import com.haulmont.thesis.web.ui.basic.browse.AbstractCardBrowser;
import com.yeliseev.credit.entity.Credit;
import com.yeliseev.credit.service.CreditService;

import javax.inject.Inject;
import java.math.BigDecimal;

public class CreditBrowse<T extends TsCard> extends AbstractCardBrowser<T> {
    @Inject
    private CreditService creditService;
    @Inject
    private Notifications notifications;
    @Inject
    private MetadataTools metadataTools;
    @Inject
    private Dialogs dialogs;
    @Inject
    private GroupTable<Credit> cardsTable;

    @Subscribe("showTotalAmountBtn")
    public void onShowTotalAmountBtnClick(Button.ClickEvent event) {
        Credit selectedItem = cardsTable.getSingleSelected();
        if (!(selectedItem == null)){
            BigDecimal totalAmount = creditService.getTotalCreditAmount(selectedItem);

            notifications.create(Notifications.NotificationType.HUMANIZED)
                    .withCaption(
                            messages.getMessage("com.yeliseev.credit.web.ui.credit", "msg://totalAmountNotification") + " " +
                            metadataTools.getInstanceName(selectedItem) + " - " + totalAmount).show();
        }
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

                        Credit selectedItem = cardsTable.getSingleSelected();
                        if (!(selectedItem == null)){

                            creditService.changeCreditAmount(selectedItem, value);
                        }
                    }
                }).show();
    }
}