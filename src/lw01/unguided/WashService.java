package lw01.unguided;

public abstract class WashService implements Billable {
    private String id;
    private int days;
    private int units;

    protected WashService(String id, int days) {
        this(id, days, 1);
    }

    protected WashService(String id, int days, int units) {
        if (days <= 0 || units <= 0) {
            throw new IllegalArgumentException("Days and units must be positive integers.");
        }
        this.id = id;
        this.days = days;
        this.units = units;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public abstract int calculateCharge();

    // Overload: menghitung total biaya berdasarkan jumlah unit
    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("Units must be a positive integer.");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Service";
    }

    // Method summary tidak di-override oleh subclass
    public String summary() {
        return id + " | " + label() + " | " + calculateCharge(units);
    }
}