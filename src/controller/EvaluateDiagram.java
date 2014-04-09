package controller;
import java.util.ArrayList;

import parser.Actor;
import parser.Association;
import parser.ExtendAssociation;
import parser.IncludeAssociation;
import parser.UseCase;
import parser.XMIParser;
import policy.UserDefinedRule;

public class EvaluateDiagram {
	private ArrayList<String> compareResult = new ArrayList(); 
	


	/*=====================Check User Defined Rules=====================================================*/
	public String checkMustHaveActors(UserDefinedRule rules, XMIParser xmiData)
	{
		//Check Must Have Actors
		String temp="";
		boolean find = false;
				for(Actor ruleActor:rules.getMustHaveActors())
				{
					for(Actor xmiActor: xmiData.getActors())
					{
						if(ruleActor.getName().equals(xmiActor.getName()))
						{
							find = true;
							break;
						}
					}
					if(!find)
					{
						temp = "Actor:\""+ruleActor.getName()+"\" Doesn't exist.-------------------------------NEED TO FIX\n";
						compareResult.add(temp);
						System.out.println(temp);
					}
				}
		return temp;
	}
	
	public String checkMustHaveUseCases(UserDefinedRule rules, XMIParser xmiData)	//Check Must Have UseCase
	{
		String temp="";
		boolean find = false;
		for(UseCase ruleUC:rules.getMustHaveUseCases())
		{
			for(UseCase usecases:xmiData.getUsecases())
			{
				if(ruleUC.getName().equals(usecases.getName()))
				{
					find= true;
					//System.out.println("Get:"+m);
					break;
				}
			}
			if(!find)
			{
				temp = "UseCase:\""+ruleUC.getName()+"\" Doesn't exist-----------------------------------NEED TO FIX\n";
				compareResult.add(temp);
				System.out.println(temp);
			}
			
		}
		return temp;
	}
	
	public String checkMustHaveAssociation(UserDefinedRule rules, XMIParser xmiData)
	{
		//Check Must Have Association
		String temp="";
		boolean find = false;
				for(Association ruleAssoc:rules.getMustHaveAssocs())
				{
					//We planed to check Type First. But association,include and extend are from different
					//class, So we don't need to check type.
					//Check Actor and useCase in this association
					for(Association assocs : xmiData.getAssociation())
					{
						if(ruleAssoc.getActorName().equals(assocs.getActorName())&&ruleAssoc.getUseCaseName().equals(assocs.getUseCaseName()))
						{
							find = true;
							//System.out.println("exist:"+assoc.getActorName()+"-----association------ "+assocs.getUseCaseName()+"---------------------OK");
							break;
						}
					}
					if(!find)
					{
						temp = "Can't find this include association:"+ruleAssoc.getActorName()+" "+ruleAssoc.getUseCaseName()+"---------------------------------NEED TO FIX\n";
						compareResult.add(temp);
						System.out.println(temp);
					}
				}	
			return temp;
	}
	
	public String checkMustNotHaveAssociation(UserDefinedRule rules, XMIParser xmiData)
	{
		//Check Must Have Association
		String temp = "";
		boolean find = false;
				for(Association ruleAssoc:rules.getMustNotHaveAssocs())
				{
					//We planed to check Type First. But association,include and extend are from different
					//class, So we don't need to check type.
					//Check Actor and useCase in this association
					for(Association assocs : xmiData.getAssociation())
					{
						if(ruleAssoc.getActorName().equals(assocs.getActorName())&&ruleAssoc.getUseCaseName().equals(assocs.getUseCaseName()))
						{
							find = true;
							//System.out.println("exist:"+assoc.getActorName()+"-----association------ "+assocs.getUseCaseName()+"---------------------OK");
							break;
						}
						
					}
					if(find)
					{
						temp = "You should not have this association:"+ruleAssoc.getActorName()+" "+ruleAssoc.getUseCaseName()+"---------------------------------NEED TO FIX\n";
						compareResult.add(temp);
						System.out.println(temp);
					}
				}	
			return temp;
	}
	
