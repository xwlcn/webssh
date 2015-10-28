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
<title><s:text name="addnote_title"></s:text></title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="blank"></div>
		<div class="container">
			<div class="col-md-9">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title"><s:text name="addnote_panel_title"></s:text></h3>
					</div>
					<div class="panel-body" style="min-height:700px">
						<form id="addForm">
							<div class="form-group">
								<label for="title"><s:text name="addnote_note_title"></s:text></label>
								<input type="text" class="form-control" id="title" name="title">
							</div>
							<div class="form-group">
								<label for="content"><s:text name="addnote_note_content"></s:text></label>
								<textarea class="form-control" id="content" name="content" rows="10"></textarea>
							</div>

							<p id="msg" class="text-danger"></p>
							<input type="button" class="btn btn-success" id="addnote" value="<s:text name="addnote_btn_add"></s:text>">
							<input type="reset" class="btn btn-success" value="<s:text name="addnote_btn_reset"></s:text>">
						</form>
					</div>
				</div>
			</div>
			<jsp:include page="centerRight.jsp"></jsp:include>
		</div>
		<div class="push"></div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
<script>
	$("#addnote").click(function(){
		var data = $("#addForm").serialize();
		$.post("addNote", data, function(data){
			data = jQuery.parseJSON(data);
			if(data.code == noteaction_addnote_success){
				$("#addForm")[0].reset();
			}
			$('#msg').html(data.msg);
			setTimeout(function(){$('#msg').html('')},3000);
		});
	});
</script>
</html>