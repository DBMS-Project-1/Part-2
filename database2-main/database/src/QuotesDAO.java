import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuotesDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	
    public QuotesDAO() {

    }

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
    public int insertQuoteEmpty(Quotes quote) throws SQLException {
        connect_func("root", "pass1234");
        String query = "INSERT INTO Quotes (contractorid, clientid) VALUES (?, ?)";
        int id = -1;

        try (PreparedStatement pstmt = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, quote.getContractorId());
            pstmt.setInt(2, quote.getClientId());


            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating quote failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating quote failed, no ID obtained.");
                }
            }
        }
        System.out.println("The Quotes generated id is: " + id);
        return id;
    }

    public void updateQuotes(int id, double price, Date scheduleStart, Date scheduleEnd) throws SQLException {
        connect_func("root", "pass1234");
        System.out.println("I am hereee updateQuotes");
        String query = "UPDATE Quotes SET price = ?, schedulestart = ?, scheduleend = ? WHERE id = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setDouble(1, price);
            pstmt.setTimestamp(2, new java.sql.Timestamp(scheduleStart.getTime()));
            pstmt.setTimestamp(3, new java.sql.Timestamp(scheduleEnd.getTime()));
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
        }
    }
    
    public void updateUserReply(int id, Boolean isAccepted, String Reply) throws SQLException {
        connect_func("root", "pass1234");
        System.out.println("I am hereee updateUserReply");
        String query = "UPDATE Quotes SET userAccept = ?, userResponse = ? WHERE id = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setBoolean(1, isAccepted);
            pstmt.setString(2, Reply);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        }
    }
    
    public void updateDavidReply(int id, Boolean isAccepted, String Reply) throws SQLException {
        connect_func("root", "pass1234");
        System.out.println("I am hereee updateUserReply");
        String query = "UPDATE Quotes SET davidAccept = ?, davidResponse = ? WHERE id = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setBoolean(1, isAccepted);
            pstmt.setString(2, Reply);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        }
    }


    
    public int getIDByUserID(int userID) throws SQLException {
        connect_func("root", "pass1234");
        String query = "SELECT id FROM Quotes WHERE clientid = ?";
        int id = -1;

        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        }

        return id;
    }
    
    public List<Quotes> listUserQuotes() throws SQLException {
        List<Quotes> listQuote = new ArrayList<>();
        String sql = "SELECT * FROM Quotes";
        connect_func();
        preparedStatement = connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int clientId = resultSet.getInt("clientid");
            double price = resultSet.getDouble("price");
            
            Timestamp scheduleStartTimestamp = resultSet.getTimestamp("schedulestart");
            Timestamp scheduleEndTimestamp = resultSet.getTimestamp("scheduleend");

            java.util.Date scheduleStart = null;
            java.util.Date scheduleEnd = null;

            if (scheduleStartTimestamp != null) {
                scheduleStart = new java.util.Date(scheduleStartTimestamp.getTime());
            }

            if (scheduleEndTimestamp != null) {
                scheduleEnd = new java.util.Date(scheduleEndTimestamp.getTime());
            }
            
            boolean userAccept = resultSet.getBoolean("userAccept");
            boolean davidAccept = resultSet.getBoolean("davidAccept");
            String userResponse = resultSet.getString("userResponse");
            String davidResponse = resultSet.getString("davidResponse");

            Quotes quote = new Quotes(id, clientId, price, scheduleStart, scheduleEnd, userAccept, davidAccept, userResponse, davidResponse);
            listQuote.add(quote);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return listQuote;
    }

    
    public List<Quotes> listDavidQuotes(int currentId) throws SQLException {
        List<Quotes> listQuote = new ArrayList<>();
        String sql = "SELECT * FROM Quotes WHERE clientid = ?";
        connect_func();
        preparedStatement = connect.prepareStatement(sql);
        preparedStatement.setInt(1, currentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int clientId = resultSet.getInt("clientid");
            double price = resultSet.getDouble("price");
            
            Timestamp scheduleStartTimestamp = resultSet.getTimestamp("schedulestart");
            Timestamp scheduleEndTimestamp = resultSet.getTimestamp("scheduleend");

            java.util.Date scheduleStart = null;
            java.util.Date scheduleEnd = null;

            if (scheduleStartTimestamp != null) {
                scheduleStart = new java.util.Date(scheduleStartTimestamp.getTime());
            }

            if (scheduleEndTimestamp != null) {
                scheduleEnd = new java.util.Date(scheduleEndTimestamp.getTime());
            }
            
            boolean userAccept = resultSet.getBoolean("userAccept");
            boolean davidAccept = resultSet.getBoolean("davidAccept");
            String userResponse = resultSet.getString("userResponse");
            String davidResponse = resultSet.getString("davidResponse");

            Quotes quote = new Quotes(id, clientId, price, scheduleStart, scheduleEnd, userAccept, davidAccept, userResponse, davidResponse);
            listQuote.add(quote);
        }

        resultSet.close();
        preparedStatement.close();
        disconnect();

        return listQuote;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    //public void updateDavidResponse(String response) 
    	
    
    
    //public void updateUserResponse();
    
    
    //might work idk yet might have to change this later
    public void updateResponse(String response, int userId, int quoteId) throws SQLException {
    	connect_func("root", "pass1234");
    	String sql = "UPDATE Quotes SET davidResponse = ?, userResponse = ? WHERE id = ?";
    	
    	
    	
    	try (PreparedStatement statement = connect.prepareStatement(sql)) {
    		if (userId == 2) {
    			statement.setString(1, response);
    			statement.setString(2, "");
    		}
    		else {
    			statement.setString(1, "");
    			statement.setString(2, response);
    		}
    		
    		statement.executeUpdate();
    		
    	}
    	
    	//might need to close prepared statement
    	statement.close();
    	disconnect();
    		

    }
    
    
    public void updateAccept(boolean accept, int userId, int quoteId) throws SQLException {
    	connect_func("root", "pass1234");
    	String sql;
    	
    	if (userId == 2)
    		sql = "UPDATE Quotes SET davidAccept = ? WHERE id = ?";
    	else
    		sql = "UPDATE Quotes SET userAccept = ? WHERE id = ?";
    	
    	try (PreparedStatement statement = connect.prepareStatement(sql)) {
    		statement.setBoolean(1, accept);
    	}
    	
    	statement.close();
    	disconnect();
    	
    }

}
