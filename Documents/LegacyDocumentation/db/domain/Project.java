package domain;

/**
 * Information class that contains all the features of one project
 * @ doc author	Dong Guo
 */

public class Project {

	private int projectId;
	private String projectName;
	private String description;
	private byte achived = 0;// Default
	private String startDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param projectName
	 * 			The name of the project
	 */
	public Project(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param projectId
	 * 			The ID of the project
	 * @param projectName
	 * 			The name of the project 
	 * @param description
	 * 			The description of the project			
	 * @param achived
	 * 			This value indicates whether the project is achived or not
	 */
	public Project(int projectId, String projectName, String description,
			byte achived) { 
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.achived = achived;
	}
	
	/**
	 * Gete the projectId
	 * 
	 * @return projectId int
	 */
	public int getProjectId() {
		return projectId;
	}
	
	/**
	 * Set projectId
	 * 
	 * @param projectId
	 * 			The ID of the project
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * Gete the projectName
	 * 
	 * @return projectName String
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * Set projectName
	 * 
	 * @param projectName
	 * 			The name of the project
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * Gete the description
	 * 
	 * @return description String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description
	 * 
	 * @param description
	 * 			The description of the project
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gete the achived
	 * 
	 * @return achived byte
	 */
	public byte isAchived() {
		return achived;
	}

	/**
	 * Set achived
	 * 
	 * @param achived
	 * 			It indicates whether the project is achived or not.
	 */
	public void setAchived(byte achived) {
		this.achived = achived;
	}

}
