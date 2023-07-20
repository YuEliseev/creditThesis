/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.web.ui.Individual;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.web.ui.individual.IndividualBrowseFrame;
import com.yeliseev.credit.service.BorrowerService;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Map;

public class ExtIndividualBrowseFrame extends IndividualBrowseFrame {
    @Inject
    GroupTable<Individual> individualsTable;
    @Inject
    private BorrowerService borrowerService;
    @Inject
    private ComponentsFactory componentsFactory;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initCreditApplicationColumn();

    }

    protected void initCreditApplicationColumn(){
        individualsTable.addGeneratedColumn("creditApplication", new Table.ColumnGenerator<Individual>() {

            @Nullable
            @Override
            public Component generateCell(Individual individual) {
                return getCreditApplication(individual);
            }
        });
    }

    protected Component getCreditApplication(Individual individual){
        if(individual != null){
            Label label = componentsFactory.createComponent(Label.class);
            label.setValue(borrowerService.getTotalCreditApplication(individual.getId()));
            return label;
        }
        return null;
    }
}