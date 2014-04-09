package repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class UseCaseJSONDAO {
	
	public Connection dbConnecter()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("java.sql.driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	 
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/rules","root", "1234");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
	 
		if (connection != null) {
			System.out.println("success!");
			
		} else {
			System.out.println("Failed to make connection!");
		}  
		return connection;
	}
	
	public static void insertJSONString(String json)
	{
    	ResultSet rs;
		try {
			Connection conn = DbManager.getConnection();
			//TODO: replace hard coded project number
		    PreparedStatement pstmt = conn.prepareStatement(
			    "insert INTO rulejsonstring (jsonString,projectID) VALUES(?,NULL);", Statement.RETURN_GENERATED_KEYS);
		    pstmt.setString(1, json);
			
		    // Execute the SQL statement and update database accordingly.
		    pstmt.executeUpdate();
		    rs = pstmt.getGeneratedKeys();
		    if (rs.next()) {
			int newId = rs.getInt(1);
			System.out.println("new id is: " + newId);
		    }
		    pstmt.close();
		    rs.close();
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
	}
	
	public static String getJSONString()
	{
		ResultSet rs;
		String jsonString = "";
		try {
			Connection conn = DbManager.getConnection();
			//TODO: replace hard coded project number
		    PreparedStatement pstmt = conn.prepareStatement(
			    "SELECT * FROM rulejsonstring order by jsonId desc;");
			
		    // Execute the SQL statement and update database accordingly.
		    rs = pstmt.executeQuery();
		    
		   //get most recent (highest id for now)
		    if (rs.next()) {
		    	jsonString = rs.getString("jsonString");
		    }
		    pstmt.close();
		    rs.close();
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return jsonString;
	}
}
