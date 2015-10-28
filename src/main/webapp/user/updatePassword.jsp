<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path %>/css/style.css">
	<script src="<%=path %>/js/jquery-2.1.4.min.js"></script>
	<script src="<%=path %>/js/global.js"></script>
	<script src="<%=path %>/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<title><s:text name="updatepassword_title"></s:text></title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="blank"></div>
		<div class="container">
			<div class="col-md-9">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title"><s:text name="updatepassword_panel_title"></s:text></h3>
					</div>
					<div class="panel-body" id="left-body" style="min-height:700px">
						<s:form id="udpForm" role="form">
							<s:token></s:token>
							<div class="form-group">
								<label for="oldPassword"><s:text name="updatepassword_user_oldpwd"></s:text></label>
								<s:textfield cssClass="form-control" type="password" id="oldPassword" name="oldPassword"></s:textfield>
							</div>
							<div class="form-group">
								<label for="newPassword"><s:text name="updatepassword_user_newpwd"></s:text></label>
								<s:textfield cssClass="form-control" type="password" id="newPassword" name="newPassword"></s:textfield>
							</div>
							<div class="form-group">
								<label for="repeatPassword"><s:text name="updatepassword_user_confirmpwd"></s:text></label>
								<s:textfield cssClass="form-control" type="password" id="repeatPassword" name="repeatPassword"></s:textfield>
							</div>
							
							<p id="msg" style="color:red"></p>
							<input class="btn btn-success" type="button" id="sub" value="<s:text name="updatepassword_btn_sub"></s:text>">
							<input class="btn btn-success" type="reset" value="<s:text name="updatepassword_btn_reset"></s:text>">
						</s:form>
					</div>
				</div>
			</div>
			<jsp:include page="centerRight.jsp"></jsp:include>
		</div>
		<div class="push"></div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script src="<%=path %>/js/pwd.js<%=new Date().getTime() %>"></script>
</html>