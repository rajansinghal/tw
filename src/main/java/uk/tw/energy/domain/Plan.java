package uk.tw.energy.domain;

import java.math.BigDecimal;

public class Plan {

    private String planName;
    private BigDecimal unitRate;
    private EnergySupplier energySupplier;

    public Plan(String planName, BigDecimal unitRate, EnergySupplier energySupplier) {
        this.planName = planName;
        this.unitRate = unitRate;
        this.energySupplier = energySupplier;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public BigDecimal getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }

    public EnergySupplier getEnergySupplier() {
        return energySupplier;
    }

    public void setEnergySupplier(EnergySupplier energySupplier) {
        this.energySupplier = energySupplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        return planName != null ? planName.equals(plan.planName) : plan.planName == null;
    }

    @Override
    public int hashCode() {
        return planName != null ? planName.hashCode() : 0;
    }

}
