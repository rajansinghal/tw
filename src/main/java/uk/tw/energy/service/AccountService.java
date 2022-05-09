package uk.tw.energy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.repo.PricePlanProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountService {

    private final PricePlanProvider pricePlanProvider;

    public AccountService(PricePlanProvider pricePlanProvider) {
        this.pricePlanProvider = pricePlanProvider;
    }

    // price plan service this
    public String getPricePlanIdForSmartMeterId(String smartMeterId) {
        return pricePlanProvider.getPricePlanByMeterId(smartMeterId);
    }


    public BigDecimal calculateCost(List<ElectricityReading> electricityReadings, PricePlan plan) {
        BigDecimal average = calculateAverageReading(electricityReadings);
        BigDecimal timeElapsed = calculateTimeElapsed(electricityReadings);
        BigDecimal averagedCost = average.divide(timeElapsed, RoundingMode.HALF_UP);
        PriceDacorator pricePlanDacorator = new WeekOfDayPriceDacorator(plan);
        return averagedCost.multiply(pricePlanDacorator.getPrice());
    }

    private BigDecimal calculateAverageReading(List<ElectricityReading> electricityReadings) {
        BigDecimal summedReadings = electricityReadings.stream()
                .map(ElectricityReading::getReading)
                .reduce(BigDecimal.ZERO, (reading, accumulator) -> reading.add(accumulator));

        return summedReadings.divide(BigDecimal.valueOf(electricityReadings.size()), RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTimeElapsed(List<ElectricityReading> electricityReadings) {
        ElectricityReading first = electricityReadings.stream()
                .min(Comparator.comparing(ElectricityReading::getTime))
                .get();
        ElectricityReading last = electricityReadings.stream()
                .max(Comparator.comparing(ElectricityReading::getTime))
                .get();

        return BigDecimal.valueOf(Duration.between(first.getTime(), last.getTime()).getSeconds() / 3600.0);
    }
}
