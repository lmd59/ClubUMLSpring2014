package domain;

/**
 * Information class that contains all the features of one rationale
 * @ doc author	Vishal Patel
 */

public class Rationale {

	private int rationaleId;

	private String summary;
	private String issue;
	private String issueRelationship;
	private String criteria;
	private String criteraRelationship;
	private String rationaleTime;
	private int userId;
	private String userName;
    
    private int compareId;
    private int promotedDiagramId;
    private int alternativeDiagramId;
    
	/**
	 * Default constructor
	 */
    public Rationale(){
    }
    
	/**
	 * Get the userName
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
	 * 			The name of the user that made this rationale
	 */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
	/**
	 * Get the userId
	 * 
	 * @return userId
	 */
    public int getUserId() {
        return userId;
    }

	/**
	 * Set userId
	 * 
	 * @param userId
	 * 			The ID of the user that made this rationale
	 */
    public void setUserId(int userId) {
        this.userId = userId;
    }

	/**
	 * Get rationaleId
	 * 
	 * @return rationaleId
	 */
    public int getRationaleId() {
        return rationaleId;
    }
    
	/**
	 * Set rationaleId
	 * 
	 * @param rationaleId
	 * 			The ID of the rationale
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
	 * @return the alternativeDiagramId
	 */
	public int getAlternativeDiagramId() {
		return alternativeDiagramId;
	}

	/**
	 * @param promotedDiagramId the alternativeDiagramId to set
	 */
	public void setAlternativeDiagramId(int alternativeDiagramId) {
		this.alternativeDiagramId = alternativeDiagramId;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary to set
	 */
	public void setSummary(String summary) {
		this.summary= summary;
	}
    
    
	/**
	 * @return the issue
	 */
	public String getIssue() {
		return issue;
	}

	/**
	 * @param issue to set
	 */
	public void setIssue(String issue) {
		this.issue= issue;
	}
    
    
	/**
	 * @return the issue relationship
	 */
	public String getIssueRelationship() {
		return issueRelationship;
	}

	/**
	 * @param issue relationship to set
	 */
	public void setIssueRelationship(String issueRelationship) {
		this.issueRelationship= issueRelationship;
	}
    
    
	/**
	 * @return the criteria
	 */
	public String getCriteria() {
		return criteria;
	}

	/**
	 * @param critera to set
	 */
	public void setCriteria(String critera) {
		this.criteria = critera;
	}
    
	/**
	 * @return the criteria relationship
	 */
	public String getCriteriaRelationship() {
		return criteraRelationship;
	}

	/**
	 * @param criteria relationship to set
	 */
	public void setCriteriaRelationship(String criteraRelationship) {
		this.criteraRelationship = criteraRelationship;
	}
    
}
