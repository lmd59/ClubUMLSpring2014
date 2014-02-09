package domain;

import java.sql.Date;

/**
 * Information class that contains all the features of one comment
 * @ doc author	Vishal Patel
 */

public class Rationale {

	private int rationaleId;
	
	private String alternative;
	private String summary;
	private String issue;
	private String issueRelationship;
	private String criteria;
	private String criteraRelationship;
	private String rationaleTime;
	private int userId;
	private String userName;
	
    private int reportId;
    
    private int compareId;
    private int promotedDiagramId;
    
    public Rationale(int rationaleId, int reportId, int userId) {
        this.rationaleId = rationaleId;
        this.reportId = reportId;
        this.userId = userId;
    }
    
	/**
	 * Default constructor
	 */
    public Rationale(){
    }
    
	/**
	 * Gete the userName
	 * 
	 * @return userName String
	 */    
    public String getUserName() {
        return userName;
    }

	/**
	 * Set userName
	 * 
	 * @param userName
	 * 			The name of the user that made this comment
	 */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
	/**
	 * Gete the userId
	 * 
	 * @return userId int
	 */
    public int getUserId() {
        return userId;
    }

	/**
	 * Set userId
	 * 
	 * @param userId
	 * 			The ID of the user that made this comment
	 */
    public void setUserId(int userId) {
        this.userId = userId;
    }

	/**
	 * Get rationaleId
	 * 
	 * @return rationaleId int
	 */
    public int getRationaleId() {
        return rationaleId;
    }
    
	/**
	 * Set commentId
	 * 
	 * @param commentId
	 * 			The ID of the comment
	 */
    public void setRationaleId(int rationaleId) {
        this.rationaleId = rationaleId;
    }


	/**
	 * Get the rationaleTime
	 * 
	 * @return rationaleTime String
	 */
    public String getRationaleTime() {
        return rationaleTime;
    }

	/**
	 * Set rationaleTime
	 * 
	 * @param rationaleTime
	 * 			The time that this rationale was created
	 */
    public void setRationaleTime(String rationaleTime) {
        this.rationaleTime = rationaleTime;
    }

	/**
	 * Get  the diagramId
	 * 
	 * @return diagramId int
	 */
    public int getReportId() {
        return reportId;
    }

	/**
	 * Set diagramId
	 * 
	 * @param diagramId
	 * 			The ID of the diagram that this comment belongs to
	 */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

	/**
	 * @return the compareId
	 */
	public int getCompareId() {
		return compareId;
	}

	/**
	 * @param compareId the compareId to set
	 */
	public void setCompareId(int compareId) {
		this.compareId = compareId;
	}

	/**
	 * @return the promotedDiagramId
	 */
	public int getPromotedDiagramId() {
		return promotedDiagramId;
	}

	/**
	 * @param promotedDiagramId the promotedDiagramId to set
	 */
	public void setPromotedDiagramId(int promotedDiagramId) {
		this.promotedDiagramId = promotedDiagramId;
	}

	/**
	 * @return the alternative
	 */
	public String getAlternative() {
		return alternative;
	}

	/**
	 * @param alternative to set
	 */
	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}


	/**
	 * @return the alternative
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param alternative to set
	 */
	public void setSummary(String summary) {
		this.summary= summary;
	}
    
    
	/**
	 * @return the alternative
	 */
	public String getIssue() {
		return issue;
	}

	/**
	 * @param alternative to set
	 */
	public void setIssue(String issue) {
		this.issue= issue;
	}
    
    
	/**
	 * @return the alternative
	 */
	public String getIssueRelationship() {
		return issueRelationship;
	}

	/**
	 * @param alternative to set
	 */
	public void setIssueRelationship(String issueRelationship) {
		this.issueRelationship= issueRelationship;
	}
    
    
	/**
	 * @return the alternative
	 */
	public String getCriteria() {
		return criteria;
	}

	/**
	 * @param alternative to set
	 */
	public void setCriteria(String critera) {
		this.criteria = critera;
	}
    
	/**
	 * @return the alternative
	 */
	public String getCriteriaRelationship() {
		return criteraRelationship;
	}

	/**
	 * @param alternative to set
	 */
	public void setCriteriaRelationship(String criteraRelationship) {
		this.criteraRelationship = criteraRelationship;
	}
    
}
