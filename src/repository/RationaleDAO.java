package repository;

/**
 * @author Vishal Patel
 * 
 */

import domain.Diagram;
import domain.Rationale;
import domain.User;
import repository.DiagramDAO;
import repository.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RationaleDAO {

    /**
     * Add Rationale into DB
     * 			
     * @param Rationale object
     * @return true if success; false if fail
     */
	public static Rationale addRationale(Rationale rationale) {
		ResultSet rs;
		try {
		    Connection conn = DbManager.getConnection();
		    String sql = "INSERT INTO rationale(compareId,userId,summary,issue,issueRelationship,criteria,criteriaRelationship,rationaleTime,promotedDiagramId,alternativeDiagramId) VALUES(?,?,?,?,?,?,?,NOW(),?,?);";
		    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    pstmt.setInt(1, rationale.getCompareId());
		    pstmt.setInt(2, rationale.getUserId());
		    pstmt.setString(3, rationale.getSummary());
		    pstmt.setString(4, rationale.getIssue());
		    pstmt.setString(5, rationale.getIssueRelationship());
		    pstmt.setString(6, rationale.getCriteria());
		    pstmt.setString(7, rationale.getCriteriaRelationship());
		    pstmt.setInt(8, rationale.getPromotedDiagramId());
		    pstmt.setInt(9, rationale.getAlternativeDiagramId());
		    
		    pstmt.executeUpdate();

		    //Get and set the auto-generated PK
		    rs = pstmt.getGeneratedKeys();
		    if (rs.next()) {
		    	int newId = rs.getInt(1);
				rationale.setRationaleId(newId);
		    }
		    rs.close();
		    pstmt.close();
		    conn.close();

		} catch (SQLException ex) {
		    Logger.getLogger(RationaleDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rationale;
	}
	
	public static boolean updateRationale(Rationale rationale) {		
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
    		conn = DbManager.getConnection();
    		pstmt = conn.prepareStatement("UPDATE rationale SET userId = ?, summary = ?, issue = ?, issueRelationship = ?, "
    				+ "criteria = ?, criteriaRelationship = ?, rationaleTime = NOW() WHERE rationaleId = ?;");

		    pstmt.setInt(1, rationale.getUserId());
		    pstmt.setString(2, rationale.getSummary());
		    pstmt.setString(3, rationale.getIssue());
		    pstmt.setString(4, rationale.getIssueRelationship());
		    pstmt.setString(5, rationale.getCriteria());
		    pstmt.setString(6, rationale.getCriteriaRelationship());
		    pstmt.setInt(7, rationale.getRationaleId());

		    if(pstmt.executeUpdate() != 0) {

    	    	pstmt.close();
    	    	conn.close();
    	    	
    	    	return true;
    	    } 
		    else {
    	    	pstmt.close();
    	    	conn.close();

    	    	return false;
    	    }
    	} 
    	catch (SQLException e) {
    		Logger.getLogger(RationaleDAO.class.getName()).log(Level.SEVERE, null, e);
    	    e.printStackTrace();
    	    return false;
    	}
	}
	
	
	public static boolean deleteRationale(Rationale rationale) {		
		try {
			Connection conn = DbManager.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM rationale where rationaleId = ?;");
			pstmt.setInt(1, rationale.getRationaleId());

			// Execute the SQL statement and update database accordingly.
			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
			return true;
		}
		catch (SQLException e) {
    		Logger.getLogger(RationaleDAO.class.getName()).log(Level.SEVERE, null, e);
    	    e.printStackTrace();
    	    return false;
    	}
	}
	
    /**
     * Get Rationale ArrayList from DB
     * 
     * @param compareId
     * @return Rationale ArrayList
     */
	public static ArrayList<Rationale> getRationales(int compareId) {
		ArrayList<Rationale> searchResult = new ArrayList<>();
		try {
		    Connection conn = DbManager.getConnection();
		    String sql = "SELECT * FROM rationale where compareId = ? ORDER BY `rationaleTime` DESC;";
		    PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, compareId);

		    ResultSet rs = pstmt.executeQuery();

		    //Initiate a list to get all returned rationale objects and set attributes
		    while (rs.next()) {
				Rationale rationale = new Rationale();
				rationale.setRationaleId(rs.getInt("rationaleId"));
				rationale.setCompareId(rs.getInt("compareId"));
				rationale.setUserId(rs.getInt("userId"));
				rationale.setSummary(rs.getString("summary"));
				rationale.setRationaleTime(rs.getString("rationaleTime"));
				rationale.setIssue(rs.getString("issue"));
				rationale.setIssueRelationship(rs.getString("issueRelationship"));
				rationale.setCriteria(rs.getString("criteria"));
				rationale.setCriteriaRelationship(rs.getString("criteriaRelationship"));
				rationale.setPromotedDiagramId(rs.getInt("promotedDiagramId"));
				rationale.setAlternativeDiagramId(rs.getInt("alternativeDiagramId"));
				searchResult.add(rationale);
		    }

		    rs.close();
		    pstmt.close();
		    conn.close();

		    for(Rationale rationale: searchResult) {
		    	User user = UserDAO.getUser(rationale.getUserId());
		    	rationale.setUserName(user.getUserName());
		    	
		    	Diagram promotedDiagram = DiagramDAO.getDiagram(rationale.getPromotedDiagramId());
		    	rationale.setPromotedDiagramName(promotedDiagram.getDiagramName());
		    	
		    	Diagram alternativeDiagram = DiagramDAO.getDiagram(rationale.getAlternativeDiagramId());
		    	rationale.setAlternativeDiagramName(alternativeDiagram.getDiagramName());
		    }
				

		    return searchResult;
		} catch (SQLException ex) {
		    Logger.getLogger(RationaleDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	    }
}