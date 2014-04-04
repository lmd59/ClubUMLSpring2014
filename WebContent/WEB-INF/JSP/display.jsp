<%--
    Document   : display
    Created on : Oct 20, 2012, 2:25:33 PM
    Author     : pratham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/display.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project Diagrams</title>
<link href="css/display.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />

<!-- BOOTSTRAP -->
<link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css"
	rel="stylesheet">
<link
	href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css"
	rel="stylesheet">

<style type="text/css">

<!--
.basictab{
padding: 3px 0;
margin-left: 0;
font: bold 12px Verdana;
border-bottom: 1px solid gray;
list-style-type: none;
text-align: left; /*set to left, center, or right to align the menu as desired*/
}

.basictab li{
display: inline;
margin: 0;
}

.basictab li a{
	text-decoration: none;
	padding: 5px 20px;
	margin-right: 0;
	border: 1px solid gray;
	border-bottom: none;
	background-color: #E0E0E0;
	color: #2d2b2b;
}

.basictab li a:visited{
color: #2d2b2b;
}

.basictab li a:hover{
	background-color: #909090;
	color: black;
}

.basictab li a:active{
color: black;
}

.basictab li.selected a{ /*selected tab effect*/
	position: relative;
	top: 1px;
	padding-top: 4px;
	background-color: #909090;
	color: black;
}

-->

.diagramBox{
	float: left;
	
}

