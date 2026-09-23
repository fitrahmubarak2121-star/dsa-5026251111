package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    protected String jobId;
    protected int pages;

    public PrintJob(String jobId, int pages) {
        this.jobId = jobId;
        this.pages = pages;
    }

    public String getJobId() {
        return jobId;
    }

    public int getPages() {
        return pages;
    }
}