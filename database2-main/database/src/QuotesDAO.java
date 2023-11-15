import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("The generated id is: " + id);
        return id;
    }

    public void insertQuote(Quotes quote, int clientId) throws SQLException {
        connect_func("root", "pass1234");
        String query = "UPDATE Quotes SET price = ?, schedulestart = ?, scheduleend = ? WHERE clientid = ?";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setDouble(1, quote.getPrice());
            pstmt.setTimestamp(2, new java.sql.Timestamp(quote.getScheduleStart().getTime()));
            pstmt.setTimestamp(3, new java.sql.Timestamp(quote.getScheduleEnd().getTime()));
            pstmt.setInt(4, clientId);

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
    
    public List<Quotes> listAllQuotes() throws SQLException {
        List<Quotes> listQuotes = new ArrayList<>();        
        String sql = "SELECT * FROM Quotes";      
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            double price = resultSet.getDouble("price");
            java.util.Date scheduleStart = new java.util.Date(resultSet.getDate("scheduleStart").getTime());
            java.util.Date scheduleEnd = new java.util.Date(resultSet.getDate("scheduleEnd").getTime());


            Quotes quote = new Quotes(price, scheduleStart, scheduleEnd);
            listQuotes.add(quote);
        }        

        resultSet.close();
        preparedStatement.close();
        disconnect();        

        return listQuotes;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }

    // Other methods for fetching, updating, or deleting Quotes can be added here
}
