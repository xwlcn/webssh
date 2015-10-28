<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../js/time.js"></script>
<div class="col-md-9">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 class="panel-title"><s:text name="welcome_panel_title"></s:text></h3>
		</div>
		<div class="panel-body" id="left-body" style="min-height:700px">
			<div id="top" style="overflow: hidden">
				<div class="lastinfo">
					<p><s:text name="welcome_text_lastloginip"></s:text></p>
					<p style="color:red">${session.user.lastLoginIP}</p>
					<p><s:text name="welcome_text_lastlogintime"></s:text></p>
					<p style="color:red"><s:date format="yyyy-MM-dd hh:mm:ss" name="#session.user.lastLoginTime"/></p>
				</div>
				<div class="col-md-8" style="display:block;float:right;">
					<br/>
					<p style="font-size:40px"><s:text name="welcome_text_welcome"></s:text></p>
					<br/>
					<p style="font-size:20px" id="time"></p>
				</div>
			</div>
			<div class="hsplit">
				<span><s:text name="welcome_text_note"></s:text></span><s:a cssClass="pull-right more" namespace="/user" action="notelist">More</s:a>
			</div>
			<div class="remember">
				<s:iterator value="notes" var="note">
					<div class="note">
					<p class="note-title"><s:property value="#note.title"/></p>
					<p style="border:none;border-top:1px silver solid;"></p>
					<p id="date"><s:date format="yyyy-MM-dd" name="#note.writeTime"/></p>
					<p class="note-content">
						<s:property value="#note.content"/>
						<br>
						<s:a class="note-read" namespace="/user" action="notelist">Read</s:a>
					</p>
				</div>
				</s:iterator>
				<div class="note" style="cursor:pointer;background: #999999;" onclick="init();">
					<p>　</p>
					<p id="book-title">memorandum book</p>
					<p id="book-opr">Open it!</p>
					<div id="book-bottom"><span class="badge">&nbsp;&nbsp;${noteCount }&nbsp;&nbsp;</span></div>
					<p>　</p>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/waterfall.js?t=<%=new Date().getTime() %>"></script>