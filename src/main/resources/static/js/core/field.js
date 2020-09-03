$(function() {
    	  $('.container').stop().addClass('active');
    	});

    	$('.close').on('click', function() {
    	  $('.container').stop().removeClass('active');
    	});

/*$('#submit').click(function(e) {
    e.preventDefault();
	alert("push");
	return false;
});*/