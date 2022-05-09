package uk.tw.energy.repo;

import org.springframework.beans.factory.annotation.Autowired;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.Meter;
import uk.tw.energy.domain.MeterReadings;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class MeterReadingRepo {

    @Autowired
    private  List<MeterReadings> meterAssociatedReadings;

    public Optional<List<ElectricityReading>> findReading(Meter meter) {
        List<ElectricityReading> electricityReadings = null;
        for (MeterReadings meterReadings : meterAssociatedReadings) {
            if (meterReadings.getSmartMeter().equals(meter)) {
                electricityReadings = meterReadings.getElectricityReadings();
            }
        }
        return ofNullable(electricityReadings);
    }


    public void save(MeterReadings meterReadings) {
        if(meterAssociatedReadings.contains(meterReadings)){
            int index =  meterAssociatedReadings.indexOf(meterReadings);
            MeterReadings mr = meterAssociatedReadings.get(index);
            mr.getElectricityReadings().addAll(meterReadings.getElectricityReadings());
            meterAssociatedReadings.add(index, mr);
        } else {
            meterAssociatedReadings.add(meterReadings);
        }
    }

}
