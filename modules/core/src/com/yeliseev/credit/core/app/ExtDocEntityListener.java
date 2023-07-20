/*
 * Copyright (c) 2023 LTD Haulmont Samara. All Rights Reserved.
 * Haulmont Samara proprietary and confidential.
 * Use is subject to license terms.
 */

package com.yeliseev.credit.core.app;

import com.haulmont.thesis.core.entity.Doc;
import com.haulmont.thesis.core.listener.DocEntityListener;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

public class ExtDocEntityListener extends DocEntityListener {
    public static final String NAME = "credit_ExtDocEntityListener";

    @Override
    protected String createDescription(Doc entity) {
        StringBuilder description = new StringBuilder();
        description.append("!");
        description.append(getDocKindName(entity));

        String number = getDescriptionNumber(entity);
        if (StringUtils.isNotBlank(number)) {
            description.append(" ");
            description.append(messages.getMessage(getClass(), "Doc.numberPrefix")).append(" ");
            description.append(StringUtils.trimToEmpty(number));
        }

        Date date = entity.getDate();
        if (isDocRegisteredWithRegDate(entity)) {
            date = entity.getRegDate();
        }
        if (date != null) {
            description.append(" ").append(messages.getMessage(getClass(), "Doc.datePrefix"))
                    .append(" ").append(String.format("%1$td.%1$tm.%1$tY", date));
        }

        return description.toString();
    }
}