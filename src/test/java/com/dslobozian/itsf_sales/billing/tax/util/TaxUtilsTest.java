package com.dslobozian.itsf_sales.billing.tax.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxUtilsTest {

    @Test
    public void givenBigDecimalWith2Decimals_whenRoundUpTo05_thenOk() {
        BigDecimal taxAmount = BigDecimal.valueOf(12.97);
        BigDecimal roundedUpTaxAmount = TaxUtils.roundUpTo05(taxAmount);
        assertEquals(BigDecimal.valueOf(13.00), roundedUpTaxAmount);
    }

    @Test
    public void givenBigDecimalWith3Decimals_whenRoundUpTo05_thenOk() {
        BigDecimal taxAmount = BigDecimal.valueOf(12.959);
        BigDecimal roundedUpTaxAmount = TaxUtils.roundUpTo05(taxAmount);
        assertEquals(BigDecimal.valueOf(13.00), roundedUpTaxAmount);
    }
}
