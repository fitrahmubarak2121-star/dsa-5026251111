package lw01.prelab;

public class ColourPrint extends PrintJob {
    private static final double RATE_PER_PAGE = 1500.0;

    public ColourPrint(String jobId, int pages) {
        super(jobId, pages);
    }

    @Override
    public double calculateCost() {
        return pages * RATE_PER_PAGE;
    }
}