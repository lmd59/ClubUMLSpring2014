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
	<title>Promote a Diagram</title>
	<script type="text/javascript">
	    var comment = "";
	    $(document).ready(function(){
			
			$( "#dialog1" ).hide();
	
 			$("#btn1").click(function(){
   				$( "#dialog1" ).dialog();
 			});
 			$( "#dialog2" ).hide();
 			$("#btn2").click(function(){
   			 	$( "#dialog2" ).dialog();
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
		   
				<div class="comment1">
			 		Comment : 
			 		<div class="scroll1" >
			 			<c:forEach items="${requestScope.diagram1comments}" var="comment">
			 			<div class="commenttime">(${comment.commentTime})</div>
			 			<div class="username">${comment.userName}&nbsp;:&nbsp;&nbsp;</div>
			 			<div class="comment">${comment.commentText}</div>
			 			</c:forEach>
			 		</div>
				</div>
				<br/> 
			 
				<div id="dialog1" title="${requestScope.diagramAName} Rationale">	 
	    			<form action="Promote" method="post" onsubmit="return checkRationale(1)">
						Summary:<input type="text" id="summary1" name="summary"/><br/>
						Issue:<input type="text" id="issue1" name="issue"/><br/>
						Issue Relationship:<input type="text" id="issueRelationship1" name="issueRelationship"/><br/>
						Criteria:<input type="text" id="criteria1" name="criteria"/><br/>
						Criteria Relationship:<input type="text" id="criteriaRelationship1" name="criteriaRelationship"/><br/>
						<div class="sumbitbutton">
						<button id="button1" class="pbutton">Submit</button><br/></div>
						<input type="hidden" name="diagramId" value="${requestScope.diagramAId}"/>
						<input type="hidden" name="compareId" value="${requestScope.compareId}"/>
						<input type="hidden" name="A" value="${requestScope.A}"/>
						<input type="hidden" name="B" value="${requestScope.B}"/>
						<input type="hidden" name="file1" value="${requestScope.diagramAId}"/>
						<input type="hidden" name="file2" value="${requestScope.diagramBId}"/>
					</form>
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
				<div id="dialog2" title="${requestScope.diagramBName} Rationale">	 
					<form action="Promote" method="post" onsubmit="return checkRationale(2)">
						Summary:<input type="text" id="summary2" name="summary"/><br/>
						Issue:<input type="text" id="issue2" name="issue"/><br/>
						Issue Relationship:<input type="text" id="issueRelationship2" name="issueRelationship"/><br/>
						Criteria:<input type="text" id="criteria2" name="criteria"/><br/>
						Criteria Relationship:<input type="text" id="criteriaRelationship2" name="criteriaRelationship"/><br/>
						<div class="sumbitbutton">
		                	<!---- <input type="submit" id="button2" value="Promote" class="pbutton"/>--->
							<button id="button2" class="pbutton">Submit</button><br/>
							<input type="hidden" name="diagramId" value="${requestScope.diagramBId}"/>
							<input type="hidden" name="compareId" value="${requestScope.compareId}"/>
							<input type="hidden" name="A" value="${requestScope.A}"/>
							<input type="hidden" name="B" value="${requestScope.B}"/>
							<input type="hidden" name="file1" value="${requestScope.diagramAId}"/>
							<input type="hidden" name="file2" value="${requestScope.diagramBId}"/>
						</div>
					</form>
				</div>
				<div class="comment2">
			 		Comment :
			 		<div class="scroll" >
			 			<c:forEach items="${requestScope.diagram2comments}" var="comment">
			 			<div class="commenttime">(${comment.commentTime})</div>
			 			<div class="username">${comment.userName}&nbsp;:&nbsp;&nbsp;</div>
			 			<div class="comment">${comment.commentText}</div>
			 			</c:forEach>
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
						<input type="hidden" name="comment1" value="${requestScope.diagram1comments}"/>
						<input type="hidden" name="comment2" value="${requestScope.diagram2comments}"/>
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
