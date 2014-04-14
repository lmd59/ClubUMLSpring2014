package policy;

import parser.Association;
import parser.Actor;
import parser.UseCase;
import parser.IncludeAssociation;
import parser.ExtendAssociation;
import java.util.ArrayList;

public class UserDefinedRule
{
	private int maximun = 0;
	private Association mustHaveAssoc = new Association();
	private Association mustNotHaveAssoc = new Association();
	private String mustHaveUseCaseName;
	private String mustHaveActorName;
	private IncludeAssociation mustHaveIncludeAssociation = new IncludeAssociation();
	private ExtendAssociation mustHaveExtendAssociation = new ExtendAssociation();
	private String associationName;
	private String assocType;
	private int maximumOfActors = 0;
	private int maximumOfUseCases = 0;
	public int getMaximun() {
		return maximun;
	}
	public void setMaximun(int maximun) {
		this.maximun = maximun;
	}
	public Association getMustHaveAssoc() {
		return mustHaveAssoc;
	}
	public void setMustHaveAssoc(Association mustHaveAssoc) {
		this.mustHaveAssoc = mustHaveAssoc;
	}
	public Association getMustNotHaveAssoc() {
		return mustNotHaveAssoc;
	}
	public void setMustNotHaveAssoc(Association mustNotHaveAssoc) {
		this.mustNotHaveAssoc = mustNotHaveAssoc;
	}
	public String getMustHaveUseCaseName() {
		return mustHaveUseCaseName;
	}
	public void setMustHaveUseCaseName(String mustHaveUseCaseName) {
		this.mustHaveUseCaseName = mustHaveUseCaseName;
	}
	public String getMustHaveActorName() {
		return mustHaveActorName;
	}
	public void setMustHaveActorName(String mustHaveActorName) {
		this.mustHaveActorName = mustHaveActorName;
	}
	public IncludeAssociation getMustHaveIncludeAssociation() {
		return mustHaveIncludeAssociation;
	}
	public void setMustHaveIncludeAssociation(
			IncludeAssociation mustHaveIncludeAssociation) {
		this.mustHaveIncludeAssociation = mustHaveIncludeAssociation;
	}
	public ExtendAssociation getMustHaveExtendAssociation() {
		return mustHaveExtendAssociation;
	}
	public void setMustHaveExtendAssociation(
			ExtendAssociation mustHaveExtendAssociation) {
		this.mustHaveExtendAssociation = mustHaveExtendAssociation;
	}
	public int getMaximumOfActors() {
		return maximumOfActors;
	}
	public void setMaximumOfActors(int maximumOfActors) {
		this.maximumOfActors = maximumOfActors;
	}
	public int getMaximumOfUseCases() {
		return maximumOfUseCases;
	}
	public void setMaximumOfUseCases(int maximumOfUseCases) {
		this.maximumOfUseCases = maximumOfUseCases;
	}
}
