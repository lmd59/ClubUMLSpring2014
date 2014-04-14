<%--
    Document   : useCaseApplet.jsp
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
		<h1 id="banner">Use Case Diagrams</h1>
	</div>
	<ul class="basictab">
	    <li><a href="LoginServlet">Home</a></li>
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
	</div>
    
    <div id="rightContainer">
		
		<div id="box">
			<span id="DiagramSelectLabel">Use Case Diagram Format: (.xml)</span> 
			<select onchange="displayClassDiagramFields(this)">
					<option value="xml">XML</option>
			</select> 
			<form action="UploadServlet" method=POST enctype="multipart/form-data">
					<input id="file1" type="file" name="file" size="50" />
					
					<input type= "hidden" value= "useCase" name="uploadType">
					<input class="submit" type="submit" onmouseover="checkError();" value="upload">
					<br><span id="errorMsg"></span>
			</form>
		</div>
	
		<div id="list">
			<form action="UseCaseApplet" method="post"
				onsubmit="return checkFields()">
				<input type="hidden" value="${ProjectID}" name="ProjectID"/>
				<input type="hidden" value="" name="UseCaseDiagramId"/>
				
				<select id="selectedRecord" name="selectedRecord">
		            <c:forEach var="diag" items="${requestScope.diagrams}">
		                <option value="${diag.useCaseDiagramId}">${diag.diagramName}</option>
		            </c:forEach>
		        </select>
				
				<input type="submit" id="compareRuleButton" value="Compare to Rule" name="submit" />
					
			</form>
		</div>
		
		<div id="box2">
		<!-- Go to create rules page  -->
			<form action="CreateRules" method="post">
				<input type="submit" id="createRulesButton" value="Create Use Case Rules"
					name="submit" />
			</form>
		</div>	
			
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
	
<script type="text/javascript">
$(function() {
	$( "#Tabs1" ).tabs(); 
});
    </script>
</body>
</html>
