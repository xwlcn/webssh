$("#submit").click(function(){
	var data = $("#loginForm").serialize();
	var old = $(this).val();
	$(this).val("Login...");
	$(this).prop("disabled", true);
	$.post("login", data, function(data){
		data = jQuery.parseJSON(data);
		$("#submit").prop("disabled",false);
		$("#submit").prop("disabled","");
		$("#submit").val(old);
		if(data.code!=useraction_login_success){
			$("#verifyCode").attr('src','../getImage?t='+new Date().getTime());
			$("#verify").val('');
			$("#message").html(data.msg);
			$("#myModal").modal('show');
  			setTimeout(function(){$("#myModal").modal('hide')},3000);
		}else{
			window.location="../index";
		}
	});
});