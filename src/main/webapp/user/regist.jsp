<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#session.user!=null"><script>window.location = "../index.jsp"</script></s:if>
<s:else>
<!DOCTYPE HTML>
<html>
  <head>

    <title><s:text name="regist_title"></s:text></title>
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
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		      <div class="alert alert-danger" id="message" style="text-align: center"></div>
			</div>
		</div>
		<div style="height: 150px"></div>
	    <div class="container register-container">
	    	<div class="col-md-8 col-md-offset-2">
		    	<div class="register-title"><s:text name="regist_container_title"></s:text></div> 
	    	</div>
	    	<div class="col-md-5 col-md-offset-2">
		    	<div class="register-body">
		    		<form id="registForm">
		    		  <div class="input-group glyphicon">
				         <span class="input-group-addon glyphicon-user"></span>
				         <input type="text" class="form-control" id="nick" name="nick" placeholder="Nick">
				      </div>
				      <div class="message" id="nickMessage"><br></div>
				      
				      <div class="input-group glyphicon">
				         <span class="input-group-addon glyphicon-envelope"></span>
				         <input type="text" class="form-control" id="email" name="email" placeholder="Email">
				      </div>
				      <div class="message" id="emailMessage"><br></div>
				
				      <div class="input-group glyphicon">
				         <span class="input-group-addon glyphicon-lock"></span>
				         <input type="password" class="form-control" id="password" name="password" placeholder="password">
				      </div>
					  <div class="message" id="passwordMessage"><br></div>
	
					  <div class="input-group glyphicon col-md-7 pull-left">
				        <span class="input-group-addon glyphicon-barcode"></span>
				        <input type="text" class="form-control" id="verify" name="verify" onkeydown="keyDown()">
				      </div>
				      
				      <div class="input-group col-md-4 pull-right" style="width:130px">
				        <img class="verifyCode" id="verifyCode" src="../getImage"/>
				      </div>
					  <div class="clearfix"><br><br><br></div>
					  
					  <div class="submit">
					  	<input class="btn btn-success col-md-12" style="width:100%" id="submit" type="button" value="<s:text name="regist_btn_sub"></s:text>"/>
					  </div>
					  
					  <div class="login-footer">
					  	<span>
					  		<a href="#"><s:text name="regist_link_forgetpwd"></s:text></a>
					  		<s:text name="regist_have"></s:text><a href="goToLoginUI"><s:text name="regist_link_login"></s:text></a>
					  	</span>
					  </div>
				   </form>
				</div>
			</div>
			<div class="col-md-3 register-right">
				<img src="../images/scan.png" class="img-rounded col-md-8 col-md-offset-1">
				<div style="clear:both;padding-top:20px;color: #f63"><s:text name="regist_scan"></s:text></div>
			</div>
	    </div>
	    <div class="push"></div>
    </div>
    <jsp:include page="../common/footer.jsp"></jsp:include>
  </body>
  <script type="text/javascript" src="../js/regist.js?t=<%=new Date().getTime() %>"></script>
  <script type="text/javascript">
  	$("#verifyCode").click(function(){
  		$(this).attr('src','../getImage?t='+new Date());
  	});
  </script>
</html>
</s:else>
