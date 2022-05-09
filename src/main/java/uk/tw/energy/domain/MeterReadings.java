package uk.tw.energy.domain;

import java.util.List;

public class MeterReadings {

    private List<ElectricityReading> electricityReadings;
    private Meter smartMeter;

    public MeterReadings(Meter smartMeter, List<ElectricityReading> electricityReadings) {
        this.smartMeter = smartMeter;
        this.electricityReadings = electricityReadings;
    }

    public List<ElectricityReading> getElectricityReadings() {
        return electricityReadings;
    }

    public Meter getSmartMeter() {
        return smartMeter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeterReadings that = (MeterReadings) o;

        return smartMeter != null ? smartMeter.equals(that.smartMeter) : that.smartMeter == null;
    }

    @Override
    public int hashCode() {
        return smartMeter != null ? smartMeter.hashCode() : 0;
    }
}
