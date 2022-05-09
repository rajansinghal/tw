package uk.tw.energy.service;

import uk.tw.energy.domain.Plan;

import java.math.BigDecimal;

public class PricePlan implements PriceInterface {

    private Plan plan;

    public PricePlan(Plan plan) {
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }

    @Override
    public BigDecimal getPrice() {
        return plan.getUnitRate();
    }
}
