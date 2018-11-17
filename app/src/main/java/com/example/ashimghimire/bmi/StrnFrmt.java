package com.example.ashimghimire.bmi;

import java.text.NumberFormat;

public class StrnFrmt {
    private NumberFormat nf;

    /**
     * Function format the double to fixed t2o precision
     * @param number that is to be formatted
     * @return the formatted number
     */
    String numberFormat(double number) {
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(number);
    }

    /**
     * Function checks if the parameter is null or not
     * @param strNumber String  the user has inputed in the Application
     * @return the double if string is not null
     * @return false if the string is null
     */
    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   //  value to mark this field is wrong
            }
        } else return 0;
    }


}
