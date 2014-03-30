package domain;

/**
 * Information class that contains all the features of one decision
 * @ doc author	Vishal Patel
 */

public class Decision {

	private int decisionId;

	private String decisionName;
	private int projectId;
	private String decisionTime;
	private int userId;
	private String userName;
	private String diagramName;
    private int diagramId;
//  future list of rationales associated with decision;
    
	/**
	 * Default constructor
	 */
    public Decision(){
    }
    
	/**
	 * Get the Decision Name
	 * 
	 * @return decisionName String
	 */    
    public String getDecisionName() {
        return decisionName;
    }

	/**
	 * Set Decision Name
	 * 
	 * @param decisionName
	 * 			The name of the decision
	 */
    public void setDecisionName(String decisionName) {
        this.decisionName = decisionName;
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
    public int getProjectId() {
        return projectId;
    }

	/**
	 * Set userId
	 * 
	 * @param userId
	 * 			The ID of the user that made this rationale
	 */
    public void setProjectId(int projectId) {
        this.projectId= projectId;
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
	 * Get decisionId
	 * 
	 * @return decisionId
	 */
    public int getDecisionId() {
        return decisionId;
    }
    
	/**
	 * Set decisionId
	 * 
	 * @param decisionId
	 * 			The ID of the decision
	 */
    public void setRationaleId(int decisionId) {
        this.decisionId = decisionId;
    }


	/**
	 * Get the decisionTime
	 * 
	 * @return decisionTime String
	 */
    public String getDecisionTime() {
        return decisionTime;
    }

	/**
	 * Set decisionTime
	 * 
	 * @param decisionTime
	 * 			The time that this decision was created
	 */
    public void setDecisionTime(String decisionTime) {
        this.decisionTime = decisionTime;
    }

	/**
	 * @return the diagramId
	 */
	public int diagramId() {
		return diagramId;
	}

	/**
	 * @param diagramId the o set
	 */
	public void setDiagramId(int diagramId) {
		this.diagramId = diagramId;
	}

    	
	/**
	 * Get the diagram name
	 * 
	 * @return diagramName String
	 */
    public String getDiagramName() {
        return diagramName;
    }

	/**
	 * Set diagramName
	 * 
	 * @param diagramName
	 */
    public void setDiagramName(String diagramName) {
        this.diagramName = diagramName;
    }
}
