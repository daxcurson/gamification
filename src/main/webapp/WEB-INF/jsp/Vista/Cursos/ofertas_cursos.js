$(document).ready(function()
{
	$.datepicker.setDefaults({
		showOn: "both",
		buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
		buttonText: "Calendario"
	});
// Agrego Datepickers a las fechas de comienzo y fin de curso.
$("#CursoOfertaFechaComienzo").datepicker({ dateFormat: "yy-mm-dd" });
$("#CursoOfertaFechaFin").datepicker({ dateFormat: "yy-mm-dd" });
});