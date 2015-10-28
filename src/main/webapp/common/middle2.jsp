<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">
	<div class="col-md-4 box bg-danger">
		<h3><b><s:text name="middle2_intro1_title"></s:text></b></h3>
		<p></p>
		<ul>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro1_item1"></s:text>
			</li>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro1_item2"></s:text>
			</li>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro1_item3"></s:text>
			</li>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro1_item4"></s:text>
			</li>
		</ul>
	</div>
	
	<div class="col-md-4 box pull-right bg-info">
		<h3><b><s:text name="middle_intro2_title"></s:text></b></h3>
		<p></p>
		<ul>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro2_item1"></s:text>
			</li>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro2_item2"></s:text>
			</li>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro2_item3"></s:text>
			</li>
			<li>
				<span class="icon-if icon-if-checkmark-green">　</span>
				<s:text name="middle2_intro2_item4"></s:text>
			</li>
		</ul>
	</div>
	
	<div class="col-md-4 box pull-right bg-warning" style="padding:75px 40px">
		<a class="btn btn-primary btn-lg btn-block" href="user/user_registPage">&gt;&gt;&gt;<s:text name="middle2_regist"></s:text>&lt;&lt;&lt;</a>
	</div>
	
	<span class="clearfix"></span>
	<hr>
	<div class="col-sm-6 clear-left">
		<div class="window">
			<div class="window-toolbar">
				<i class="red"></i>
				<i class="yellow"></i>
				<i class="green"></i>
				<span>Shell Terminal</span>
				</div><div class="window-content">
				<textarea class="console-area" style="outline: none" readonly="readonly">
Last login: Sat Jul 11 20:22:13 2015 from xxx.xxx.xxx.xxx                                  
[root@SAC ~]# ls                                                                          
cron.sh  Documents  Music     Public    Templates                                         
Desktop  Download   Pictures  shell_script  Videos                                        
[root@SAC ~]#
				</textarea>
			</div>
		</div>
	</div>	
	<div class="col-sm-6 pull-right clear-right">
		<div class="window">
			<div class="window-toolbar">
				<i class="red"></i>
				<i class="yellow"></i>
				<i class="green"></i>
				<span>Sftp Window</span>
			</div>
			<div class="window-content">
				<ul class="list-group file-terminal-list">
				   <li class="list-group-item glyphicon glyphicon-folder-open">　...</li>
				   <li class="list-group-item glyphicon glyphicon-folder-open">　root</li>
				   <li class="list-group-item glyphicon glyphicon-file">
				   	bash
				   	<a class="glyphicon glyphicon-trash pull-right" href="javascript:void(0)" title="delete" style="color:red"></a>
				   	<a class="glyphicon glyphicon-download-alt pull-right" href="javascript:void(0)" title="download" style="margin-right:10px;color:#FF9933"></a>
				   	<a class="glyphicon glyphicon-edit pull-right" href="javascript:void(0)" title="edit" style="margin-right:10px;color:blue"></a>
				   	</li>
				   <li class="list-group-item glyphicon glyphicon-file">
				   	a.txt
				   		<a class="glyphicon glyphicon-trash pull-right" href="javascript:void(0)" title="delete" style="color:red"></a>
				   		<a class="glyphicon glyphicon-download-alt pull-right" href="javascript:void(0)" title="download" style="margin-right:10px;color:#FF9933"></a>
				   		<a class="glyphicon glyphicon-edit pull-right" href="javascript:void(0)" title="edit" style="margin-right:10px;color:blue"></a>
				   	</li>
				   <li class="list-group-item glyphicon glyphicon-file">
				   	webssh
				   		<a class="glyphicon glyphicon-trash pull-right" href="javascript:void(0)" title="delete" style="color:red"></a>
				   		<a class="glyphicon glyphicon-download-alt pull-right" href="javascript:void(0)" title="download" style="margin-right:10px;color:#FF9933"></a>
				   		<a class="glyphicon glyphicon-edit pull-right" href="javascript:void(0)" title="edit" style="margin-right:10px;color:blue"></a>
				   	</li>
				</ul>
			</div>
		</div>
	</div>
	<span class="clearfix"></span>
	<hr>

</div>
