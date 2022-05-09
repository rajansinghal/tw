package uk.tw.energy.domain;

public class Meter {
    private String meterId;
    private Plan plan;

    public Meter(String meterId, Plan plan) {
        this.meterId = meterId;
        this.plan = plan;
    }

    public String getMeterId() {
        return meterId;
    }

    public Plan getPlan() {
        return plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meter meter = (Meter) o;

        return meterId != null ? meterId.equals(meter.meterId) : meter.meterId == null;
    }

    @Override
    public int hashCode() {
        return meterId != null ? meterId.hashCode() : 0;
    }
}
