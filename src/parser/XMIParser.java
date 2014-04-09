package parser;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import policy.UserDefinedRule;
import java.io.*;
/**
 *
 * @author Chao Li
 */
public class XMIParser {
	//UseCaseDiagramItems cdi = new UseCaseDiagramItems();
	Association assoc = new Association();
	UseCase usecase = new UseCase();
	Actor ac = new Actor();
	JasonParser jp = new JasonParser();
	IncludeAssociation includeAssoc = new IncludeAssociation();
	ExtendAssociation extendAssoc = new ExtendAssociation();
	UserDefinedRule udr = new UserDefinedRule();
	private ArrayList <Actor> actors = new ArrayList<Actor>(); 
	private ArrayList<Association> association= new ArrayList<Association>();
	private ArrayList<String> temp = new ArrayList<String>();
	private ArrayList<UseCase> usecases = new ArrayList<UseCase>();
	private ArrayList<IncludeAssociation> includeAssocs = new ArrayList<IncludeAssociation>();
	private ArrayList<ExtendAssociation> extendAssocs = new ArrayList<ExtendAssociation>();
	private ArrayList<String> mustHaveActors = new ArrayList<String>();
	private ArrayList<String>mustHaveUseCases = new ArrayList<String>(); 
	private ArrayList<Association> mustNotHaveAssocs = new ArrayList<Association>();
	private ArrayList<Association> mustHaveAssoc = new ArrayList<Association>();
	private ArrayList<IncludeAssociation> mustHaveIncludeAssociation = new ArrayList<IncludeAssociation>();
	private ArrayList<IncludeAssociation> mustNotHaveIncludeAssociation = new ArrayList<IncludeAssociation>();
	private ArrayList<ExtendAssociation> mustHaveExtendAssociation = new ArrayList<ExtendAssociation>();
	private ArrayList<ExtendAssociation> mustNotHaveExtendAssociation = new ArrayList<ExtendAssociation>();
	//private ArrayList <Object> xmiData = new ArrayList();
	private String associationName="";
	private int counter=0;
	public XMIParser XMLReader(String fileName) throws DocumentException
	{
		XMIParser xmi = new XMIParser();
		//XMIParser xmitemp = new XMIParser();
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(fileName));
		Element root = doc.getRootElement();
		parse(root);
		setAssociation(getAssociationData(temp,association));
		/*printActor(getActors());//get actor_name actor_id;
		printUseCase(getUseCases());//get usecase_name usecase_id;
		printAssociation(getAssociation());//get association_id usecase_id actor_id;
		printExtendAssociation(getExtendAssociations());
		printIncludeAssociation(getIncludeAssociations());*/
		//testUserDefineRules();
		//checkMustHave();
		//checkMustNotHave();
		//System.out.println("=============================JSON DATA START=====================================");
		//jp.parser();
		//System.out.println(xmi.getActors());
		xmi.setActors(getActors());
		xmi.setUsecases(getUsecases());
		xmi.setAssociation(getAssociation());
		xmi.setIncludeAssocs(getIncludeAssocs());
		xmi.setExtendAssocs(getExtendAssocs());

