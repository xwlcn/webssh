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
	<script src="<%=path %>/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<title><s:text name="viewnote_title"></s:text></title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="blank"></div>
		<div class="container">
			<div class="col-md-9">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title clearfix">
							<span class="pull-left"><s:text name="viewnote_panel_title"></s:text></span>
							<a class="pull-right" style="font-size:14px" href="javascript:history.go(-1)"><s:text name="viewnote_link_return"></s:text></a>
						</h3>
					</div>
					<div class="panel-body" style="min-height:700px">
						<h2 class="text-center">${model.title }</h2>
						<p class="text-center" style="font-size:11px;color:#8e8e8e">
							<span>
								<s:text name="viewnote_text_writetime"></s:text>
								<s:date format="yyyy-MM-dd hh:mm:ss" name="model.writeTime"/>
							</span>
							<span class="col-md-offset-2"><s:text name="viewnote_text_state"></s:text><span class='${model.finished ? "text-success" : "text-danger" }'>${model.finished ? "已完成" : "未完成" } </span></span>
						</p>
						<div style="text-indent:35px;font-size:14px">
							${model.content }
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="centerRight.jsp"></jsp:include>
		</div>
		<div class="push"></div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>