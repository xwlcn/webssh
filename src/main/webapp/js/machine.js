var ci;
$("#updateModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});
$(function(){
	$('#deleteModal').modal({backdrop: 'static', keyboard: false, show:false});
})
$("#keytype").change(function(){
	switch($(this).val()) {
	case '0':
		$('#keyword').attr('name','nameKeyWord');
		break;
	case '1':
		$('#keyword').attr('name','hostKeyWord');
		break;
	case '2':
		$('#keyword').attr('name','portKeyWord');
		break;
	}
});
function selAll(){
	$('input[name="ids"]').each(function(){
		this.checked = true;
	});
}
function deSelAll(){
	$('input[name="ids"]').each(function(){
		this.checked = !this.checked;
	});
}
function deleteSel(){
	var data = $('form[name="machineForm"]').serialize();
	$.post('deleteMachines', data, function(data){
		showMsg(jQuery.parseJSON(data));
	});
}
function cancel(){
	$(ci).parent().parent().find("input[name='ids']")[0].checked = false;
}
$(document).ready(function(){
	
	$("a[name='del']").each(function(){
		$(this).click(function(){
			ci = this;
			$(this).parent().parent().find("input[name='ids']")[0].checked = true;
		});
	});
})
$("#btnSub").click(function(){
	var data = $('#updateForm').serialize();
	$.post('updateMachine',data,function(data){
		showMsg(jQuery.parseJSON(data));
	})
});
function showMsg(data){
	$('#message').html(data.msg);
	//success
	if (data.code == machineaction_deletemachines_success || data.code == machineaction_updatemachine_success) {
		$("#message").removeClass("alert alert-danger").addClass("alert alert-success");
	}
	//failed
	else {
		$("#message").removeClass("alert alert-danger").addClass("alert alert-danger");
	}
	$("#msgModal").modal('show');
	setTimeout(function(){location.reload();},1000);
}