	public String checkMustHaveExtendAssociation(UserDefinedRule rules, XMIParser xmiData)
	{
		String temp = "";
		boolean find = false;
		//Check Must Have Extend Association
		for(ExtendAssociation ruleEA:rules.getMustHaveExtendAssociations())
		{
			for(ExtendAssociation eas:xmiData.getExtendAssocs())
			{
				if(ruleEA.getBaseUseCaseName().equals(eas.getBaseUseCaseName())&&ruleEA.getExtendUseCaseName().equals(eas.getExtendUseCaseName()))
				{
					find = true;
					break;
				}
			}
			if(!find)
			{
				temp = "Can't find this extend association:"+ruleEA.getBaseUseCaseName()+" "+ruleEA.getExtendUseCaseName()+"---------------------------------NEED TO FIX\n";
				compareResult.add(temp);
				System.out.println(temp);
			}
		}
		return temp;
	}

	public String checkMustHaveIncludeAssociation(UserDefinedRule rules, XMIParser xmiData)
	{
		String temp="";
		boolean find = false;
		//Check Must Have Include Association
				for(IncludeAssociation ia:rules.getMustHaveIncludeAssociations())
				{
					for(IncludeAssociation ias:xmiData.getIncludeAssocs())
					{
						if(ia.getBaseUseCaseName().equals(ias.getBaseUseCaseName())&&ia.getAddUseCaseName().equals(ias.getAddUseCaseName()))
						{
							find = true;
							System.out.println("exist this include association:"+ias.getAddUseCaseName()+"---------------------------------OK");
							break;
						}
					}
					if(!find)
					{
						temp = "You should create this include Association:"+ia.getBaseUseCaseName()+" "+ia.getAddUseCaseName()+"---------------------------------NEED TO FIX\n";
						compareResult.add(temp);
						System.out.println(temp);
					}
				}
				return temp;
	}
	
	public String checkMustNotHaveIncludeAssociation(UserDefinedRule rules, XMIParser xmiData)
	{
		String temp="";
		boolean find = false;
		for(IncludeAssociation ia:rules.getMustNotHaveIncludeAssociations())
		{
			for(IncludeAssociation ias : xmiData.getIncludeAssocs())
			{
				if(ia.getBaseUseCaseName().equals(ias.getBaseUseCaseName())&&ia.getAddUseCaseName().equals(ias.getAddUseCaseName()))
				{
					find = true;
				}			
			}
			if(find)
			{
				temp ="Must Not Have include Association:"+ia.getBaseUseCaseName()+" "+ia.getAddUseCaseName()+"---------------------------------NEED TO FIX\n";
				compareResult.add(temp);
				System.out.println(temp);
			}
		}
		return temp;
		
	}
	
	public String checkMustNotHaveExtendAssociation(UserDefinedRule rules, XMIParser xmiData)
	{
		String temp="";
		boolean find = false;
		for(ExtendAssociation ea:rules.getMustNotHaveExtendAssociations())
		{
			for(ExtendAssociation eas:xmiData.getExtendAssocs())
			{
				if(ea.getBaseUseCaseName().equals(eas.getBaseUseCaseName())&&ea.getExtendUseCaseName().equals(eas.getExtendUseCaseName()))
				{
					find = true;
				}
				if(find)
				{
					temp="You Should Not Have this Extend Association:"+ea.getBaseUseCaseName()+" "+ea.getExtendUseCaseName()+"---------------------------------NEED TO FIX\n";
					compareResult.add(temp);
					System.out.println(temp);
				}
			}
		}
		return temp;
	}
	
	
	public ArrayList<String> getCompareResult() {
		return compareResult;
	}

	public void setCompareResult(ArrayList<String> compareResult) {
		this.compareResult = compareResult;
	}

	public void evaluate(UserDefinedRule rules, XMIParser xmiData)
	{

				checkMustHaveActors(rules,xmiData);
				checkMustHaveUseCases(rules, xmiData);
				checkMustHaveAssociation( rules,  xmiData);
				checkMustNotHaveAssociation( rules,  xmiData);
				checkMustHaveExtendAssociation(rules, xmiData);
				checkMustNotHaveExtendAssociation(rules, xmiData);
				checkMustHaveIncludeAssociation( rules,  xmiData);
				checkMustNotHaveIncludeAssociation( rules,  xmiData);
				setCompareResult(compareResult);
	}
}
