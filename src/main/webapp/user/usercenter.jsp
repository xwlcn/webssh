<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.user==null">
	<script>window.location = "../index.jsp"</script>
</s:if>
<s:else>
<!DOCTYPE HTML>
<html>
  <head>
    <title><s:text name="usercenter_title"></s:text></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/style.css">
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
  </head>
  
  <body>
    <div class="wrapper">
  		<jsp:include page="../common/header.jsp"></jsp:include>
  		<div class="blank"></div>
  		<div class="container">
			<jsp:include page="welcome.jsp"></jsp:include>
	  		<jsp:include page="centerRight.jsp"></jsp:include>
  		</div>
	    <div class="push"></div>
	</div>
    <jsp:include page="../common/footer.jsp"></jsp:include>
  </body>
</html>
</s:else>