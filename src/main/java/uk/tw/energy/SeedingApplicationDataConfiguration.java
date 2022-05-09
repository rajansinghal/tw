package uk.tw.energy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import uk.tw.energy.domain.EnergySupplier;
import uk.tw.energy.domain.Meter;
import uk.tw.energy.domain.MeterReadings;
import uk.tw.energy.domain.PeakDayMultiplier;
import uk.tw.energy.domain.Plan;
import uk.tw.energy.generator.ElectricityReadingsGenerator;
import uk.tw.energy.repo.MeterReadingRepo;
import uk.tw.energy.repo.PricePlanProvider;
import uk.tw.energy.validator.MeterReadingValidator;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SeedingApplicationDataConfiguration {

    private static final String MOST_EVIL_PRICE_PLAN_ID = "price-plan-0";
    private static final String RENEWABLES_PRICE_PLAN_ID = "price-plan-1";
    private static final String STANDARD_PRICE_PLAN_ID = "price-plan-2";

    @Bean
    public List<Plan> pricePlans() {
        final List<Plan> plans = new ArrayList<>();
        plans.add(new Plan(MOST_EVIL_PRICE_PLAN_ID, BigDecimal.TEN, new EnergySupplier("Dr Evil's Dark Energy")));
        plans.add(new Plan(RENEWABLES_PRICE_PLAN_ID, BigDecimal.valueOf(2), new EnergySupplier("The Green Eco")));
        plans.add(new Plan(STANDARD_PRICE_PLAN_ID, BigDecimal.ONE, new EnergySupplier("Power for Everyone")));
        return plans;
    }

    @Bean
    public List<MeterReadings> meterAssociatedReadings() {
        final List<MeterReadings> meterReadings = new ArrayList<>();
        final ElectricityReadingsGenerator electricityReadingsGenerator = new ElectricityReadingsGenerator();
        smartMeters().forEach(sm -> meterReadings.add(new MeterReadings(sm, electricityReadingsGenerator.generate(20))));
        return meterReadings;
    }

    @Bean
    public List<Meter> smartMeters() {
        final List<Meter> smartMeters = new ArrayList<>();
        smartMeters.add(new Meter("smart-meter-0", new Plan(MOST_EVIL_PRICE_PLAN_ID, BigDecimal.TEN, new EnergySupplier("Dr Evil's Dark Energy"))));
        smartMeters.add(new Meter("smart-meter-1", new Plan(RENEWABLES_PRICE_PLAN_ID, BigDecimal.valueOf(2), new EnergySupplier("The Green Eco"))));
        smartMeters.add(new Meter("smart-meter-2", new Plan(MOST_EVIL_PRICE_PLAN_ID, BigDecimal.TEN, new EnergySupplier("Dr Evil's Dark Energy"))));
        smartMeters.add(new Meter("smart-meter-3", new Plan(STANDARD_PRICE_PLAN_ID, BigDecimal.ONE, new EnergySupplier("Power for Everyone"))));
        smartMeters.add(new Meter("smart-meter-4", new Plan(RENEWABLES_PRICE_PLAN_ID, BigDecimal.valueOf(2), new EnergySupplier("The Green Eco"))));
        return smartMeters;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    @Bean
    public MeterReadingValidator getMeterReadingValidator(){
        return new MeterReadingValidator();
    }

    @Bean
    public MeterReadingRepo getMeterReadingRepo() {
        return new MeterReadingRepo();
    }

    @Bean
    public PricePlanProvider getPricePlanProvider() {
        return new PricePlanProvider();
    }


}
