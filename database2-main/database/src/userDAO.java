import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from Users where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public int getUserByEmail(String email) throws SQLException {
        connect_func("root", "pass1234");
        String query = "SELECT id FROM Users WHERE email = ?";
        int id = -1;

        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        }

        return id;
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<>();        
        String sql = "SELECT * FROM Users";      
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String creditCardNum = resultSet.getString("creditCardNum");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");

            user user = new user(email, firstName, lastName, creditCardNum, password, role);
            listUser.add(user);
        }        

        resultSet.close();
        preparedStatement.close();
        disconnect();        

        return listUser;
    }

    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }


    public void insert(user user) throws SQLException {
        connect_func("root", "pass1234");

        String sql = "INSERT INTO Users(firstName, lastName, creditCardNum, password, email) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);

        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getCreditCardNum());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getEmail());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }


    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM Users WHERE email = ?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();

        return rowDeleted;
    }

     
    public boolean update(user user) throws SQLException {
        String sql = "UPDATE Users SET firstName=?, lastName=?, creditCardNum=?, password=? WHERE email=?";
        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getCreditCardNum());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getEmail());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        
        preparedStatement.close();
        disconnect();

        return rowUpdated;
    }


    
    public user getUser(String email) throws SQLException {
        user user = null;
        String sql = "SELECT * FROM Users WHERE email = ?";

        connect_func();

        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String creditCard = resultSet.getString("creditCardNum");
            String password = resultSet.getString("password");

            user = new user(email, firstName, lastName, creditCard, password);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return user;
    }



    
    public boolean checkEmail(String email) throws SQLException {
        boolean checks = false;
        String sql = "SELECT * FROM Users WHERE email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            checks = true;
        }
        
        preparedStatement.close();
        disconnect();
        
        return checks;
    }

    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM Users WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM Users";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException {
        connect_func();
        statement = (Statement) connect.createStatement();

        String[] INITIAL = {
                "drop database if exists testdb; ",
                "create database testdb; ",
                "use testdb; ",
                //"drop table if exists Users; ", //shouldnt need this because dropping database should delete everything
                ("CREATE TABLE if not exists Users( " +
                        "id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                        "firstName VARCHAR(50), " +
                        "lastName VARCHAR(50), " +
                        "creditCardNum CHAR(16), " +
                        "password VARCHAR(20) NOT NULL, " +
                        "email VARCHAR(50) UNIQUE, " +
                        "role VARCHAR(20) " +
                        "); "),
                ("CREATE TABLE if not exists Quotes( " +
                        "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                		"contractorid INTEGER," + 
                        "clientid INTEGER," +
                		"price DOUBLE," +
                        
                		//values for acceptance and response
                		"userAccept BOOL," +
                        "davidAccept BOOL," +
                		"userResponse VARCHAR(200)," +
                        "davidResponse VARCHAR(200)," +
                		
                        
                        "schedulestart DATETIME," + 
                		"scheduleend DATETIME," + 
                        "FOREIGN KEY (contractorid) references Users(id)," +
                		"FOREIGN KEY (clientid) references Users(id)" +
                        "); "),
                ("CREATE TABLE if not exists Trees( " +
                        "id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                		"quoteid INTEGER," + 
                        "size DOUBLE," +
                		"height DOUBLE," +
                        "distanceFromHouse DOUBLE," +
                		"FOREIGN KEY (quoteid) references Quotes(id)" +
                        ");")
                
        };

        String[] TUPLES = {
                "INSERT IGNORE INTO Users(id, firstName, lastName, creditCardNum, password, email) " +
                        "VALUES " +
                        "(1, 'root', 'root', '1234567890123456', 'pass1234', 'root'), " +
                        "(2, 'David', 'Smith', '1111222233334444', 'pass1234', 'David Smith'), " +
                        "(3, 'User', 'Two', '2222333344445555', 'user21234', 'user2@gmail.com'), " +
                        "(4, 'User', 'Three', '3333444455556666', 'user31234', 'user3@gmail.com'), " +
                        "(5, 'User', 'Four', '4444555566667777', 'user41234', 'user4@gmail.com'), " +
                        "(6, 'User', 'Five', '5555666677778888', 'user51234', 'user5@gmail.com'), " +
                        "(7, 'User', 'Six', '6666777788889999', 'user61234', 'user6@gmail.com'), " +
                        "(8, 'User', 'Seven', '7777888899990000', 'user71234', 'user7@gmail.com'), " +
                        "(9, 'User', 'Eight', '8888999900001111', 'user81234', 'user8@gmail.com'), " +
                        "(10, 'User', 'Nine', '9999000011112222', 'user91234', 'user9@gmail.com')"
        };


        // for loop to put these in the database
        for (int i = 0; i < INITIAL.length; i++)
            statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)
            statement.execute(TUPLES[i]);
        disconnect();
    }


}
