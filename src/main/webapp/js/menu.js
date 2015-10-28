function writeCookie(name, value, hours)
{
  var expire = "";
  if(hours != null)
  {
    expire = new Date((new Date()).getTime() + hours * 3600000);
    expire = "; expires=" + expire.toGMTString();
  }
  document.cookie = name + "=" + escape(value) + expire;
}
function readCookie(name)
{
  var cookieValue = "";
  var search = name + "=";
  if(document.cookie.length > 0)
  { 
    offset = document.cookie.indexOf(search);
    if (offset != -1)
    { 
      offset += search.length;
      end = document.cookie.indexOf(";", offset);
      if (end == -1) end = document.cookie.length;
      cookieValue = unescape(document.cookie.substring(offset, end))
    }
  }
  return cookieValue;
}

$(function(){
	var a = readCookie('lastMenu');
	var c = $('.panel-group').find('.panel-collapse');
	for(var i=0;i<c.length;i++){
		if(a==i)
			$(c[i]).addClass('in');
		else
			$(c[i]).removeClass('in');
	}
	$('.panel-group').find('.panel-title a').each(function(n){
		$(this).click(function(){
			if(a!=n)
				writeCookie('lastMenu', n, 24);
			else
				writeCookie('lastMenu', -1, 24);
		})
	})
})