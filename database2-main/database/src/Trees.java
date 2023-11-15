public class Trees {
    protected int id;
    protected int quoteId;
    protected double size;
    protected double height;
    protected double distanceFromHouse;

    // constructors
    public Trees() {
    }


    public Trees(int quoteId,double size, double height, double distanceFromHouse) {
    	this.quoteId = quoteId;
        this.size = size;
        this.height = height;
        this.distanceFromHouse = distanceFromHouse;
    }

    // getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDistanceFromHouse() {
        return distanceFromHouse;
    }

    public void setDistanceFromHouse(double distanceFromHouse) {
        this.distanceFromHouse = distanceFromHouse;
    }
}
