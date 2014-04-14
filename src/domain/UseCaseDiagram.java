package domain;

import java.sql.Date;

public class UseCaseDiagram {
	
	private int useCaseDiagramId;
	private String diagramName;
	private Date createTime;
	private String filePath;
	
	public int getUseCaseDiagramId() {
		return useCaseDiagramId;
	}
	public void setUseCaseDiagramId(int useCaseDiagramId) {
		this.useCaseDiagramId = useCaseDiagramId;
	}
	public String getDiagramName() {
		return diagramName;
	}
	public void setDiagramName(String diagramName) {
		this.diagramName = diagramName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	

}
