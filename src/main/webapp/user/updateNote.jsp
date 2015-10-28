<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="updateForm" role="form">
	<input type="hidden" name="id" value="${model.id }">
	<div class="modal-header success">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h4 class="modal-title" id="machineModalLabel"><s:text name="updatenote_modal_header"></s:text></h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="title"><s:text name="updatenote_note_title"></s:text></label> 
			<input type="text" class="form-control" id="title" name="title" value="${model.title }">
		</div>
		<div class="form-group">
			<label for="content"><s:text name="update_note_content"></s:text></label> 
			<textarea class="form-control" id="content" name="content" rows="10">${model.content }</textarea>
		</div>
		<p id="msg" class="text-danger"></p>

	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">
			<s:text name="updatenote_btn_close"></s:text>
		</button>
		<button id="btnSub" type="button" class="btn btn-primary" data-dismiss="modal">
			<s:text name="updatenote_btn_sub"></s:text>
		</button>
	</div>
</form>
<script src="../js/note.js"></script>
