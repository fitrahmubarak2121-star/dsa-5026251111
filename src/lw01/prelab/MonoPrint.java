package lw01.prelab;

public class MonoPrint extends PrintJob {
    private static final double RATE_PER_PAGE = 500.0;

    public MonoPrint(String jobId, int pages) {
        super(jobId, pages);
    }

    @Override
    public double calculateCost() {
        return pages * RATE_PER_PAGE;
    }
}