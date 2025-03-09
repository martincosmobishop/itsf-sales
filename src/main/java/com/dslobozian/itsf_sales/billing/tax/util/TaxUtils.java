package com.dslobozian.itsf_sales.billing.tax.util;

import java.math.BigDecimal;

public final class TaxUtils {

    /**
     * During my tests for a roundedUpTo05 of 1.15 the BigDecimal value got converted to 1.149999,
     * which when setting the scale to 2 decimals rounding mode RoundingMode.FLOOR got a value of 1.14.
     * The solution I found was to use Float.toString tho generate a value, and create a new BigDecimal from it.
     * I would like your input on the why...
     * */
    public static BigDecimal roundUpTo05(BigDecimal value) {
        float roundedUpTo05 = Math.round(Math.ceil(value.floatValue() * 20)) / 20f;
        //return BigDecimal.valueOf(roundedUpTo05);
        return new BigDecimal(Float.valueOf(roundedUpTo05).toString());
    }
}
