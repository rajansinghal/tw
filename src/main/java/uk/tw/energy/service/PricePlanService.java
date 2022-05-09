package uk.tw.energy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.Plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PricePlanService {

    private  List<PricePlan> pricePlanPlans;
    private final List<Plan> plans;
    private final MeterReadingService meterReadingService;
    private final AccountService accountService;

    public PricePlanService(List<Plan> plans, MeterReadingService meterReadingService, AccountService accountService) {
        this.plans = plans;
        this.pricePlanPlans = getPricePlans();
        this.meterReadingService = meterReadingService;
        this.accountService = accountService;
    }

    public Optional<Map<Plan, BigDecimal>> getConsumptionCostOfElectricityReadingsForEachPricePlan(String smartMeterId) {
        Optional<List<ElectricityReading>> electricityReadings = meterReadingService.getReadings(smartMeterId);

        if (!electricityReadings.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(pricePlanPlans.stream().collect(Collectors.toMap(PricePlan::getPlan, p -> accountService.calculateCost(electricityReadings.get(), p))));
    }

    private List<PricePlan> getPricePlans() {
        List<PricePlan> pricePlanPlans = new ArrayList<>();
        for(Plan p : plans) {
            pricePlanPlans.add(new PricePlan(p));
        }
        return pricePlanPlans;
    }
}
