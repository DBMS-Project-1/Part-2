public class user {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String creditCardNum;
    protected String email;

    // constructors
    public user() {
    }

    public user(String email) {
        this.email = email;
    }
	    
	public user(String email,String firstName, String lastName, String creditCardNum, String password) 
	{
	    this(firstName,lastName,creditCardNum, password);
	    this.email = email;
	}
	 
	
	public user(String firstName, String lastName, String creditCardNum, String password) 
	{
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.creditCardNum = creditCardNum;
	    this.password = password;
	}
	    
	// getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCard) {
        this.creditCardNum = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}