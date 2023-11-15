import java.util.Date;

public class Quotes {
    protected int id;
    protected int contractorId;
    protected int clientId;
    protected double price;
    protected Date scheduleStart;
    protected Date scheduleEnd;

    // constructors
    public Quotes() {
    }

    public Quotes(int id) {
        this.id = id;
    }

    public Quotes(double price, Date scheduleStart, Date scheduleEnd) {
        this.price = price;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
    }

    // getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(Date scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public Date getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(Date scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }
}
