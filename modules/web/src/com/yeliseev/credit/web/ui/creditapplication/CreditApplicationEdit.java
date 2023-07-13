/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.web.ui.creditapplication;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.thesis.core.entity.Bank;
import com.haulmont.thesis.core.entity.Contractor;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.thesis.web.voice.VoiceActionPriorities;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.thesis.core.entity.DocCategory;
import com.haulmont.workflow.core.entity.CardRelation;
import com.yeliseev.credit.entity.Credit;
import com.yeliseev.credit.entity.CreditApplication;
import com.yeliseev.credit.service.BorrowerService;

import javax.inject.Inject;
import javax.persistence.Persistence;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.haulmont.thesis.web.voice.VoiceCompanionsRepository.voiceCompanion;

public class  CreditApplicationEdit<T extends CreditApplication> extends AbstractDocEditor<T> {

    @Inject
    protected LookupPickerField<DocCategory> docCategory;
    @Inject
    private CollectionDatasource<Credit, UUID> creditsDs;
    @Inject
    private BorrowerService borrowerService;
    @Inject
    private TextField<Bank> bank;
    @Inject
    private LookupField<Contractor> borrower;
    @Inject
    private TextField<String> bankApplication;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        borrower.addValueChangeListener(stringValueChangeEvent -> fillBankApplication());
        bank.addValueChangeListener(stringValueChangeEvent -> fillBankApplication());
    }


    @Override
    public void setItem(Entity item) {
        super.setItem(item);
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "processTab,openHistoryTab,securityTab,cardProjectsTab,correspondenceHistoryTab,docTransferLogTab,cardLinksTab,docLogTab,versionsTab";
    }

    @Override
    protected void addPrintDocActions() {
        super.addPrintDocActions();
        printButton.addAction(new PrintReportAction("printExecutionList", this, "printDocExecutionListReportName"));
    }

    protected void initVoiceControl() {
        voiceCompanion(docCategory).setPriorityOffset(VoiceActionPriorities.TAB_SHEET + 10);
    }

    @Override
    protected void fillHiddenTabs() {
        hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        if (getAccessData().getNotVersion()) {
            hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        }
        super.fillHiddenTabs();
    }

    private void fillBankApplication(){
        if (!(getItem().getCredit() == null)){
            if (!(getItem().getCredit().getBank() == null) && !(getItem().getBorrower() == null)){
                bankApplication.setValue(
                        String.valueOf(
                                borrowerService.getCountCreditApplication(getItem())));
            }
        }
    }
}