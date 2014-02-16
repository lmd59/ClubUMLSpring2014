<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="background-color: #F3F3F3" xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="stylesheet" type="text/css" href="css/display.css"/>
	 <link rel="stylesheet" type="text/css" href="style.css"/>
	<link rel="stylesheet" type="text/css" href="promote.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  	
   	<!----Kareem Added---->
  	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/pepper-grinder/jquery-ui.css" media="screen" rel="stylesheet" type="text/css">
  	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js" type="text/javascript"></script>
  	<script type="text/javascript" src="jquery-ui-form.js"></script>
  	<link rel="stylesheet" href="promote_style.css" />
  	
	
	
	<title>Promote a Diagram</title>
	<script type="text/javascript">
	    var comment = "";
	    
	    $(document).ready(function(){

			$( "#dialog1" ).hide();

 			$("#btn1").click(function(){
   				$( "#dialog1" ).dialog({height: 850, width:650 });
 			});
 			$( "#dialog2" ).hide();
 			$("#btn2").click(function(){
   			 	$( "#dialog2" ).dialog({height: 850, width:650 });
  			});
 			
 			$("#suggestButton").click(function() {
  	           $("#suggestForm").submit();
  	       	});
  			
  			$("#returnButton").click(function() {
   	           $("#returnForm").submit();
   	       	});

			var report = "reports/";
			var link = $("#reportLink").val();
			var reportLink = link.substring(link.lastIndexOf("/"));
			report = report + reportLink;
			$("#saveAsPdf").click(function(e){
			    window.open(report,'Download');
			});
	    });
	    
	    function checkComments(dialog){
	    	switch(dialog) {
	    		case(1):comment = $("#comment1").val();
	    				break;
	    		case(2):comment = $("#comment2").val();
	    				break;
	    	}
		if(comment == ""){
		    alert("Please enter comment before promoting");
		    return false;
		}
		return true;
	    }
	    
	    function checkRationale(dialog){
	    	switch(dialog) {
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
			if(critieraRelationship == ""){
		    	alert("Please enter valid criteria relationship before promoting");
		    	return false;
			}
			
			return true;
	    	}

	</script>
	<style>

</style>
    </head>

    <body style="background-color: #F3F3F3">
	<div id="myHeader">
	    <h1 id="banner">Promote Diagram</h1>
	    <input type="hidden" id="reportLink" value="${requestScope.reportPath}"></input>
	</div>
	<div id="promote-container">
	    <div class="file1" style="float:left;">

			<div class="image-wrap">
				<img  src="${requestScope.path1}"/>
			</div>		   

			<div class="center">
				<div class="promote-button-wrap">
					<button id="btn1" class="pbutton">Promote</button>
				</div>
		   
				<div class="rationale1">
			 		Rationale : 
			 		<div class="scroll1">
			 			<c:forEach items="${requestScope.diagram1rationales}" var="rationale">
			 			<div class="comment">
			 				<table>
					  		<tr>
					  			<td align="left"><label for="" style="vertical-align: top; font-weight:bold;">${rationale.rationaleTime}</label></td>
					  			<td><label>${rationale.userName}: ${rationale.promotedDiagramName} vs. ${rationale.alternativeDiagramName}</label></td>
					   		</tr>
					   						  
						  	<tr>
							  	<td align="right"><label for="" style="vertical-align: top;">Summary:</label></td>
					  			<td><label>${rationale.summary}</label></td>
					  		</tr>
					  	
					  		<tr>
					  			<td align="right"><label for="" style="vertical-align: top;">Issue:</label></td>
					  			<td><label>${rationale.issue}</label></td>	
						  	</tr>
					  	
						  	<tr>
						  		<td></td>
						  		<td><label>Issue Relationship: ${rationale.issueRelationship}</label></td>	
						  	</tr>
						  	
						  	<tr>
						  		<td align="right"><label for="" style="vertical-align: top;">Criteria</label></td>
						  		<td><label>${rationale.criteria}</label></td>	
						  	</tr>
						  	
						  	<tr>
						  		<td></td>
						  		<td><label>Criteria Relationship: ${rationale.criteriaRelationship}</label></td>	
						  	</tr>
						  	
						  	</table>
					  	</div>
			 			</c:forEach>
			 		</div>
				</div>
				<br/> 

				<div id="dialog1" title="${requestScope.diagramAName} Rationale">	 
	    			<h1>Rationale Diagram1 Promotion</h1>
					<h2>Alternative to ${requestScope.diagramBName}</h2>
	    			<form action="Promote" method="post" onsubmit="return checkRationale(1)">
					 
					 <fieldset>
					 
					 <legend font-weight:bold;>Promote Diagram1</legend>	
					 
					 <table style="width:300px">	
					  
					  <tr>
					  <td align="right">	
					   	<label for="" style="vertical-align: top; font-weight:bold;">Summary</label>
					  </td>
					  
					  <td>
					  	<textarea name="summary" id="summary1" placeholder="Summary" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					  
					  <tr>
					  <td align="right">	
					   	<label for="" style="vertical-align: top; font-weight:bold;">Issue</label>
					  </td>
					  
					  <td>
					   <textarea name="issue" id="issue1" placeholder="Issues" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					   
					   
					  <tr>
					  <td align="right">	
					   	<label for="" style="vertical-align: top; font-weight:bold;">Issue Relationship</label>
					  </td>
					  
					  <td>
					    <textarea name="issueRelationship" id="issueRelationship1" placeholder="Issue Relationship" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					   
					   
					  <tr>
					  <td align="right">	
					   	<label for="" style="vertical-align: top; font-weight:bold;">Criteria</label>
					  </td>
					  
					  <td>
					  
					  	 <textarea name="criteria" id="criteria1" placeholder="Criteria" cols="5" rows="5"></textarea>
					   
					   </td>
					   </tr>
					   
					   
					  <tr>
					  <td align="right">	
					   	<label for="" style="vertical-align: top; font-weight:bold;">Criteria Relationship</label>
					  </td>
					  
					  <td>
					  
					  	<textarea name="criteriaRelationship" id="criteriaRelationship1" placeholder="Criteria Relationship" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					    
					 </table>
	 			
	 				
    				<table align="center">
      				<tr>
      				<td>
      				<div class="sumbitbutton">	
						<button id="button1" class="pbutton">Promote</button><br/></div>
					</td>
					<td>
					<div class="sumbitbutton">
						<button id="button1" class="pbutton">Cancel</button><br/></div>
					</td>
					</tr>
		 			</table>
						
						<input type="hidden" name="diagramId" value="${requestScope.diagramAId}"/>
						<input type="hidden" name="alternativeDiagramId" value="${requestScope.diagramBId}"/>
						<input type="hidden" name="compareId" value="${requestScope.compareId}"/>
						<input type="hidden" name="A" value="${requestScope.A}"/>
						<input type="hidden" name="B" value="${requestScope.B}"/>
						<input type="hidden" name="file1" value="${requestScope.diagramAId}"/>
						<input type="hidden" name="file2" value="${requestScope.diagramBId}"/>
						
					 </fieldset>	
					</form>
	    		</div>
	    		</div>
	    	</div>
		</div>

	    <div class="file2">
			<div class="image-wrap">
				<img src="${requestScope.path2}" />
			</div>
			<div class="center">

				<div class="promote-button-wrap">
					<button id="btn2" class="pbutton">Promote</button>
				</div>
				
				<div class="rationale2">
			 		Rationale : 
			 		<div class="scroll" >
			 			<c:forEach items="${requestScope.diagram2rationales}" var="rationale">
			 			<div class="comment">
			 				<table>
					  		<tr>
					  			<td align="left"><label for="" style="vertical-align: top; font-weight:bold;">${rationale.rationaleTime}</label></td>
					  			<td><label>${rationale.userName}: ${rationale.promotedDiagramName} vs. ${rationale.alternativeDiagramName}</label></td>
					   		</tr>
					   						  
						  	<tr>
							  	<td align="right"><label for="" style="vertical-align: top;">Summary:</label></td>
					  			<td><label>${rationale.summary}</label></td>
					  		</tr>
					  	
					  		<tr>
					  			<td align="right"><label for="" style="vertical-align: top;">Issue:</label></td>
					  			<td><label>${rationale.issue}</label></td>	
						  	</tr>
					  	
						  	<tr>
						  		<td></td>
						  		<td><label>Issue Relationship: ${rationale.issueRelationship}</label></td>	
						  	</tr>
						  	
						  	<tr>
						  		<td align="right"><label for="" style="vertical-align: top;">Criteria</label></td>
						  		<td><label>${rationale.criteria}</label></td>	
						  	</tr>
						  	
						  	<tr>
						  		<td></td>
						  		<td><label>Criteria Relationship: ${rationale.criteriaRelationship}</label></td>	
						  	</tr>
						  	
						  	</table>
					  	</div>
			 			</c:forEach>
			 		</div>
				</div>
				<br/> 
				<div id="dialog2" title="${requestScope.diagramBName} Rationale">	 
					<h1>Rationale Diagram2 Promotion</h1>
					<h2>Alternative to ${requestScope.diagramAName}</h2>
					<form action="Promote" method="post" onsubmit="return checkRationale(2)">
					 
					 <fieldset>
					 
					 <legend font-weight:bold;>Promote Diagram2</legend>	
					 
					 <table style="width:300px">	
					  
					  <tr>
					  <td align="right">	
					   	<label for="summary2" style="vertical-align: top; font-weight:bold;">Summary</label>
					  </td>
					  
					  <td>
					  	<textarea name="summary" id="summary2" placeholder="Summary" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					  
					  <tr>
					  <td align="right">	
					   	<label for="Issue" style="vertical-align: top; font-weight:bold;">Issue</label>
					  </td>
					  
					  <td>
					   <textarea name="issue" id="issue2" placeholder="Issues" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					   
					   
					  <tr>
					  <td align="right">	
					   	<label for="" style="vertical-align: top; font-weight:bold;">Issue Relationship</label>
					  </td>
					  
					  <td>
					    <textarea name="issueRelationship" id="issueRelationship2" placeholder="Issue Relationship" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					   
					   
					  <tr>
					  <td align="right">	
					   	<label for="Criteria" style="vertical-align: top; font-weight:bold;">Criteria</label>
					  </td>
					  
					  <td>
					  
					  	 <textarea name="criteria" id="criteria2" placeholder="Criteria" cols="5" rows="5"></textarea>
					   
					   </td>
					   </tr>
					   
					   
					  <tr>
					  <td align="right">	
					   	<label for="Criteria Relationship" style="vertical-align: top; font-weight:bold;">Criteria Relationship</label>
					  </td>
					  
					  <td>
					  
					  	<textarea name="criteriaRelationship" id="criteriaRelationship2" placeholder="Criteria Relationship" cols="5" rows="5"></textarea>
					   </td>
					   </tr>
					    
					 </table>
	 			
	 				
    				<table align="center">
      				<tr>
      				<td>
      				<div class="sumbitbutton">	
						<button id="button1" class="pbutton">Promote</button><br/></div>
					</td>
					<td>
					<div class="sumbitbutton">
						<button id="button1" class="pbutton">Cancel</button><br/></div>
					</td>
					</tr>
		 			</table>
					
					<input type="hidden" name="diagramId" value="${requestScope.diagramBId}"/>
							<input type="hidden" name="alternativeDiagramId" value="${requestScope.diagramAId}"/>							<input type="hidden" name="compareId" value="${requestScope.compareId}"/>
							<input type="hidden" name="A" value="${requestScope.A}"/>
							<input type="hidden" name="B" value="${requestScope.B}"/>
							<input type="hidden" name="file1" value="${requestScope.diagramAId}"/>
							<input type="hidden" name="file2" value="${requestScope.diagramBId}"/>
						</fieldset>
					</form>
				</div>
				</div>
			</div>
	    </div>
	    <p>${requestScope.compareId}</p>
		<br/>
		<div class="reportfile" style="float:left;">
			<!---- <div id="reportLink" style="display: none">${requestScope.reportPath}</div>---->

			<div  class="scrollreport">

			<pre>${requestScope.reportText}</pre>

			</div>
			<form class="putton" action="Compare" method="post" id="suggestForm">
						<input type="hidden" name="smartsuggest" value="Suggest Promote"/> 
						<input type="hidden" name="reportText" value="${requestScope.reportText}"/>
						<input type="hidden" name="reportPath" value="${requestScope.reportPath}"/>
						<input type="hidden" name="file1" value="${requestScope.diagramAId}"/>
						<input type="hidden" name="file2" value="${requestScope.diagramBId}"/>
						<input type="hidden" name="rationale1" value="${requestScope.diagram1rationales}"/>
						<input type="hidden" name="rationale2" value="${requestScope.diagram2rationales}"/>
			</form>
			<form action="Display" method="post" id="returnForm"></form>
			<div class="promote-bottom-button-wrap">
				<div class="promote-bottom-button">
					<button id="saveAsPdf" class="pbutton">Save As PDF</button>
					<button  id="suggestButton" class="pbutton" type="submit">Suggestion Promote</button>
					<button  class="pbutton" id="returnButton">Return</button>
				</div>
			</div>
		</div>
	</div>
	  
</body>

</html>