</style>
<link href="jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link href="jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
<link href="jQueryAssets/jquery.ui.tabs.min.css" rel="stylesheet" type="text/css">
<script src="jQueryAssets/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="jQueryAssets/jquery-ui-1.9.2.tabs.custom.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var type = "";
	$(document).ready(function() {
		
		
		$("#createDecision").hide();
		$("#selectDiagram").hide();
			
		$("#createButton").click(function(){
				
				$("#createDecision").dialog({height: 250, width:300 });
				
				$("#button1").click(function(){
					
					$("#selectDiagram").dialog({height: 250, width:300 });
					
				});
		});
		
		
				
		$("#downloadButton").click(function() {
			type = this.id.toString();
		});
		$("#compareButton").click(function() {
			type = this.id.toString();
		});
		$("#displayButton").click(function() {
			type = this.id.toString();
		});
		// Merge function
		$("#mergeButton").click(function() {
			type = this.id.toString();
		});
		
		// resize image proportionally
		$('img').each(function() {
			var maxWidth = 530;
	        var maxHeight = 530;
	        
	        var ratio = 0;  // Used for aspect ratio
	        var width = $(this).width();    // Current image width
	        var height = $(this).height();  // Current image height
			
	        /* Make height/width at least the max size to begin with */
	        // Check if the current width is smaller than max
	        if(width < maxWidth){
	            ratio = maxWidth / width;   // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        // Check if current height is smaller than min
	        if(height < maxHeight){
	            ratio = maxHeight / height; // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        
	        /* Then reduce height/width to fit the max */
	        // Check if the current width is larger than the max
	        if(width > maxWidth){
	            ratio = maxWidth / width;   // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        // Check if current height is larger than max
	        if(height > maxHeight){
	            ratio = maxHeight / height; // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        
	        // Set CSS
	        $(this).css("height", height);   // Set new height
	        $(this).css("width", width);    // Scale width based on ratio
	    });
	});
	
	
	
</script>
</head>
<body>
	<div id="myHeader">
		<h1 id="banner">Class Diagrams</h1>
	</div>
	<ul class="basictab">
	    <li><a href="#tabs-1">Home</a></li>
	    <li><a href="ManageProject">Project</a></li>
	    <li><a href="ManageContext">Context</a></li>
        <li><a href="ManagePolicy">Policy</a></li>
      </ul>
</div>

<div id="mainContainer">
	
  <div id="diagramBox">
		<c:if test="${requestScope.diagramId1 != null}">
			<img src="${requestScope.firstPath}"/>
		</c:if>
		<!-- 
			<br><b>Comments : </b>
		<c:if test="${requestScope.comments != null}">
			<div id="commentBox">
				<table id="myTable">
					<c:forEach items="${requestScope.comments}" var="comment">
						<tr>
							<td><b><c:out value="${comment.userName}"></c:out> </b></td>
							<td><c:out value=" ${comment.content}"></c:out></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
		 -->
		
	</div>
    
    <div id="rightContainer">
    
		<div id="selectContextBox">
		<span>Diagram Context:</span>
		<select>
		<option value="context1">Context 1</option>
		<option value="context2">Context 2</option>
		</select>
		</div>
		
		<div id="box">
			<span id="DiagramSelectLabel">Class Diagram Format: (.ecore)</span> 
			<select onchange="displayClassDiagramFields(this)">
					<option value="ecore">ECORE</option>
					<option value="xmi">XMI</option>
			</select> 
			<form action="UploadServlet" method=POST enctype="multipart/form-data">
					<input id="file1" type="file" name="file" size="50" />
					<input id="file2" type="file" name="file2" size="50" style="display:none"/>
					<input id="file3" type="file" name="file3" size="50" style="display:none"/>
					
					<input type="hidden" value="${ProjectID }" name="ProjectID">
					<input class="submit" type="submit" onmouseover="checkError();" value="upload">
					<br><span id="errorMsg"></span>
			</form>
		</div>
	
		<div id="box2">
			<form action="DisplayDiagram" method="post">
				<input type="submit" id="downloadProjectButton" value="DownloadProject"
					name="submit" />
			</form>
		</div>
		
		<div id="list">
			<form action="DisplayDiagram" method="post"
				onsubmit="return checkFields()">
				<input type="hidden" value="${ProjectID}" name="ProjectID"/>
				<input type="submit" id="displayButton" value="Display" name="submit" />
				<input type="submit" id="downloadButton" value="Download" name="submit" />
				<input type="submit" id="compareButton" value="Go to compare" name="submit"
					<c:if test="${type=='sequence'}">disabled</c:if>
					<c:if test="${type=='class'}">disabled</c:if>/>
				<!-- Merge -->
				<input type="submit" id="mergeButton" value="Go To Merge" name="submit"
					<c:if test="${type=='sequence'}">disabled</c:if>
					<c:if test="${type=='Ecore'}">disabled</c:if>/>
				<!-- End Merge -->
				<table>
					<tr>
						<td>
						<input type="checkbox" name="checkall"
								value="10" onclick="toggleChecked(this.checked)">
						</td>
						<td><b>Image</b></td>
						<td><b>Edited</b></td>
						<td><b>Type</b></td>
					</tr>
					<c:forEach items="${requestScope.diagrams}" var="diagram">
						<tr>
							<td><input class="myCheckBox" type="checkbox" name="check"
								value="${diagram.diagramId}" id="${diagram.diagramType}"/></td>
							<td>${diagram.diagramName}</td>
							<td>${diagram.createdTime}</td>
							<td>${diagram.diagramType}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
		
		<div id="decision">
		
		<div id="decisionDisplays" style="width:80%;float:left;">
		<fieldset class="well the-fieldset" >
    		<legend class="the-legend">Decisions</legend>
			
			<table border-spacing: 8px 2px;>
				<tr>
				<td></td>
				<td><b>Decisions</b></td>
				<td><b>User</b></td>
				<td><b>Diagram</b></td>
				</tr>
					<!-- Commented out Now. Need to enable it after integration
						<c:forEach items="${requestScope.decisions}" var="decisions">
						<tr>
							<td><input class="myCheckBox" type="checkbox" name="check"
								value="${decision.decisionId}" id="${diagram.diagramId}"/></td>
							<td>${diagram.decisionName}</td>
							<td>${diagram.createdTime}</td>
							<td>${diagram.diagramName}</td>
						</tr>
					</c:forEach>
					</td>
					-->
					<tr>
						<c:forEach items="${requestScope.decisions}" var="decision">
						<tr>
							<td><input class="myCheckBox" type="checkbox" name="check"
								value=1/></td>
							<td>${decision.value.decisionName}</td>
							<td>${decision.value.userName}</td>
						</tr>
						</c:forEach>
					</tr>
				</table>
						
    	
    	</fieldset>
		
		</div>
		
		<div id="decisionMenu" style="width:15%;float:right;">
		<br>
		<br>
		<table>
			<tr>
			<button id="displayButton">Display</button> 
			</tr>
			<tr>
			<button id="editButton">Edit</button> 
			</tr>
			<tr>
			<button id="createButton">Create</button> 
			</tr>
			</table>
		</div>
			
			
		</div>
		
	<div id="createDecision">	 
	   <form action="DisplayDiagram" method="post" onsubmit="">
		 <input type="hidden" value="${ProjectID}" name="ProjectID"/>
	     <h4><strong>Create New Dicision</strong></h4>
		 <br/>

	    <label><b>Decision Name</b></label>
	     <input type="text" id="decisionName" name ="decisionName" placeholder="Decision Name" size="20" maxlength="75"/>
	     <br>
	     <br>
	     <table align="center">
      	 	<tr>
      			<td>
      				<input type="submit" id="createDecisionButton" value="CreateDecision" name="submit"/>
				</td>
			</tr>
	     </table>
	   </form>
	 </div>
	
	<div id="selectDiagram">	 
	   <form action=" " method="post" onsubmit="">
	     <h4><strong>Select UML Diagram</strong></h4>
		 <br/>
	    <label><b>Select a Diagram</b></label>
		 <select id="digramList"">
 		   <label>Diagram</label>
	    	 <select name="selectDiagram">
	 	    <option>Diagram1</option>
	  		<option>Diagram2</option>
	 	    <option>Diagram3</option>
		 </select>
	     <br>
	     <br>
	     <table align="center">
      	 	<tr>
      			<td>
      				<div class="sumbitbutton1"><button id="button2" class="pbutton2">Choose Diagram</button></div>
				</td>
			</tr>
	     </table>
	   </form>
	 </div>
	 
	     		
		
	</div>
	
	</div>
	
	
	
	
	
	<!-- Merge Form -->
	<form id=requestForm action="ClassMergeComunicator" method=POST style="display: none;" onsubmit="return checkFields()">
		<input name=request id=req value="" />
	</form>
	<!-- End Merges -->
<script type="text/javascript">
$(function() {
	$( "#Tabs1" ).tabs(); 
});
    </script>
</body>
</html>
