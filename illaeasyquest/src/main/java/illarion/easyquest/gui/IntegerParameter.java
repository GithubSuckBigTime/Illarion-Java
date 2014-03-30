/*
 * This file is part of the Illarion project.
 *
 * Copyright © 2014 - Illarion e.V.
 *
 * Illarion is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Illarion is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package illarion.easyquest.gui;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

@SuppressWarnings("serial")
public class IntegerParameter extends JFormattedTextField implements Parameter {

    public IntegerParameter() {
        super();
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
        setFormatterFactory(factory);
        setHorizontalAlignment(JFormattedTextField.RIGHT);
        setParameter(new Long(0));
    }

    public void setParameter(@Nullable Object parameter) {
        if (parameter != null) {
            if (parameter instanceof Long) {
                setValue(parameter);
            } else {
                setValue(new Long((String) parameter));
            }
        } else {
            setValue((long) 0);
        }
    }

    @Nonnull
    public Object getParameter() {
        return getValue();
    }
}