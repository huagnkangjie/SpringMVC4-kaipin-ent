(function () {
    document.addEventListener('DOMContentLoaded', function () {
        var html = document.documentElement;
        var windowWidth = html.clientWidth; 
      	if(windowWidth>=750){
        	windowWidth = 320;
        }
        html.style.fontSize = windowWidth * 100 / 750 + 'px';
    }, false);
})();