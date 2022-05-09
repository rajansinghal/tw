package uk.tw.energy.service;

import java.math.BigDecimal;

public class PriceDacorator implements PriceInterface {
    protected PriceInterface pricePlan;

    public PriceDacorator(PriceInterface pricePlan) {
        this.pricePlan = pricePlan;
    }

    @Override
    public BigDecimal getPrice() {
        return pricePlan.getPrice();
    }
}
