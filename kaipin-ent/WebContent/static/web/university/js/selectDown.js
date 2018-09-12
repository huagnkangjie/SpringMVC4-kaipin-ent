/*
 * 
 * fn ：下拉选择框（select）选值
 * 
 */

(function(){
	$(".the-areaOf-selectDown").find('.adress-select-txt').off().on("change",'select.selectDown',function(){
		 var $opt = $(this).find('option');
         $opt.each(function(i) {
             if ($opt[i].selected == true) {
                 txt = $opt[i].innerHTML;
             }
         });
         $(this).parents('.adress-select-txt').find('.ipt-txt span').html(txt);
	});
})();
