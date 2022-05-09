package uk.tw.energy.domain;

public class EnergySupplier {
    private String energySupplierName;
    // List of plans offered
    // other details i.e address, commerical info etc

    public EnergySupplier(String energySupplierName) {
        this.energySupplierName = energySupplierName;
    }

    public String getEnergySupplierName() {
        return energySupplierName;
    }
}
