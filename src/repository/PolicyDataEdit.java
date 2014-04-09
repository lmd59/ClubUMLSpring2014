package repository;
import parser.Actor;
import parser.Association;
import parser.ExtendAssociation;
import parser.IncludeAssociation;
import parser.UseCase;
import policy.UserDefinedRule;
import parser.JasonParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class PolicyDataEdit {
	Association assoc = new Association();
	UseCase usecase = new UseCase();
	Actor ac = new Actor();
	JasonParser jp = new JasonParser();
	IncludeAssociation includeAssoc = new IncludeAssociation();
	ExtendAssociation extendAssoc = new ExtendAssociation();
	UserDefinedRule udr = new UserDefinedRule();
	
	
	public Connection dbConnecter()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("java.sql.driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	 
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/rules","root", "1234");
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
	 
		if (connection != null) {
			System.out.println("success!");
			
		} else {
			System.out.println("Failed to make connection!");
		}  
		return connection;
	}
	
	public void getMustHaveActors(Connection conn)
	{
		String sql = "Select * FROM actor";
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("actorName")+rs.getInt("maxUcNum"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getMustHaveUseCases(Connection conn)
	{
		String sql = "Select * FROM usecase";
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("usecaseName")+rs.getInt("maxAcNum"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getMustHaveAssociation(Connection conn)
	{
		String sql = "SELECT associationId,actorName, usecaseName FROM association as assocs " +
				" JOIN actor as ac" +
				" ON ac.actorId = assocs.actorId" +
				" JOIN usecase as uc" +
				" ON uc.usecaseId = assocs.usecaseId" +
				" WHERE mustHave = 1";
		try {

			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql);
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println("===============================\nMust Have:"+" AssociationId:"
							+rs.getString("associationId")+
				"===>ActorName:"+rs.getString("actorName")+" UseCaseName:"+rs.getString("usecaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void getMustNotHaveAssociation(Connection conn)
	{
		String sql = "SELECT associationId,actorName, usecaseName FROM association as assocs " +
				" JOIN actor as ac" +
				" ON ac.actorId = assocs.actorId" +
				" JOIN usecase as uc" +
				" ON uc.usecaseId = assocs.usecaseId" +
				" WHERE mustHave = 0";
		try {
			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql);
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println("===============================\nMust Not Have:"+" AssociationId:"
							+rs.getString("associationId")+
				"===>ActorName:"+rs.getString("actorName")+" UseCaseName:"+rs.getString("usecaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void getMustHaveIncludeRelationship(Connection conn)
	{
		String sql = "SELECT relationshipId, relationshipType, uc.usecaseName AS FromUseCaseName, uc2.usecaseName  AS ToUseCaseName" +
				" FROM usecaserelationship AS ucr " +
				"JOIN usecase as uc " +
				"ON uc.usecaseId = ucr.startUseCaseId " +
				"JOIN usecase as uc2 " +
				"ON uc2.usecaseId = ucr.startUseCaseId " +
				"where mustHave =1 AND relationshipType = 1";
		try {

			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql); => Print Sql String to test
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println(rs.getString("relationshipId")+rs.getString("FromUseCaseName")+rs.getString("ToUseCaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void getMustNotHaveIncludeRelationship(Connection conn)
	{

		String sql = "SELECT relationshipId, relationshipType, uc.usecaseName AS FromUseCaseName, uc2.usecaseName  AS ToUseCaseName" +
				" FROM usecaserelationship AS ucr " +
				"JOIN usecase as uc " +
				"ON uc.usecaseId = ucr.startUseCaseId " +
				"JOIN usecase as uc2 " +
				"ON uc2.usecaseId = ucr.startUseCaseId " +
				"where mustHave =0 AND relationshipType = 1";
		try {

			Statement st = conn.createStatement();
				//System.out.println("SQL String:"+sql);=> Print Sql String to test
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{
					System.out.println(rs.getString("relationshipId")+rs.getString("FromUseCaseName")+rs.getString("ToUseCaseName"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public String insertMustHaveActors(String actorName,long maxUcNum)
	{
		String sql = "Insert INTO actor(actorName,maxUcNum) VALUES(?,?,?)";
		System.out.println(sql);
		return sql;
		
	}
	
	public String insertMustHaveUseCases(String usecaseName, long maxAcNum)
	{
		String sql = "insert INTO usecase (usecaseName,maxAcNum) VALUES('"+usecaseName+"',"+maxAcNum+")";
		System.out.println(sql);
		return sql;
	}
	
	public String insertMustNotHaveAssoc(String actorId, String usecaseId)
	{
		String sql = "INSERT INTO association (actorId,usecaseId,mustHave) " +
				"VALUES('"+actorId+"','"+usecaseId+"',0)";
		System.out.println("not assoc:"+sql);
		return sql;
	}
	
	public String insertMustHaveAssoc(String actorId, String usecaseId)
	{
		String sql = "INSERT INTO association (actorId,usecaseId,mustHave) " +
				"VALUES('"+actorId+"','"+usecaseId+"',1)";
		System.out.println("assoc:"+sql);
		return sql;
	}
	
	public String insertMustHaveIncludeAssocs(String startUseCaseId,String endUseCaseId)
	{
		String sql = "insert INTO usecaserelationship (relationshipType,startUseCaseId,endUseCaseId,mustHave)" +
				"VALUES(1,'"+startUseCaseId+"','"+endUseCaseId+"',1)";
		System.out.println("include"+sql);
		return sql;
	}
	
	public String insertMustNotHaveIncludeAssocs(String startUseCaseId,String endUseCaseId)
	{
		String sql = "insert INTO usecaserelationship (relationshipType,startUseCaseId,endUseCaseId,mustHave)" +
				"VALUES(1,'"+startUseCaseId+"','"+endUseCaseId+"',0)";
		System.out.println("Not include"+sql);
		return sql;
	}
	
	public String insertMustHaveExtendAssoc(String startUseCaseId,String endUseCaseId)
	{
		String sql = "insert INTO usecaserelationship (relationshipType,startUseCaseId,endUseCaseId,mustHave)" +
				"VALUES(0,'"+startUseCaseId+"','"+endUseCaseId+"',1)";
		System.out.println("Extend"+sql);
		return sql;
	}
	
	public String insertMustNotHaveExtendAssoc(String startUseCaseId,String endUseCaseId)
	{
		String sql = "insert INTO usecaserelationship (relationshipType,startUseCaseId,endUseCaseId,mustHave)" +
				"VALUES(0,'"+startUseCaseId+"','"+endUseCaseId+"',0)";
		System.out.println("Not Extend"+sql);
		return sql;
	}
	
	
	public int executeInsert(Connection conn, String sql)
	{
		int newId=0;
		try {

			PreparedStatement st = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				//System.out.println("SQL String:"+sql);=> Print Sql String to test
				ResultSet rs= st.getGeneratedKeys();
				//st.execute(sql);
				
				if(rs.next())
				{

					newId = rs.getInt(1);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		return newId;
	}
	
	public void insertPolicy(/*UserDefinedRule udr*/)
	{
		//test===========================
		Actor actor = new Actor();
		actor.setName("test1");
		udr.setMustHaveActors(actor);
		//===========================
		Connection conn = dbConnecter();
		if(udr.getMustHaveActors().size()!=0)
		{
			for(Actor a:udr.getMustHaveActors())
			{
				String sql = insertMustHaveActors(a.getName(),a.getUcMaxNum());		
				System.out.println(executeInsert(conn,sql));
			}
		}
		/*if(udr.getMustHaveUseCases().size()!=0)
		{
			for(UseCase u:udr.getMustHaveUseCases())
			{
				String sql = insertMustHaveUseCases(u.getName(),u.getMaxActNum());
				executeInsert(conn,sql);
			}
		}
		if(udr.getMustHaveAssocs().size()!=0)
		{	
			for(Association a:udr.getMustHaveAssocs())
			{
				String sql = insertMustHaveAssoc(a.getActorName(),a.getUseCaseName());
				executeInsert(conn,sql);
			}
			
		}
		if(udr.getMustNotHaveAssocs().size()!=0)
		{
			for(Association a:udr.getMustNotHaveAssocs())
			{
				String sql =insertMustNotHaveAssoc(a.getActorName(),a.getUseCaseName());
				executeInsert(conn,sql);
			}
		}
		if(udr.getMustHaveIncludeAssociations().size()!=0)
		{
			for(IncludeAssociation ia:udr.getMustHaveIncludeAssociations()){
				String sql =insertMustHaveIncludeAssocs(ia.getBaseUseCaseName(),ia.getAddUseCaseName());
			executeInsert(conn,sql);
			}
		}
		if(udr.getMustNotHaveIncludeAssociations().size()!=0)
		{
			for(IncludeAssociation ia:udr.getMustHaveIncludeAssociations()){
				String sql =insertMustNotHaveIncludeAssocs(ia.getBaseUseCaseName(),ia.getAddUseCaseName());
				executeInsert(conn,sql);
				}
		}
		if(udr.getMustHaveExtendAssociations().size()!=0)
		{
			for(ExtendAssociation ia:udr.getMustHaveExtendAssociations()){
				String sql =insertMustHaveExtendAssoc(ia.getBaseUseCaseName(),ia.getExtendUseCaseName());
				executeInsert(conn,sql);
				}
		}
		if(udr.getMustNotHaveExtendAssociations().size()!=0)
		{
			for(ExtendAssociation ia:udr.getMustNotHaveExtendAssociations()){
				String sql =insertMustNotHaveExtendAssoc(ia.getBaseUseCaseName(),ia.getExtendUseCaseName());
				executeInsert(conn,sql);
				}
		}*/
		
		try{
			if(conn!=null){
				conn.close();
				System.out.println("Database Connection Closed");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void getPolicy()
	{	
		Connection conn = dbConnecter();
		getMustHaveAssociation(conn);
		getMustNotHaveAssociation(conn);
		getMustHaveIncludeRelationship(conn);
		getMustNotHaveIncludeRelationship(conn);
		getMustHaveActors(conn);
		getMustHaveUseCases(conn);
		try{
		conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String []args)
	{
		
		PolicyDataEdit pde = new PolicyDataEdit();
		pde.insertPolicy();
	}
	
}
