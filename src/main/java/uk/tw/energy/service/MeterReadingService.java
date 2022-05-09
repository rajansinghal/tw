package uk.tw.energy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.Meter;
import uk.tw.energy.domain.MeterReadings;
import uk.tw.energy.repo.MeterReadingRepo;

import java.util.List;
import java.util.Optional;

@Service
public class MeterReadingService {

    @Autowired
    private final MeterReadingRepo meterReadingRepo;

    public MeterReadingService(MeterReadingRepo meterReadingRepo) {
        this.meterReadingRepo = meterReadingRepo;
    }


    public Optional<List<ElectricityReading>> getReadings(String smartMeterId) {
        return meterReadingRepo.findReading(new Meter(smartMeterId, null));
    }

    public void storeReadings(MeterReadings meterReadings) {
        meterReadingRepo.save(meterReadings);
    }
}
