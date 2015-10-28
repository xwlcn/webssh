var e = true;
function getHigher(arr) {
	$higher = arr[0];
	for ($i = 0; $i < $col; $i++) {
		if(arr[$i] > $higher) {
			$higher = arr[$i];
		}
	}
	return $higher;
}
function init(){
	if(e){
		e = false;
		$('#book-opr').html('Close it!');
		//获取.panel-body宽度
		$width = $('#left-body').width();
		//计算每列个数
		$col = Math.floor($width / 200);
		//获取便签的个数
		$count = $('.remember').children('.note').length;
		var arr = {};
		$bottom = $('#top').height() + $('.hsplit').height() + 76;
		for ($i = 0; $i < $col; $i++) arr[$i] = $bottom;
		
		var notes = $('.remember').find('.note');
		var j = 0;
		function bianli(e){
			//当前div的高度
			$self_hight = $(e).height();
			//找到最低的那一列
			$lower = arr[0];
			$pos = 0;
			for ($i = 0; $i < $col; $i++) {
				if(arr[$i] < $lower) {
					$lower = arr[$i];
					$pos = $i;
				}
			}
			$left = 192 * $pos + 10;
			$top = $lower;
			$(e).css({'left':16+'px','top':$bottom+'px'});
			//更新最低那一列的高度
			arr[$pos] += $self_hight + 80;
			//最后记得更新.panel-body高度，否则便签将'撑破'.panel-body
			$higher = getHigher(arr);
			$('#left-body').animate({'height':$higher + 40},"fast");
			$(e).animate({"left": $left+"px", "top": $top+"px"}, "fast", function(){
				if(j < notes.size())
					bianli(notes[++j]);
			});

		}
		bianli(notes[j]);
		/*$('.remember').children('.note').each(function(){
			//当前div的高度
			$self_hight = $(this).height();
			//找到最低的那一列
			$lower = arr[0];
			$pos = 0;
			for ($i = 0; $i < $col; $i++) {
				if(arr[$i] < $lower) {
					$lower = arr[$i];
					$pos = $i;
				}
			}
			$left = 192 * $pos + 10;
			$top = $lower;
			$(this).css({'left':16+'px','top':$bottom+'px'});
			$(this).animate({"left": $left+"px", "top": $top+"px"}, 2000);
			//更新最低那一列的高度
			arr[$pos] += $self_hight + 80;
		});
		//最后记得更新.panel-body高度，否则便签将'撑破'.panel-body
		$higher = getHigher(arr);
		$('#left-body').css({'height':$higher + 40});*/
	}else{
		e = true;
		$('#book-opr').html('Open it!');
		$bottom = $('#top').height() + $('.hsplit').height() + 76;
		$('.remember').children('.note').each(function(){
			$(this).animate({"left": 16+'px', "top":$bottom+'px'}, 2000);
		});
		$('#left-body').delay(2000).animate({'height':700});
	}
}
$(document).ready(function(){});

$(window).resize(function(){e=false;init();});
