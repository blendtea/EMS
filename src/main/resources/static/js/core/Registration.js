$(document).on('click', '.registration .submission', function () {
	var data = $(this).closest('form').serialize();

		console.log(data);

			$.post('/registration', data, function () {
			});
		});
