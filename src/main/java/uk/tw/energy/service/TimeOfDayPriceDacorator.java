package uk.tw.energy.service;

import java.math.BigDecimal;

public class TimeOfDayPriceDacorator extends PriceDacorator {

    public TimeOfDayPriceDacorator(PriceInterface pricePlan) {
        super(pricePlan);
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }
}
