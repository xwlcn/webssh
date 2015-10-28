<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form action="updateMachine" method="post" id="updateForm" role="form">
	<input type="hidden" name="id" value="${model.id }">
	<div class="modal-header success">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="machineModalLabel"><s:text name="updatemachine_modal_header"></s:text></h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="name"><s:text name="updatemachine_machine_name"></s:text></label> <input type="text"
				class="form-control" id="name" name="name" value="${model.name }">
		</div>
		<div class="form-group">
			<label for="hostname"><s:text name="updatemachine_machine_hostname"></s:text></label> <input type="text"
				class="form-control" id="hostname" name="hostname"
				value="${model.hostname }">
		</div>
		<div class="form-group">
			<label for="username"><s:text name="updatemachine_machine_username"></s:text></label> <input type="text"
				class="form-control" id="username" name="username"
				value="${model.username }">
		</div>
		<div class="form-group">
			<label for="password"><s:text name="updatemachine_machine_password"></s:text></label> <input type="password"
				class="form-control" id="password" name="password"
				value="${model.password }">
		</div>
		<div class="form-group">
			<label for="port"><s:text name="updatemachine_machine_port"></s:text></label> <input type="text"
				class="form-control" id="port" name="port" value="${model.port }">
		</div>
		<p id="msg" class="text-danger"></p>

	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="updatemachine_btn_close"></s:text>
		</button>
		<button id="btnSub" type="button" class="btn btn-primary" data-dismiss="modal">
			<s:text name="updatemachine_btn_sub"></s:text></button>
	</div>
</form>
<script src="../js/machine.js"></script>