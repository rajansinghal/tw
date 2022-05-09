package uk.tw.energy.repo;

import org.springframework.beans.factory.annotation.Autowired;
import uk.tw.energy.domain.Meter;

import java.util.List;

public class PricePlanProvider {

    @Autowired
    private  List<Meter> smartMeterToPricePlanAccounts;

    public String getPricePlanByMeterId(String meterId) {
        for(Meter meter: smartMeterToPricePlanAccounts){
            if(meter.getMeterId().equalsIgnoreCase(meterId)){
                return meter.getPlan().getPlanName();
            }
        }
        return null;
    }
}
