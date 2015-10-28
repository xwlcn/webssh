$('#sub').click(function(){
	var data = $('#udpForm').serialize();
	$.post('updatePassword',data,function(data){
		data = jQuery.parseJSON(data);
		$('#msg').html(data.msg);
		if(data.code == useraction_updatepassword_success) {
			setTimeout("window.location = '../index';",1500);
		}
	});
});