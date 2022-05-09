package uk.tw.energy.domain;

import java.math.BigDecimal;
import java.time.DayOfWeek;

public class PeakDayMultiplier {

    private DayOfWeek dayOfWeek;  // hours may arise
    private BigDecimal multiplier;

    public PeakDayMultiplier(DayOfWeek dayOfWeek, BigDecimal multiplier) {
        this.dayOfWeek = dayOfWeek;
        this.multiplier = multiplier;
    }


    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }
}
