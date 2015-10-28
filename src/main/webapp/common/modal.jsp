<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- action return message model begin -->
<div class="modal fade" id="msgModal" tabindex="-1" role="dialog" 
   aria-labelledby="msgModalLabel" data-backdrop="static">
    <div class="modal-dialog">
        <div id="message" style="text-align: center"></div>
	</div>
</div>
<!-- action return message model end -->


<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
	aria-labelledby="updateModalLabel" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
		</div>
	</div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
	aria-labelledby="deleteModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="deleteModalLabel"><s:text name="modal_deletemodal_title"></s:text></h4>
			</div>
			<div class="modal-body"><s:text name="modal_deletemodal_body"></s:text></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="deleteSel();">
					<s:text name="modal_deletemodal_btn_sub"></s:text>
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel()">
					<s:text name="modal_deletemodal_btn_close"></s:text>
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="removeModal" tabindex="-1" role="dialog"
	aria-labelledby="removeModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="removeModalLabel"><s:text name="modal_deletemodal_btn_sub"></s:text></h4>
			</div>
			<div class="modal-body"><s:text name="modal_deletemodal_body"></s:text></div>
			<div class="modal-footer">
				<button id="subRemove" type="button" class="btn btn-primary" data-dismiss="modal"><s:text name="modal_deletemodal_btn_sub"></s:text></button>
				<button type="button" class="btn btn-default" data-dismiss="modal"><s:text name="modal_deletemodal_btn_close"></s:text></button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="selectModal" tabindex="-1" role="dialog"
	aria-labelledby="selectModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Select</h4>
			</div>
			<div class="modal-body text-center">
				<a id="shell" type="button" class="btn btn-primary" >Open Shell</a>
				<a id="sftp" type="button" class="btn btn-primary" >Open Sftp</a>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- attributes model begin -->
<div class="modal fade" id="attrModal" tabindex="-1" role="dialog"
aria-labelledby="attrModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="attrModalLabel"></h4>
			</div>
			<div class="modal-body text-center">
				<table class="table" style="margin-bottom: 0">
					<thead>
						<tr>
							<th class="text-center">Permissions</th>
							<th class="text-center">Read</th>
							<th class="text-center">Write</th>
							<th class="text-center">Execute</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Owner</td>
							<td><input type="checkbox" id="usrR" value="400"/></td>
							<td><input type="checkbox" id="usrW" value="200"/></td>
							<td><input type="checkbox" id="usrE" value="100"/></td>
						</tr>
						<tr>
							<td>Group</td>
							<td><input type="checkbox" id="grpR" value="40"/></td>
							<td><input type="checkbox" id="grpW" value="20"/></td>
							<td><input type="checkbox" id="grpE" value="10"/></td>
						</tr>
						<tr>
							<td>Other</td>
							<td><input type="checkbox" id="othR" value="4"/></td>
							<td><input type="checkbox" id="othW" value="2"/></td>
							<td><input type="checkbox" id="othE" value="1"/></td>
						</tr>
						<tr>
							<td>Permission</td>
							<td colspan="3">
								<input class="form-control input-sm" type="text" id="permissions" name="permissions"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button id="subAttr" type="button" class="btn btn-primary" data-dismiss="modal" onclick="">Submit</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!-- attributes model end -->