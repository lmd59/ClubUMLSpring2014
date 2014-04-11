/**
 * @author AmeyaCJoshi
 * 
 * @description This file contains the client-side validations of the input
 * being sent to the server-side in accordance with the DB
 * constraints.
 * 
 */

function checkRationale(dialog){
    switch(dialog) 
    {
    	case(1):
    		summary = $("#summary1").val();
    		issue = $("#issue1").val();
    		issueRelationship = $("#issueRelationship1").val();
    		criteria = $("#criteria1").val();
    		criteriaRelationship = $("#criteriaRelationship1").val();
    		break;
    	case(2):
    		summary = $("#summary2").val();
    		issue = $("#issue2").val();
    		issueRelationship = $("#issueRelationship2").val();
    		criteria = $("#criteria2").val();
    		criteriaRelationship = $("#criteriaRelationship2").val();
    		break;
   	}
	if (summary == ""){
	   	alert("Please enter valid summary before promoting");
	   	return false;
	}
	if (issue == ""){
	   	alert("Please enter valid issue before promoting");
	   	return false;
	}
	if	(issueRelationship == ""){
	   	alert("Please enter valid issue relationship before promoting");
	   	return false;
	}
	if(criteria == ""){
	   	alert("Please enter valid criteria before promoting");
	   	return false;
	}
	if(criteriaRelationship == ""){
	   	alert("Please enter valid criteria relationship before promoting");
	   	return false;
	}
	
	return true;
}

function checkUpdateProject()
{
	projectNameUpdate = $("#projectNameUpdate").val();
	descriptionUpdate = $("#descriptionUpdate").val();
		
	if(projectNameUpdate == ""){
	   	alert("Please enter valid project name!");
	   	return false;
	}
	if(descriptionUpdate == ""){
	   	alert("Please enter a valid project description!");
	   	return false;
	}
	return true;
}

function checkAddProject()
{
	projectNameAdd = $("#projectNameAdd").val();
	descriptionAdd = $("#descriptionAdd").val();
		
	if(projectNameAdd == ""){
	   	alert("Please enter valid project name!");
	   	return false;
	}
	if(descriptionAdd == ""){
	   	alert("Please enter a valid project description!");
	   	return false;
	}
	return true;
}

function checkUpdatePolicy()
{
	PolicyNameUpdate = $("#PolicyNameUpdate").val();
	PolicyDescriptionUpdate = $("#PolicyDescriptionUpdate").val();
		
	if(PolicyNameUpdate == ""){
	   	alert("Please enter valid policy name!");
	   	return false;
	}
	if(PolicyDescriptionUpdate == ""){
	   	alert("Please enter a valid policy description!");
	   	return false;
	}
	return true;
}

function checkAddPolicy()
{
	PolicyName = $("#PolicyName").val();
	PolicyDescription = $("#PolicyDescription").val();
		
	if(PolicyName == ""){
	   	alert("Please enter valid policy name!");
	   	return false;
	}
	if(PolicyDescription == ""){
	   	alert("Please enter a valid policy description!");
	   	return false;
	}
	return true;
}

function checkAddContext()
{
	contextName = $("#contextName").val();
	project = $("#project").val();
	Description = $("#Description").val();
		
	if(contextName == ""){
	   	alert("Please enter valid context name!");
	   	return false;
	}
	if(project == ""){
	   	alert("Please enter valid project context name!");
	   	return false;
	}
	if(Description == ""){
	   	alert("Please enter a valid context description!");
	   	return false;
	}
	return true;
}

function checkUpdateContext()
{
	contextUpdate = $("#contextUpdate").val();
	projectUpdate = $("#projectUpdate").val();
	descriptionUpdate = $("#descriptionUpdate").val();
		
	if(contextUpdate == ""){
	   	alert("Please enter valid context name!");
	   	return false;
	}
	if(projectUpdate == ""){
	   	alert("Please enter valid project context name!");
	   	return false;
	}
	if(descriptionUpdate == ""){
	   	alert("Please enter a valid context description!");
	   	return false;
	}
	return true;
}