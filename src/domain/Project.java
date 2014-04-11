package domain;

import java.sql.Date;

/**
 * Information class that contains all the features of one project
 * @ doc author	Dong Guo
 * @author Siddhesh
 * @author Aniket Hajirnis
 * @author Indrajit Kulkarni
 */

public class Project {

	private int projectId;
	private String projectName;
	private String description;
	private Date startDate;
	private boolean enabled;
	private Date disabledDate; 

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param projectName
	 * 			The name of the project
	 * @param string4 
	 * @param b 
	 * @param string3 
	 * @param string2 
	 * @param string 
	 */
	public Project(String projectName, String description, Date startDate,
			boolean enabled, Date disabledDate) {
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.enabled = enabled;
		this.disabledDate = disabledDate;
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
	 */
	public Project(int projectId, String projectName, String description) { 
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
	}
	

	
	/**
	 * 
	 * @param projectId
	 * @param projectName
	 * @param description
	 * @param startDate
	 * @param enabled
	 * @param disabledDate
	 */
	public Project(int projectId, String projectName, String description, Date startDate,
			boolean enabled, Date disabledDate) {
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		this.setEnabled(enabled);
		this.setDisabledDate(disabledDate);
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
	 * @param startDate
	 * 			The startDate of this project
	 */
	public Project(int projectId, String projectName, String description,
			Date startDate) {
		
		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.startDate = startDate;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param projectName
	 * 			The name of the project 
	 * @param description
	 * 			The description of the project			
	 * @param startDate
	 * 			The enabled value of the project
	 */
	public Project(String projectName, String description,
			boolean enabled) {
		
		this.projectName = projectName;
		this.description = description;
		this.enabled = enabled;
		// TODO Auto-generated constructor stub
	}

	public Project(String projectName, String description,
			Boolean projectstatus, int projectId) {
		

		this.projectId = projectId;
		this.projectName = projectName;
		this.description = description;
		this.enabled = projectstatus;
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
	 * Get the projectName
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

	public Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(Date disabledDate) {
		this.disabledDate = disabledDate;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
