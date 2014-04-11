<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/display.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<html style="background-color: #F3F3F3" xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="stylesheet" type="text/css" href="css/display.css"/>
	<link rel="stylesheet" type="text/css" href="style.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>


<style type="text/css">

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
    
	<title>Use Case Results</title>
	
	</head>
	
    <body style="background-color: #F3F3F3">
	<div id="myHeader">
	    <h1 id="banner">Use Case Results</h1>
	</div>
    
    <div id = "basictab"> 
		<ul class="basictab">
	    	<li><a href="#tabs-1">Home</a></li>
	    	<li><a href="ManageProject">Project</a></li>
	    	<li><a href="ManageContext">Context</a></li>
        	<li><a href="ManagePolicy">Policy</a></li>
      	</ul>
	</div>	

	<div id="myContainer">
		<div  class="scrollreport">

			<pre>
				<c:forEach items="${compareResult}" var="result">
					${result}<br/>
				</c:forEach>
			</pre>

		</div>
	    
	</div>
    </body>
</html>