public class user {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String creditCardNum;
    protected String email;
    protected String role;

    // constructors
    public user() {
    }

    public user(String email) {
        this.email = email;
    }
	    
	public user(String email,String firstName, String lastName, String creditCardNum, String password, String role) 
	{
	    this(firstName,lastName,creditCardNum, password, role);
	    this.email = email;
	}
	 
	
	public user(String firstName, String lastName, String creditCardNum, String password, String role) 
	{
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.creditCardNum = creditCardNum;
	    this.password = password;
	    this.role = role;
	}
	    
	// getter and setter methods
    public int getRole() {
        return id;
    }

    public void setRole(String role) {
        this.role = role;
    }
	
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