<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id="footer" class="footer" style="background: #111">

    	<div class="container">
	        <p class="text-muted credit" style="margin-top:20px">
	            <span style="color: #0f0;font-size: 21px;"><s:text name="footer_title"></s:text></span>
	        </p>
	        <p class="text-muted credit">
	        	<s:text name="footer_intro"></s:text>
	        </p>
	        <p class="text-muted credit">
	        	<s:text name="footer_link"></s:text>
	        </p>
	        <p class="text-muted credit" style="margin-bottom: 20px">
	        	<a class="label label-primary" href="http://www.hhtc.edu.cn" target="_blank">怀化学院</a>
	        	<a class="label label-success" href="http://jsj.hhtc.edu.cn" target="_blank">怀化学院计算机系</a>
	        	<a class="label label-warning" href="http://www.baidu.com" target="_blank">百度一下</a>
	        	<a class="label label-info" href="http://www.qq.com" target="_blank">腾讯QQ</a>
	        </p>
        </div>

</div>