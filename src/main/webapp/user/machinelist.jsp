<%@page import="online.webssh.beans.PageBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path %>/css/bootstrap-select.css">
<link rel="stylesheet" href="<%=path %>/css/style.css">
<script src="<%=path %>/js/jquery-2.1.4.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-select.js"></script>
<script src="<%=path %>/js/machine.js"></script>
<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<title><s:text name="machinelist_title"></s:text></title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="blank"></div>
		<div class="container">
			<div class="col-md-9">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title"><s:text name="machinelist_panel_title"></s:text></h3>
					</div>
					<div class="panel-body" style="min-height: 700px">
						<form class="form-inline" action="machinelist" method="post"
							style="margin-bottom: 10px">
							<div class="form-group">
								<select id="keytype" name="keytype"
									class="form-control selectpicker">
									<option value="0" <s:if test="keytype==0">selected</s:if>><s:text name="machinelist_search_name"></s:text></option>
									<option value="1" <s:if test="keytype==1">selected</s:if>><s:text name="machinelist_search_hostname"></s:text>
									</option>
									<option value="2" <s:if test="keytype==2">selected</s:if>><s:text name="machinelist_search_port"></s:text></option>
								</select>
							</div>
							<div class="form-group">
								<s:if test="keytype==0 || keytype==null">
									<s:textfield class="form-control" type="text" id="keyword"
										name="nameKeyWord"></s:textfield>
								</s:if>
								<s:elseif test="keytype=1">
									<s:textfield class="form-control" type="text" id="keyword"
										name="hostKeyWord"></s:textfield>
								</s:elseif>
								<s:else>
									<s:textfield class="form-control" type="text" id="keyword"
										name="portKeyWord"></s:textfield>
								</s:else>
							</div>
							<input type="submit" class="btn btn-warning" value="<s:text name="machinelist_search_btn"></s:text>" />
						</form>
						<form name="machineForm" action="deleteMachines" method="post">
							<input type="hidden" name="pageNum" value="${page.pageNum }">
							<input type="hidden" name="keytype" value="${keytype }">
							<input type="hidden" name="nameKeyWord" value="${nameKeyWord }">
							<input type="hidden" name="hostKeyWord" value="${hostKeyWord }">
							<input type="hidden" name="portKeyWord" value="${portKeyWord }">
							<table class="table table-striped  table-responsive machinelist">
								<thead>
									<tr style="background: #aaa; color: white">
										<th width="45px"><s:text name="machinelist_table_th1"></s:text></th>
										<th><s:text name="machinelist_table_th2"></s:text></th>
										<th style="width: 30%"><s:text name="machinelist_table_th3"></s:text></th>
										<th><s:text name="machinelist_table_th4"></s:text></th>
										<th><s:text name="machinelist_table_th5"></s:text></th>
										<th class="opr"><s:text name="machinelist_table_th6"></s:text></th>
									</tr>
								</thead>
								<tbody>
									<s:if test="page.list.size==0">
										<tr>
											<td colspan="6"><s:text name="machinelist_norecord"></s:text></td>
										</tr>
									</s:if>
									<s:iterator value="page.list" var="machine" status="status">
										<tr>
											<td><input type="checkbox" name="ids"
												value="<s:property value="#machine.id"/>" /></td>
											<td><s:property
													value="#status.count + (page.pageNum-1)*page.pageSize" /></td>
											<td><s:property value="#machine.name" /></td>
											<td><s:property value="#machine.hostname" /></td>
											<td><s:property value="#machine.port" /></td>
											<td class="opr">
												<a class='btn btn-warning btn-sm ${session.currentMachine.id == machine.id ? "disabled" : ""}'
													data-toggle="modal" data-target="#selectModal" onclick="openSelect('<s:property value="#machine.id"/>')">
														<s:if test="#session.currentMachine.id == #machine.id">
															<s:text name="machinelist_btn_opened"></s:text>
														</s:if>
														<s:else>
															<s:text name="machinelist_btn_open"></s:text>
														</s:else>
												</a>
												<a class="btn btn-success btn-sm" data-toggle="modal" data-target="#updateModal"
													href='machine_updateMachinePage?id=<s:property value="#machine.id"/>'>
													<s:text name="machinelist_btn_update"></s:text>
												</a>
												<a class="btn btn-danger btn-sm" name="del" href="javascript:void(0)" data-toggle="modal" data-target="#deleteModal">
													<s:text name="machinelist_btn_delete"></s:text>
												</a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
								<tfoot>
									<s:if test="page.list.size>0">
										<tr>
											<td colspan="6">
												<div class="pull-left" style="margin-top: 8px">
													<a href="javascript:selAll()"><s:text name="machinelist_link_sellall"></s:text></a> <a
														href="javascript:deSelAll()"><s:text name="machinelist_link_desell"></s:text></a> <a
														href="javascript:void(0)" data-toggle="modal"
														data-target="#deleteModal" style="outline: none"><s:text name="machinelist_link_delsell"></s:text></a>
													<s:text name="page_total"></s:text>${page.totalPage }<s:text name="page_split1"></s:text>${page.pageNum }<s:text name="page_split2"></s:text>${page.totalSize }<s:text name="page_split3"></s:text>
												</div>

												<ul class="pagination pull-right">
													<li><a href="machinelist?keytype=${keytype}&nameKeyWord=${nameKeyWord}&hostKeyWord=${hostKeyWord}&portKeyWord=${portKeyWord}"><s:text name="page_first"></s:text></a></li>
													<s:if test="page.pageNum<=page.colNum">
														<li class='disabled'><a href="#">&laquo;</a></li>
													</s:if>
													<s:else>
														<li><a
															href="machinelist?pageNum=<s:number name='page.start-1' />&keytype=${keytype}&nameKeyWord=${nameKeyWord}&hostKeyWord=${hostKeyWord}&portKeyWord=${portKeyWord}">&laquo;</a>
														</li>
													</s:else>
													<c:forEach begin="${page.start }" end="${page.end }"
														step="1" var="p">
														<c:if test="${p>page.totalPage}">
															<li class='disabled'><a href="#">${p }</a></li>
														</c:if>
														<c:if test="${p<=page.totalPage}">
															<li ${p==page.pageNum?"class='active'":"" }><a
																href="machinelist?pageNum=${p }&keytype=${keytype}&nameKeyWord=${nameKeyWord}&hostKeyWord=${hostKeyWord}&portKeyWord=${portKeyWord}">${p }</a></li>
														</c:if>
													</c:forEach>
													<s:if test="page.end+1>page.totalPage">
														<li class='disabled'><a href="#">&raquo;</a></li>
													</s:if>
													<s:else>
														<li><a
															href="machinelist?pageNum=<s:number name='page.end+1' />&keytype=${keytype}&nameKeyWord=${nameKeyWord}&hostKeyWord=${hostKeyWord}&portKeyWord=${portKeyWord}">&raquo;</a>
														</li>
													</s:else>
													<li><a href="machinelist?pageNum=${page.totalPage }&keytype=${keytype}&nameKeyWord=${nameKeyWord}&hostKeyWord=${hostKeyWord}&portKeyWord=${portKeyWord}"><s:text name="page_last"></s:text></a></li>
												</ul>
											</td>
										</tr>
									</s:if>
								</tfoot>
							</table>
						</form>
					</div>
				</div>
			</div>
			<jsp:include page="centerRight.jsp"></jsp:include>
		</div>
		<div class="push"></div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<jsp:include page="../common/modal.jsp"></jsp:include>
</body>
<script type="text/javascript">
	function openSelect(id) {
		$('#shell').attr('href','machine_openShellTerminalPage?id=' + id);
		$('#sftp').attr('href','machine_openShellTerminalPage?id=' + id);
		$('#shell').prop('href','machine_openShellTerminalPage?id=' + id);
		$('#sftp').prop('href','machine_openSftpWindowPage?id=' + id);
		return true;
	}
</script>
</html>


