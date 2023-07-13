/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.web.ui.credit;


import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.components.*;
import com.yeliseev.credit.entity.*;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.thesis.web.ui.basic.editor.AbstractCardEditor;
import com.haulmont.thesis.web.ui.basic.editor.CardHeaderFragment;
import com.haulmont.workflow.core.app.WfUtils;
import com.yeliseev.credit.service.BankService;
import com.yeliseev.credit.service.CreditService;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.thesis.core.entity.Numerator;
import com.haulmont.thesis.core.app.NumerationService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.PersistenceHelper;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditEdit<T extends Credit> extends AbstractCardEditor<T> {

    private Boolean flag = false;
    @Inject
    protected NumerationService numerationService;
    @Inject
    protected UserSessionSource uss;
    @Inject
    private CreditService creditService;
    @Inject
    private BankService bankService;
    @Inject
    private LookupField bank;
    @Inject
    private TextField<String> bankAmount;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        bank.addValueChangeListener(valueChangeEvent -> fillBankAmount());
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "processTab,securityTab,cardLogTab";
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        T card = getItem();
        if (isNumberAssignNeeded())
            setNumber(card);
        if (isNewItem(card)) {
            initDefaultActors();
        }
        initCardHeader(card);
    }

    protected boolean isNumberAssignNeeded() {
        return true;
    }

    protected void setNumber(Entity item) {
        LoadContext<Numerator> ctx = new LoadContext<>(Numerator.class);
        if (PersistenceHelper.isNew(item) && item.getMetaClass().getProperty("number") != null) {
            ctx.setQueryString("select n from df$Numerator n where n.code=:name").setParameter("name",
                    getItem().getClass().getSimpleName() + "Numerator");
            List<Numerator> numeratorList = getDsContext().getDataSupplier().loadList(ctx);
            if (!numeratorList.isEmpty()) {
                Numerator numerator = numeratorList.get(0);
                HashMap<String, Object> params = new HashMap<>();
                params.put("entity", getItem());
                item.setValue("number", numerationService.getNextNumber(numerator, params));
            }
        }
    }

    @Override
    protected void initHeaderFragmentContent(CardHeaderFragment cardHeaderFragment) {
        cardHeaderFragment.addDetailsInfoItem(
            "numberCaption",
            messages.getMessage(Credit.class, "notification.number"),
            "numberValue",
            cardDs,
            "number"
        );
        cardHeaderFragment.addDetailsInfoItem(
            "dateCaption",
            messages.getMessage(Credit.class, "notification.from"),
            "dateValue",
            cardDs,
            "createTs",
            (date) -> date == null ? StringUtils.EMPTY : new SimpleDateFormat("dd.MM.yyyy", uss.getLocale()).format(date)
        );
        super.initHeaderFragmentContent(cardHeaderFragment);
    }

    protected void initCardHeader(T item) {
        cardHeaderFragment.setDetailsInfoItemVisible("headerProcNameCaption", "headerProcName",
        !WfUtils.isCardInState(item, "New") && StringUtils.isNotEmpty(item.getState()));
    }
    

    @Override
    protected void initCardRolesFrame() {
        super.initCardRolesFrame();
        if (cardRolesFrame != null) {
            if(cardRolesFrame.getComponent("moveDown")!=null)
                cardRolesFrame.getComponentNN("moveDown").setVisible(false);
            if(cardRolesFrame.getComponent("moveUp")!=null)
                cardRolesFrame.getComponentNN("moveUp").setVisible(false);

            boolean editable = getAccessData().isCardRolesEditable();
            cardRolesFrame.setEditable(editable);
        }
    }
    @Override
    protected boolean preCommit(){
        validateAmount();

        return super.preCommit() && flag;
    }



    public void validateAmount() {
        if (getItem().getAmount() == null && !flag){
            showOptionDialog(getMessage("Внимание"),
                    getMessage("В документе не указана сумма кредита. Желаете сохранить документ?"),
                    MessageType.CONFIRMATION, new Action[]{new DialogAction(DialogAction.Type.YES, true) {
                        @Override
                        public void actionPerform(Component component) {
                            close(COMMIT_ACTION_ID);
                            flag = true;
                        }
                    }, new DialogAction(DialogAction.Type.NO) {
                        @Override
                        public void actionPerform(Component component) {
                        }
                    }});
        }else{

            /*Вызов сервиса округляющего сумму кредита по определённому виду кредита
            * на данный момент округление выполняется по виду кредита "рефинансирование"
            * из-за недостаточной конкретности в задании
            * Предложение - добавить в creditKind поле специфицирующее тип округления*/
            creditService.roundAmountCeiling(getItem());
            flag = true;
        }
    }

    private void fillBankAmount(){
        if (!(getItem().getBank() == null)){

            bankAmount.setValue(String.valueOf(
                    bankService.getTotalBankAmount(getItem())
            ));
        }else{

            bankAmount.setValue("0");
        }
    }

}