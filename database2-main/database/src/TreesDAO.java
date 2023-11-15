import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TreesDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

    
    public TreesDAO() {
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
    // Save a Tree to the database
    public void insertTree(Trees tree) throws SQLException {
        connect_func("root", "pass1234");

        String query = "INSERT INTO Trees (quoteid, size, height, distanceFromHouse) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
        	pstmt.setDouble(1, tree.getQuoteId());
            pstmt.setDouble(2, tree.getSize());
            pstmt.setDouble(3, tree.getHeight());
            pstmt.setDouble(4, tree.getDistanceFromHouse());

            pstmt.executeUpdate();
        }
    }
    
    public List<Trees> listAllTrees() throws SQLException {
        List<Trees> listTree = new ArrayList<>();        
        String sql = "SELECT * FROM Trees";      
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("I am hereee listAllTrees");
        while (resultSet.next()) {
        	int quoteID = resultSet.getInt("quoteid");
            double size = resultSet.getDouble("size");
            double height = resultSet.getDouble("height");
            double distanceFromHouse = resultSet.getDouble("distanceFromHouse");


            Trees tree = new Trees(quoteID, size, height, distanceFromHouse);
            listTree.add(tree);
        }        

        resultSet.close();
        preparedStatement.close();
        disconnect();        

        return listTree;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
}
