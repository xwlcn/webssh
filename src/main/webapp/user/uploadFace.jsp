<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=path %>/css/style.css">
	<script src="<%=path %>/js/jquery-2.1.4.min.js"></script>
	<script src="<%=path %>/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		#preview{height:150px;width:150px;overflow:hidden;}
		#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
	</style>
	<script type="text/javascript">
 
 
                //图片上传预览    IE是用了滤镜。
        function previewImage(file)
        {
          var MAXWIDTH  = 260; 
          var MAXHEIGHT = 180;
          var div = document.getElementById('preview');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=imghead>';
              var img = document.getElementById('imghead');
              img.style = 'height:150px;width:150;border:1px solid silver;';
              $('#imghead').addClass('img-circle');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  150;
                img.height =  150;
//                 img.style.marginLeft = rect.left+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div class='img-circle' id=divhead style='height:150px;width:150;border:1px solid silver;"+sFilter+src+"\"'></div>";
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                 
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
             
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
</script> 
<title><s:text name="uploadface_title"></s:text></title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="../common/header.jsp"></jsp:include>
		<div class="blank"></div>
		<div class="container">
			<div class="col-md-9">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title"><s:text name="uploadface_panel_title"></s:text></h3>
					</div>
					<div class="panel-body" id="left-body" style="min-height:700px">
						<s:form namespace="/user" action="file_uploadFace" method="post" enctype="multipart/form-data">
							<s:debug></s:debug>
							<s:token></s:token>
							<s:textfield name="id" type="hidden" value="%{#session.user.id}"></s:textfield>
							<div id="preview">
								<img class="img-circle" style="height:150px;width:150px;border:1px solid silver;" 
									src="<%=path%>${sessionScope.user.facePath}">
							</div>
							<div class="form-group">
								<label for="inputfile"><s:text name="uploadface_label_upload"></s:text></label>
								<s:file name="file" onchange="previewImage(this)"></s:file>
								<p class="help-block"><s:text name="uploadface_attention"></s:text></p>
								<s:fielderror></s:fielderror>
								<p style="color:red">${requestScope.message }</p>
							</div>
							
							<input class="btn btn-success" type="submit" style="width:200px" value="<s:text name="uploadface_btn_upload"></s:text>"/>
						</s:form>
					</div>
				</div>
			</div>
			<jsp:include page="centerRight.jsp"></jsp:include>
		</div>
		<div class="push"></div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>