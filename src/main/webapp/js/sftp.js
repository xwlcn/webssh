$(document).ready(function(){
	$('#message').html('Sftp file loading...');
	$("#msgModal").modal('show');
	$id = $('#mid').val();
	
	$.getJSON('connectSftp?id=' + $id, function(data){
		showFiles(data);
	});
	/* $("#message").removeClass("alert alert-danger").addClass("alert alert-danger");
	$('#message').html('loading error.');
	$("#msgModal").modal('hide'); */

});
$("#attrModal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
    $('input[type=checkbox]').each(function(){
    	$(this).removeAttr('checked');
    })
    $('#permissions').val('');
});
$('input[type=checkbox]').click(function(){
	$p = 0; 
	$('input[type=checkbox]').each(function(){
		if ($(this).is(':checked'))
			$p += parseInt($(this).val());
	 });
	$('#permissions').val($p);
})
function initAttr(p, fileName) {
	$('#attrModalLabel').html(fileName);
	$('#subAttr').attr('onclick',"setAttr('"+fileName+"')");
	p = parseInt(p);
	$('#permissions').val(p.toString(8).slice(-3));
	if (p%2==1) {
		$('#othE').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#othW').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#othR').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#grpE').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#grpW').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#grpR').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#usrE').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#usrW').prop('checked',true)
	}
	p = p>>1;
	if (p%2==1) {
		$('#usrR').prop('checked',true)
	}
}
function downLoadFile(fileName) {
	var form = $("<form>");
    form.attr('style', 'display:none');
    form.attr('target', '_blank');
    form.attr('method', 'post');
    form.attr('action', "downloadFile");

    var input1 = $('<input>');
    input1.attr('type', 'hidden');
    input1.attr('name', 'fileFileName');
    input1.attr('value', fileName);
    $('body').append(form);
    form.append(input1);
    form.submit();
}

function refreshProgress() {
	$.get('uploadState?t=' + new Date().getTime(),function(data){
		data = jQuery.parseJSON(data);
		$('.progress-bar').css('width',data.percent);
		$('#percent').html(data.percent);
		$('#state').html('current percent:' + data.percent);
		if (data.percent != '100.00%')
			setTimeout("refreshProgress()", 1000);
	});
};
//create new fodder
$('#subCreate').click(function(){
	$fName = $('#fName').val();
	if ($.trim($fName)=="") {
		alert("Fodder name is empty!");
		return;
	}
	exec('mkdir', $fName);
})
//upload file
$('#subFile').click(function(){
	$.ajaxFileUpload({
        url: 'execCommand?cmd=upload', 
        type: 'post',
        secureuri: false,
        fileElementId: 'file',
        dataType: 'json',
        elementIds: '',
        success: function(data, status){  
        	$('#message').html('Sftp file loading...');
        	$("#msgModal").modal('show');
        	showFiles(data);
        },
        error: function(data, status, e){ 
        	$('#progress').html("upload failed.");
        }
    });
	refreshProgress();
});
function setAttr(fileName) {
	$('#message').html('Sftp file loading...');
	$("#msgModal").modal('show');
	$.post('execCommand', {'cmd':'attr', 'fileFileName':fileName, 'permissions':$('#permissions').val()}, function(data){
		showFiles(jQuery.parseJSON(data));
	});
}
function exec(cmd, cmdParam) {
	$('#message').html('Sftp file loading...');
	$("#msgModal").modal('show');
	$.post('execCommand', {'cmd':cmd, 'cmdParam':cmdParam}, function(data){
		showFiles(jQuery.parseJSON(data));
	});
};
function initRemoveModal(fileName) {
	$('#subRemove').attr("onclick", "exec('rm','"+fileName+"')");
}
function showFiles(data) {
	if (data.code == -1 || data.code == machineaction_connectsftp_failed || 
			data.code == machineaction_connectsftp_sys_error ||
			data.code == machineaction_execcommand_error) {
		$("#message").removeClass("alert alert-danger").addClass("alert alert-danger");
		$('#message').html(data.msg);
		setTimeout(function(){$("#msgModal").modal('hide');}, 2000);
		return ;
	}
	
	$('#catalog').html(data.currentCatalog);
	
	if (data != undefined && data != "")
		$('#file-list').empty();
	
	if (data.currentCatalog != "/") {
		$tr = '<tr><td>&nbsp;<span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;　<a href="javascript:void(0)" onclick="exec(\'cd\',\'..\')">..</a></td><td></td><td></td><td></td><td></td></tr>'
		$('#file-list').append($tr);
	}

	$(data.files).each(function(i, e){
		if (e.directory) {
			$tr = '<tr><td>&nbsp;<span class="glyphicon glyphicon-folder-open"></span>&nbsp;　<a href="javascript:void(0)" onclick="exec(\'cd\',\''+e.filename+'\')">' +e.filename+ '</a></td><td></td><td>'+e.mtime+'</td><td>'+e.strPermissions+'</td><td><a class="glyphicon glyphicon-edit pull-right" href="javascript:void(0)" data-toggle="modal" data-target="#attrModal" title="attributes" style="margin-right:10px;color:blue;outline:none" onclick="initAttr(\''+e.intPermissions+'\',\''+e.filename+'\')"></a></td></tr>';
		} else {
			$tr = '<tr><td>&nbsp;<span class="glyphicon glyphicon-file"></span>　'+e.filename+'</td><td>'+e.size+'</td><td>'+e.mtime+'</td><td>'+e.strPermissions+'</td><td><a class="glyphicon glyphicon-trash pull-right" data-toggle="modal" data-target="#removeModal" href="javascript:void(0)" title="delete" style="color:red;outline:none" onclick="initRemoveModal(\''+e.filename+'\')"></a><a class="glyphicon glyphicon-download-alt pull-right" href="javascript:void(0)" title="download" style="margin-right:10px;color:#FF9933;outline:none" onclick="downLoadFile(\''+e.filename+'\')"></a><a class="glyphicon glyphicon-edit pull-right" href="javascript:void(0)" data-toggle="modal" data-target="#attrModal" title="attributes" style="margin-right:10px;color:blue;outline:none" onclick="initAttr(\''+e.intPermissions+'\',\''+e.filename+'\')"></a></td></tr>';
		}
		$('#file-list').append($tr);
	});
	$("#msgModal").modal('hide');
}