
$(document).on('click', '.submit', function () {
	var data = $(this).closest('form').serialize();

		console.log(data);

			$.ajax({
				url : 'test',
				type : 'POST',
				data : {data},
				success : function(responce){
					console.log(true);

				},
				error : function(){
					console.log("failed connection");
				}
			})

/*			$.post('/registration', data, function () {
			});*/
		});