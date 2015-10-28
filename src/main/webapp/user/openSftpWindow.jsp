<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script src="<%=path %>/js/ajaxfileupload.js"></script>
	<script src="<%=path %>/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<title><s:text name="opensftpwindow_title"></s:text></title>
</head>
<body>
	<input type="hidden" id="mid" value="${model.id }"/> 
	<div class="wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="blank"></div>
		<div class="container">
			<div class="col-md-9">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<label class="panel-title pull-left" style="margin-top:5px" id="catalog"></label>
						<button class="btn btn-sm btn-success col-md-2 col-sm-3 pull-right" id="subCreate">Create fodder</button>
						<div class="col-md-2 pull-right">
							<input class="form-control input-sm" type="text" id="fName"/>
						</div>
						<label class="control-label pull-right" for="fName" style="margin-top:5px">Fodder name</label>
						<span class="clearfix"></span>
					</div>
					<div class="panel-body" style="height:500px;padding:0;overflow:scroll">
						<table class="table table-striped  table-responsive table-hover">
							<thead>
								<tr>
									<th>Remote Name</th>
									<th>Size</th>
									<th>Modified</th>
									<th>Attributes</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="file-list">
							</tbody>
						</table>
					</div>
				</div>
				<div class="progress progress-striped active" style="margin-bottom:10px">
				   <div class="progress-bar progress-bar-info" role="progressbar" 
				      aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" 
				      style="width: 0%;">
				      <span id="percent">00.00%</span>
				   </div>
				</div>
				<div class="form-group">
					<label for="file"><s:text name="opensftpwindow_label_upload"></s:text></label>
					<s:file name="file" id="file"></s:file>
					<span id="state" style="color:red">current percent:00.00%</span>
				</div>
				<button class="btn btn-sm btn-success col-md-1 col-sm-3" id="subFile"><s:text name="opensftpwindow_btn_upload"></s:text></button>
			</div>
			<jsp:include page="centerRight.jsp"></jsp:include>
		</div>
		<div class="push"></div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/modal.jsp"></jsp:include>
</body>
<script type="text/javascript" src="<%=path %>/js/sftp.js?t=<%=new Date().getTime() %>"></script>
</html>
