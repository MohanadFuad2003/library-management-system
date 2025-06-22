
package models;

public class SystemConfig {
    private int configId; // مفتاح رئيسي
    private double fineRate;
    private int maxBooksBorrowed;
    private int maxBorrowPeriod;

    public SystemConfig() {}

    public SystemConfig(int configId, double fineRate, int maxBooksBorrowed, int maxBorrowPeriod) {
        this.configId = configId;
        this.fineRate = fineRate;
        this.maxBooksBorrowed = maxBooksBorrowed;
        this.maxBorrowPeriod = maxBorrowPeriod;
    }

    // getters and setters
    public int getConfigId() { return configId; }
    public void setConfigId(int configId) { this.configId = configId; }

    public double getFineRate() { return fineRate; }
    public void setFineRate(double fineRate) { this.fineRate = fineRate; }

    public int getMaxBooksBorrowed() { return maxBooksBorrowed; }
    public void setMaxBooksBorrowed(int maxBooksBorrowed) { this.maxBooksBorrowed = maxBooksBorrowed; }

    public int getMaxBorrowPeriod() { return maxBorrowPeriod; }
    public void setMaxBorrowPeriod(int maxBorrowPeriod) { this.maxBorrowPeriod = maxBorrowPeriod; }
}
