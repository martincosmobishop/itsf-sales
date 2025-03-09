package com.dslobozian.itsf_sales.billing.tax.service.impl;

import com.dslobozian.itsf_sales.sales.catalog.Product;
import com.dslobozian.itsf_sales.sales.catalog.ProductType;
import com.dslobozian.itsf_sales.sales.order.Order;
import com.dslobozian.itsf_sales.billing.tax.service.TaxService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import static com.dslobozian.itsf_sales.billing.tax.util.TaxUtils.roundUpTo05;

@Service
public class TaxServiceImpl implements TaxService {
    @Value("${itsf.tax.basic.exemptions}")
    private Set<ProductType> basicTaxExemptedProducts;
    @Value("${itsf.tax.basic.percentage}")
    private int basicTaxPercentage;
    @Value("${itsf.tax.imported.exemptions}")
    private Set<ProductType> importedTaxExemptedProducts;
    @Value("${itsf.tax.imported.percentage}")
    private int importedTaxPercentage;

    @Override
    public BigDecimal computeTax(@Valid Order order) {
        return order.getProducts()
            .entrySet()
            .stream()
            .map(entry -> computeTax(entry.getKey(), entry.getValue()))
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.FLOOR);
    }


    @Override
    public BigDecimal computeTax(Product product, Integer quantity) {
        int totalTaxPercentage = 0;
        if(!basicTaxExemptedProducts.contains(product.getType())) {
            totalTaxPercentage += basicTaxPercentage;
        }
        if(product.isImported() && !importedTaxExemptedProducts.contains(product.getType())) {
            totalTaxPercentage += importedTaxPercentage;
        }
        return roundUpTo05(product.getPrice().setScale(2, RoundingMode.FLOOR)
                                 .multiply(BigDecimal.valueOf(quantity))
                                 .multiply(BigDecimal.valueOf(totalTaxPercentage))
                                 .divide(BigDecimal.valueOf(100 + totalTaxPercentage), RoundingMode.FLOOR))
                                 ;
    }

}
