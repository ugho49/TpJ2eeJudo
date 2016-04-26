$(document).ready(function () {
	$('.datepicker').pickadate({
	    selectYears: 20,
	    selectMonths: true,
	    format: "dd/mm/yyyy"
	});

	$('select').material_select();
	
	$('.delete').delay(3000).fadeOut(500, function () {
		$(this).remove();
	});
});
