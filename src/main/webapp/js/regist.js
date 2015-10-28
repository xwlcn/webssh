var b = false;
function checkNick(){
	b = false;
	var nick = $("#nick").val();
	if(nick.trim()==""){
		$("#nickMessage").html('昵称不能为空！');
	}else if(nick.length>20){
		$("#nickMessage").html('昵称长度不能超过20个字符！');
	}else{
		$.get("checkNick?t="+new Date(), {nick:nick}, function(data){
			data = jQuery.parseJSON(data);
			$("#nickMessage").html(data.code==useraction_register_nick_ok ? '<span style="color:green">'+data.msg+'<span>' : data.msg);
			b = data.code==useraction_register_nick_ok ? true : false;
		})
	}
}
var e = false;
function checkEmail(){
	e = false;
	var email = $("#email").val();
	if(email.trim()==""){
		$("#emailMessage").html('邮箱不能为空！');
		e = false;
	}else if(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)==false){
		$("#emailMessage").html('请填写正确的邮箱地址。');
		e = false;
	}else{
		$.get("checkEmail?t="+new Date(),{email:email},function(data){
			data = jQuery.parseJSON(data);
			if(data.code==useraction_register_email_ok){
				$("#emailMessage").html('<span style="color:green">'+data.msg+'</span>');
				e = true;
			}else{
				$("#emailMessage").html(data.msg);
				e = false;
			}
		});
	}
	e = false;
}
function checkPassword(){
	var pwd = $("#password").val();
	if(pwd.trim()==""){
		$("#passwordMessage").html('密码不能为空！');
		return false;
	}else if(pwd.length<6){
		$("#passwordMessage").html('密码长度必须是6到16位！');
		return false;
	}else{
		$("#passwordMessage").html('<br>');
		return true;
	}
}
$("#nick").focusout(function(){
	checkNick();
});
$("#email").focusout(function(){
	checkEmail();
});
$("#password").focusout(function(){
	checkPassword();
});

$("#submit").click(function(){
	var p = checkPassword();
	if(!e) checkEmail();
	if(!b) checkNick();
	if(e&&p&&b){
  		$(this).val("Loading...");
  		$(this).prop("disabled", true);
  		var data = $("#registForm").serialize();
  		$.post("register?t="+new Date(),data,function(data){
  			data = jQuery.parseJSON(data);
  			
  			$("#submit").prop("disabled",false);
  			$("#submit").prop("disabled","");
  			$("#submit").val('注册');
  			$("#message").html(data.msg);
  			//success
  			if(data.code==useraction_register_success){
  				$("#message").removeClass("alert alert-danger").addClass("alert alert-success");
  			}else{
  				$("#message").removeClass("alert alert-success").addClass("alert alert-danger");
  				//verify code error
  				if(data.code == useraction_register_verifycode_error){
  					$("#verifyCode").attr('src','../getImage?t='+new Date().getTime());
  					$("#verify").val('');
  				}
  			}
  			$("#myModal").modal('show');
  			setTimeout(function(){$("#myModal").modal('hide')},3000);
  			$(function () { 
  				$('#myModal').on('hide.bs.modal', function () {
  					//success
  					if(data.code==useraction_register_success){
  						window.location.href='user_loginPage';
  					}
  				});
  			 });
  		});
	}
});

function keyDown() {if(event.keyCode == 13) $("#submit").click();}