		return xmi;
	}
    
	public void parse(Element ele)
	{
		parseAttribute(ele);
		List el = ele.elements();
		for(Object e:el)
		{
			Element element = (Element)e;
			if(!element.isTextOnly())
			{
				parse(element);			
			}
			else
			{
				parseAttribute(element);			
			}
		}	
	}
	
	public void parseAttribute(Element ele)
	{
		
		List attList = ele.attributes();
		for(Object e:attList)
		{
			boolean open = true;
			Attribute attr = (Attribute)e;
			if(ele.getName().equals("Association")&&attr.getQName().getName().equals("xmi.id"))
			{
				associationName = attr.getValue();
				temp.add(associationName);//store the association name in temp
				 //This is the assoication name.
			}
				if(ele.getName().equals("AssociationEnd")&&attr.getQName().getName().equals("association"))//get association
				{
					if(attr.getValue().equals(associationName));
					{
						temp.add(ele.attribute(10).getValue());//store usecase_id and actor_id 
					}				
				}
				else if(ele.getName().equals("Actor"))//get actor
				{
					counter++;
						ac.setName(ele.attribute(1).getValue());
						ac.setId(ele.attribute(0).getValue());
						if(counter%10==1)
						{
							actors.add(ac);
							ac = new Actor();
						}
				}
				else if(ele.getName().equals("UseCase"))//get usecase
				{
					counter++;
					if(counter%10==1)
					{
						usecase.setId(ele.attribute(0).getValue());
						usecase.setName(ele.attribute(1).getValue());
						usecases.add(usecase);
						usecase = new UseCase();
					}			
				}
				else if(ele.getName().equals("Include"))
				{	
						counter++;
						if(counter%7==1)//System.out.println(ele.attribute(1).getValue(),when meet 7, add to the arrayList;
						{
							includeAssoc.setBaseUseCaseId(ele.attribute(5).getValue());					
							includeAssoc.setAddUseCaseId(ele.attribute(6).getValue());
							includeAssoc.setId(ele.attribute(0).getValue());
							includeAssoc.setAssocType("include");
							for(UseCase ia:getUsecases())
							{
								if(ia.getId().equals(ele.attribute(5).getValue()))
								{
									includeAssoc.setBaseUseCaseName(ia.getName());
								}
								else if(ia.getId().equals(ele.attribute(6).getValue()))
								{
									includeAssoc.setAddUseCaseName(ia.getName());
								}
							}
							includeAssocs.add(includeAssoc);
							includeAssoc = new IncludeAssociation();
						}
				}
				else if(ele.getName().equals("Extend"))
				{
					counter++;	
					if(counter%7==1)
					{
						extendAssoc.setBaseUseCaseId(ele.attribute(5).getValue());
						extendAssoc.setExtendUseCaseId(ele.attribute(6).getValue());
						extendAssoc.setId(ele.attribute(0).getValue());
						extendAssoc.setAssocType("extend");
						for(UseCase ia:getUsecases())
						{
							if(ia.getId().equals(ele.attribute(5).getValue()))
							{
								extendAssoc.setBaseUseCaseName(ia.getName());
							}
							else if(ia.getId().equals(ele.attribute(6).getValue()))
							{
								extendAssoc.setExtendUseCaseName(ia.getName());
							}
						}
						extendAssocs.add(extendAssoc);
						extendAssoc = new ExtendAssociation();
					}

				}
		}
	}
	
	

	/*======================test printer functions start===============================*/
	public void printActor(ArrayList<Actor> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println(array.get(i).getId()+" "+array.get(i).getName());
		}
	}
	
	public void printUseCase(ArrayList<UseCase> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println(array.get(i).getId()+" "+array.get(i).getName());
		}
	}
	
	public void printIncludeAssociation(ArrayList<IncludeAssociation> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println("IncludeId:"+array.get(i).getId()+"      From:"+array.get(i).getBaseUseCaseId()+":"+array.get(i).getBaseUseCaseName()+"-----------> To:"+array.get(i).getAddUseCaseId()
					+":"+array.get(i).getAddUseCaseName()+" Type:"+array.get(i).getAssocType());
		}
	}
	
	public void printExtendAssociation(ArrayList<ExtendAssociation> array)
	{
		for(int i=0;i<array.size();i++)
		{
			System.out.println("ExtendId:"+array.get(i).getId()+"      From:"+array.get(i).getBaseUseCaseId()+":"+array.get(i).getBaseUseCaseName()+"------------>To:"+array.get(i).getExtendUseCaseId()
					+":"+array.get(i).getExtendUseCaseName()+" Type:"+array.get(i).getAssocType());
		}
	}
	
	public void printAssociation(ArrayList<Association> as)
	{
		for(int i=0;i<as.size();i++)//loop in associations
		{
			for(Actor a:actors)//search actors name
			{
				for(UseCase u:usecases){ // search usecase name
					if(as.get(i).getActorId().equals(a.getId())&&as.get(i).getUseCaseId().equals(u.getId()))
					{
						System.out.println("Association:"+" "+as.get(i).getActorId()+":"+a.getName()+"---------"
					+as.get(i).getAssocId()+"---------"+as.get(i).getUseCaseId()+":"+u.getName()+" Type:"+as.get(i).getAssocType());
					}
				}
			}
			
		}
	}
	/*==================================test print functions end==================================*/
	/*===========================get and set functions start==============================*/
	
	/*get and set actor,include,extend,association and usecases===============end */
	
	public ArrayList<Actor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<Actor> actors) {
		this.actors = actors;
	}

	public ArrayList<Association> getAssociation() {
		return association;
	}

	public void setAssociation(ArrayList<Association> association) {
		this.association = association;
	}

	public ArrayList<UseCase> getUsecases() {
		return usecases;
	}

	public void setUsecases(ArrayList<UseCase> usecases) {
		this.usecases = usecases;
	}

	public ArrayList<IncludeAssociation> getIncludeAssocs() {
		return includeAssocs;
	}

	public void setIncludeAssocs(ArrayList<IncludeAssociation> includeAssocs) {
		this.includeAssocs = includeAssocs;
	}

	

	public ArrayList<ExtendAssociation> getExtendAssocs() {
		return extendAssocs;
	}

	public void setExtendAssocs(ArrayList<ExtendAssociation> extendAssocs) {
		this.extendAssocs = extendAssocs;
	}

	public ArrayList<Association> getAssociationData(ArrayList<String> data,ArrayList<Association> tempAssociation)
	{
		//System.out.println("size:"+data.size());
		// we store associations in an arraylist [i%3=0]:Associd [i%3=1]:actorId [i%3=2]:setUseCaseId. Every three index is a association group.
		for(int i=0;i<data.size();i++)
		{
		if(i%3==0)
		{
			assoc.setAssocId(data.get(i));
		}
		else if(i%3==1)
		{
			assoc.setActorId(data.get(i));
			for(Actor act:getActors())
			{
				if(data.get(i).equals(act.getId()))
				{
					assoc.setActorName(act.getName());
				}
			}
		}
		else if(i%3==2)
		{
			assoc.setUseCaseId(data.get(i));
			for(UseCase uc:getUsecases())
			{
				if(data.get(i).equals(uc.getId()))
				{
					assoc.setUseCaseName(uc.getName());
				}
			}
			assoc.setAssocType("association");
			tempAssociation.add(assoc);
			assoc = new Association();
		}
		}
		return tempAssociation;
	}
	

	
