package uk.tw.energy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import uk.tw.energy.domain.PeakDayMultiplier;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeekOfDayPriceDacorator extends PriceDacorator {

    private final List<PeakDayMultiplier> peakDayMultipliers;

    public WeekOfDayPriceDacorator(PriceInterface pricePlan) {
        super(pricePlan);
        peakDayMultipliers = getPeakDayMultipliers();
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal unitRate = super.getPrice();
        return  peakDayMultipliers.stream()
                .filter(multiplier -> multiplier.getDayOfWeek().equals(LocalDateTime.now().getDayOfWeek()))
                .findFirst()
                .map(multiplier -> unitRate.multiply(multiplier.getMultiplier()))
                .orElse(unitRate);
    }

    private List<PeakDayMultiplier> getPeakDayMultipliers(){
       List<PeakDayMultiplier> peakDayMultipliers = new ArrayList<>();
            peakDayMultipliers.add(new PeakDayMultiplier(DayOfWeek.SUNDAY, new BigDecimal(2)));
            peakDayMultipliers.add(new PeakDayMultiplier(DayOfWeek.MONDAY,new BigDecimal(3)));
            peakDayMultipliers.add(new PeakDayMultiplier(DayOfWeek.TUESDAY,new BigDecimal(4)));
            peakDayMultipliers.add(new PeakDayMultiplier(DayOfWeek.WEDNESDAY,new BigDecimal(5)));
            peakDayMultipliers.add(new PeakDayMultiplier(DayOfWeek.THURSDAY,new BigDecimal(6)));
            peakDayMultipliers.add(new PeakDayMultiplier(DayOfWeek.FRIDAY,new BigDecimal(7)));
            peakDayMultipliers.add(new PeakDayMultiplier(DayOfWeek.SATURDAY,new BigDecimal(8)));
            return peakDayMultipliers;
    }
}
