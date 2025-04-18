package CustomWidgets;

import javax.swing.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

    public static Date convertToSQLDate(Object dateObj) {
        if (dateObj instanceof java.util.Date) {
            java.util.Date utilDate = (java.util.Date) dateObj;
            return new Date(utilDate.getTime());
        } else if (dateObj instanceof java.util.Calendar) {
            java.util.Calendar calendar = (java.util.Calendar) dateObj;
            return new Date(calendar.getTimeInMillis());
        } else {
            throw new IllegalArgumentException("Invalid date object: " + dateObj);
        }
    }

}