/*	public void testUserDefineRules()//Test user defined rules
	{
		System.out.println("");
		System.out.println("Must have actor:Student" +
				"\nMust have use case: Add Course" +
				"\nMust have relationship Actor1 with Project"+
				"\nMust have include association CheckPermission with Project"+
				"\nMust have extend Association Document with Make comments"+
				"\nMust not have relationship: Actor2 with Project");
		//===============must have assoc
		assoc.setActorName("Actor1");
		assoc.setUseCaseName("Project");
		assoc.setAssocType("association");
		udr.setMustHaveAssoc(assoc);
		mustHaveAssoc.add(udr.getMustHaveAssoc());
		//===============must have actor
		udr.setMustHaveActorName("Student");
		mustHaveActors.add(udr.getMustHaveActorName());
		//===============must have usecase
		udr.setMustHaveUseCaseName("Add Course");
		mustHaveUseCases.add(udr.getMustHaveUseCaseName());
		//===============must have include Assoc
		includeAssoc.setAddUseCaseName("CheckPermission");
		includeAssoc.setBaseUseCaseName("Project");
		udr.setMustHaveIncludeAssociation(includeAssoc);
		mustHaveIncludeAssociation.add(udr.getMustHaveIncludeAssociation());
		//==============must have extend assoc
		extendAssoc.setBaseUseCaseName("Document");
		extendAssoc.setExtendUseCaseName("MakeComments");
		udr.setMustHaveExtendAssociation(extendAssoc);
		mustHaveExtendAssociation.add(udr.getMustHaveExtendAssociation());
		//=============must not have association
		assoc.setActorName("Actor2");
		assoc.setUseCaseName("Project");
		assoc.setAssocType("association");
		udr.setMustNotHaveAssoc(assoc);
		mustNotHaveAssocs.add(udr.getMustNotHaveAssoc());
		System.out.println("");
		
	}*/
}