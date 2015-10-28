<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title><s:text name="home_title"></s:text></title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
  </head>
  
  <body>
  	
  	<div class="wrapper">
  		<jsp:include page="common/header.jsp"></jsp:include>
  		<jsp:include page="common/middle.jsp"></jsp:include>
 		<jsp:include page="common/middle2.jsp"></jsp:include>
	    <div class="push"></div>
	</div>
    <jsp:include page="common/footer.jsp"></jsp:include>
  </body>
</html>
