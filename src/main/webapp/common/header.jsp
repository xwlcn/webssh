<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="../js/menu.js"></script>
<script src="../js/global.js"></script>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
   <div class="container">
	   <div class="navbar-header">
	   	  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
          </button>
	      <a class="navbar-brand" href="<%=path%>"><s:text name="header_navbar_brand"></s:text></a>
	   </div>
	   <div class="collapse navbar-collapse">
	      <ul class="nav navbar-nav">
	         <li><a href="<%=path%>/user/machine_openShellTerminalPage" target="_blank"><s:text name="header_shell_term"></s:text></a></li>
	         <li><a href="<%=path%>/user/machine_openSftpWindowPage" target="_blank"><s:text name="header_sftp_window"></s:text></a></li>
	         <li><a href="#"><s:text name="header_help"></s:text></a></li>
	      </ul>
	      <ul class="nav navbar-nav" style="float:right;">
	      	<li class="dropdown pull-right">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	               Language <b class="caret"></b>
	            </a>
	            <ul class="dropdown-menu">
	               <li><s:a namespace="/" action="changeLanguage?request_locale=zh_CN">简体中文</s:a></li>
	               <li><s:a namespace="/" action="changeLanguage?request_locale=en_US">English</s:a></li>
	            </ul>
	         </li>
	      	<s:if test="#session.user!=null">
	         	<li class=" pull-right"><s:a namespace="/user" action="logout"><s:text name="header_logout"></s:text></s:a></li>
	         	<li class="pull-right"><s:a namespace="/user" action="user_usercenterPage" onclick="clear()"><s:text name="header_usercenter"></s:text></s:a></li>
	         	<li class="pull-right" style="padding-top:6px">
	         	<img class="img-circle" style="height:40px;width:40px;" src="<%=path%>${sessionScope.user.facePath}">
	         	</li>
	         </s:if>
	         <s:else>
		         <li class="pull-right"><s:a namespace="/user" action="user_registPage"><s:text name="header_regist"></s:text></s:a></li>
		         <li class="pull-right"><s:a namespace="/user" action="user_loginPage"><s:text name="header_login"></s:text></s:a></li>
	         </s:else>
	      </ul>
	   </div>
   </div>
</nav>
<script>
$('.collapse').collapse("toggle");
function clear(){writeCookie('lastMenu', '', 60*60*24);}
</script>