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
    
    // Save a Quote to the database
    public void insertQuote(Quotes quote) throws SQLException {
    	connect_func("root", "pass1234");
        String query = "INSERT INTO Quotes (price, schedulestart, scheduleend) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setDouble(1, quote.getPrice());
            pstmt.setTimestamp(2, new java.sql.Timestamp(quote.getScheduleStart().getTime()));
            pstmt.setTimestamp(3, new java.sql.Timestamp(quote.getScheduleEnd().getTime()));

            pstmt.executeUpdate();
        }
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
