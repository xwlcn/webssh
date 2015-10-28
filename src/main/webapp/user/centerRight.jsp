<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="col-md-3" style="padding-left:0">
<div class="panel panel-success mypanel">
	<div class="panel-heading mypanel-heading">
    		<h3 class="panel-title"><s:text name="centerright_user_title"></s:text></h3>
	</div>
	<div class="panel-body">
  			<img style="height:150px;width:150px;border:1px solid silver;" src="<%=path%>${sessionScope.user.facePath}" class="img-circle center-block">
		<br>
		<div style="text-align:center">
			<s:text name="centerright_user_username"></s:text> ${session.user.nick}
		</div>
	</div>
</div>
</div>
<div class="panel-group col-md-3" id="accordion" style="padding-left:0">
  <div class="panel panel-default clear-top mypanel">
    <div class="panel-heading mypanel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseInfo">
         	 <s:text name="centerright_manage_info"></s:text>
        </a>
      </h4>
    </div>
    <div id="collapseInfo" class="panel-collapse collapse">
      <div class="panel-body" style="padding:0">
        <s:a cssClass="btn btn-primary btn-block" role="button" style="margin-top:0;border-radius:0" 
			namespace="/user" action="user_uploadFacePage"><s:text name="centerright_manage_info_uploadface"></s:text></s:a>
		<s:a cssClass="btn btn-primary btn-block" style="border-radius:0;margin:0" role="button"
			namespace="/user" action="user_updatePasswordPage"><s:text name="centerright_manage_info_updatepwd"></s:text></s:a>
      </div>
    </div>
  </div>
  
  <div class="panel panel-default clear-top mypanel">
    <div class="panel-heading mypanel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseMachine">
         	 <s:text name="centerright_manage_machine"></s:text>
        </a>
      </h4>
    </div>
    <div id="collapseMachine" class="panel-collapse collapse">
      <div class="panel-body" style="padding:0">
        <s:a cssClass="btn btn-success btn-block" style="border-radius:0" role="button" 
				namespace="/user" action="machine_addMachinePage"><s:text name="centerright_manage_machine_add"></s:text></s:a>
		<s:a cssClass="btn btn-success btn-block" style="border-radius:0;margin:0" role="button" 
				namespace="/user" action="machinelist"><s:text name="centerright_manage_machine_list"></s:text></s:a>
      </div>
    </div>
  </div>
  
  <div class="panel panel-default clear-top mypanel">
    <div class="panel-heading mypanel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#collapseNote">
         	<s:text name="centerright_manage_note"></s:text>
        </a>
      </h4>
    </div>
    <div id="collapseNote" class="panel-collapse collapse">
      <div class="panel-body" style="padding:0">
      	<s:a cssClass="btn btn-warning btn-block" style="border-radius:0" role="button" 
			namespace="/user" action="note_addNotePage"><s:text name="centerright_manage_note_add"></s:text></s:a>
		<s:a cssClass="btn btn-warning btn-block" style="border-radius:0;margin:0" role="button" 
			namespace="/user" action="notelist"><s:text name="centerright_manage_note_list"></s:text></s:a>
      </div>
    </div>
  </div>
  <s:a cssClass="btn btn-danger btn-block" style="border-radius:0" role="button" 
		namespace="/user" action="logout"><s:text name="centerright_logout"></s:text></s:a>
</div>