function clock(){
    var showtime=document.getElementById("time");
    var thisDay=new Date();
    var year=thisDay.getFullYear();
    var month=thisDay.getMonth()+1;//在js中月份从0开始，因此要加1
    var day=thisDay.getDate();//获取日时getDate，原本还以为时getDay，getDay时获得星期几
    var h=thisDay.getHours();
    var m=thisDay.getMinutes();
    var s=thisDay.getSeconds();
    if(month<10)month="0"+month;
    if(day<10)day="0"+day;
    if(h<10)h="0"+h;
    if(m<10)m="0"+m;
    if(s<10)s="0"+s;
    var strtime="当前的系统时间是："+year+"年"+month+
    "月"+day+"日  "+h+"时"+m+"分"+s+"秒";
    showtime.innerHTML=strtime;
    setTimeout("clock()", 1000);
}
$().ready(function(){
	clock();
});