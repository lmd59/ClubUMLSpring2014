<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<!-- BOOTSTRAP -->
<link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css"
	rel="stylesheet">
<link
	href="http://twitter.github.io/bootstrap/assets/css/bootstrap-responsive.css"
	rel="stylesheet">
<title>Insert title here</title>

<script type="text/javascript">
 $("#AddContext").hide();
 
 $("#addContextBtn").click(function(){
	 $("#AddContext").dialog(); 
	 
 });
 $("#UpdateContext").hide();
 $("#updateContextBtn").click(function(){
	 $("#UpdateContext").dialog(); 
	 
 });
	
</script>

<style type="text/css">
#Description{

height:150px;
}
.contextTab{

}
.contul{
	list-style: none;
	font-weight: bold;
}
.contextMenu{
	float: left;
	padding-right: 30px; 
}
.table-hover{

width:80%;
	
}
#menu li{
display: inline;
}
<!--
/* Tab-Menu CSS code */
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

/*end of tab-menu css code*/
-->
</style>
</head>
<body>

<div id="AddContext">
	 <h3><strong>Add Context</strong></h3>
     <br/>
     <form action="CreateContext" method="get">
     
      <div class="form-group">

    <label class="col-lg-2 control-label" id="ContextName" >Context Name</label>

    <div class="col-lg-10">

      <input type="text" class="form-control" id="ProjectName" name ="ProjectName" placeholder="ProjectName"/>

    </div>

  </div>
  
    <div class="form-group">

    <label class="col-lg-2 control-label">Description</label>

    <div class="col-lg-10">

    

      <input type="text" class="form-control" id="Description" name="Description" placeholder="Description"/>

    </div>

  </div>
  
  
  <div class="form-group">

    <label class="col-lg-2 control-label">Select Project</label>

  <select>

  <c:forEach items="${activeprojects}" var="project">

  <option>${project.projectName }</option>

  </c:forEach>

  </select>

  </div>
  
  <input class="btn btn-info"  type="submit" value="Create" />
     </form>
	
</div>


<div id="UpdateContext">
	 <h3><strong>Update Context</strong></h3>
     <br/>
     <form action="CreateContext" method="get">
     
      <div class="form-group">

    <label class="col-lg-2 control-label" id="ContextName" >Context Name</label>

    <div class="col-lg-10">

      <input type="text" class="form-control" id="ProjectName" name ="ProjectName" placeholder="ProjectName"/>

    </div>

  </div>
  
    <div class="form-group">

    <label class="col-lg-2 control-label">Description</label>

    <div class="col-lg-10">

    

      <input type="text" class="form-control" id="Description" name="Description" placeholder="Description"/>

    </div>

  </div>
  
  
  <div class="form-group">

    <label class="col-lg-2 control-label">Select Project</label>

  <select>

  <c:forEach items="${activeprojects}" var="project">

  <option>${project.projectName }</option>

  </c:forEach>

  </select>

  </div>
  
  <input class="btn btn-info"  type="submit" value="Update" />
     </form>
	
</div>

<div id="menu">
		<ul class="contul">
			<li><button class="btn btn-link"  id="addContextBtn">Add Context</button> </li> 
			<li><button class="btn btn-link" id="updateContextBtn">Delete Selected Context</button></li>
		</ul>
</div>
	
<div class="contextTab">
<ul class="basictab">
	    <li><a href="LoginServlet">Home</a></li>
	    <li><a href="ManageProject">Project</a></li>
	    <li><a href="ManageContext">Context</a></li>
        <li><a href="ManagePolicy">Policy</a></li>
      </ul>
<h2>Current Contexts</h2>
	<table class="table table-hover">
		<tr>
			<th>Select</th>
			<th>Context Name</th>
			<th>Description</th>
			<th>Projects</th>
			
			
		</tr>
		<c:forEach items="${activecontexts}" var="contexts">
			<tr>
			<td> <input type="checkbox"> </td>
			<td>${contexts.diagramContextName }</td>
			<td>${contexts.diagramContextDescription }</td>
			<td>Project</td>
		</tr>
		</c:forEach>
		
	</table>
	
	
	<h2>Deactive Contexts</h2>
	<table class="table table-hover">
		<tr>
			<th>Select</th>
			<th>Context Name</th>
			<th>Description</th>
			<th>Projects</th>
			
			
		</tr>
		<c:forEach items="${activecontexts}" var="contexts">
			<tr>
			<td> <input type="checkbox"> </td>
			<td>${contexts.diagramContextName }</td>
			<td>${contexts.diagramContextDescription }</td>
			<td>Project</td>
		</tr>
		</c:forEach>
		
	</table>
</div>


</body>
</html>