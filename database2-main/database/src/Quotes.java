import java.util.Date;
//import java.lang.String;


public class Quotes {
    protected int id;
    protected int contractorId;
    protected int clientId;
    protected double price;
    protected Date scheduleStart;
    protected Date scheduleEnd;
    
    //new variables for acceptance and resopnse
    protected boolean userAccept;
    protected boolean davidAccept;
    protected String userResponse;
    protected String davidResponse;

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
    
    public Quotes (int id, int clientId, double price, Date scheduleStart, Date scheduleEnd, boolean userAccept, boolean davidAccept, String userResponse, String davidResponse) {
    	this.id = id;
        this.clientId = clientId;
        this.price = price;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.userAccept = userAccept;
        this.davidAccept = davidAccept;
        this.userResponse = userResponse;
        this.davidResponse = davidResponse;
    }
    
    public Quotes (int id, double price, Date scheduleStart, Date scheduleEnd, boolean userAccept, boolean davidAccept, String userResponse, String davidResponse) {
    	this.id = id;
        this.price = price;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.userAccept = userAccept;
        this.davidAccept = davidAccept;
        this.userResponse = userResponse;
        this.davidResponse = davidResponse;
    }
    
    public Quotes(int contractorId, int clientId) {
        this.contractorId = contractorId;
        this.clientId = clientId;
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
    
    
    //setters and getters for acceptance and response
    public void setUserAccept(boolean userAccept) {
    	this.userAccept = userAccept;
    }
    
    public boolean getUserAccept() {
    	return userAccept;
    }
    
    public void setDavidAccept(boolean davidAccept) {
    	this.davidAccept = davidAccept;
    }
    
    public boolean getDavidAccept() {
    	return davidAccept;
    }
    
    public void setUserResponse(String userResponse) {
    	this.userResponse = userResponse;
    }
    
    public String getUserResponse() {
    	return userResponse;
    }
    
    public void setDavidResponse(String davidResponse) {
    	this.davidResponse = davidResponse;
    }
    
    public String getDavidResponse() {
    	return davidResponse;
    }
    
}
