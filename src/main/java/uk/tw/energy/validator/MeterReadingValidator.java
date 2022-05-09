package uk.tw.energy.validator;

import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.MeterReadings;

import java.util.List;


public class MeterReadingValidator {

    public boolean isMeterReadingsValid(MeterReadings meterReadings) {
        String smartMeterId = meterReadings.getSmartMeter().getMeterId();
        List<ElectricityReading> electricityReadings = meterReadings.getElectricityReadings();
        return smartMeterId != null && !smartMeterId.isEmpty()
                && electricityReadings != null && !electricityReadings.isEmpty();
    }
}
