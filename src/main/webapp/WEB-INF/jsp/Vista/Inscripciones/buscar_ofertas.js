$(document).ready(function()
{
	$.datepicker.setDefaults({
		showOn: "both",
		buttonImage: "${pageContext.request.contextPath}/img/cal.gif",
		buttonText: "Calendario"
		});
	// Agrego Datepickers a las fechas de comienzo y fin de curso.
	$("#InscripcionFecha").datepicker({ dateFormat: "yy-mm-dd" });
	buscar_ofertas();
});
function tabla_ofertas(ofertas)
{
	var options="";
	$.each(ofertas,function(index,oferta)
	{
		options+='<option value="'+oferta.id+'">'+
		oferta.fecha_comienzo+'-'+oferta.fecha_fin+
		'</option>';
	}
	);
	$("#CursoOferta").html(options);
}
/**
 * Busca ofertas del curso elegido
 */
function buscar_ofertas()
{
	var curso_id=$('#Curso').val();
	var url="${pageContext.request.contextPath}/inscripciones/listar_ofertas/"+curso_id;
	$.getJSON(url,
	{
	},
	function(ofertas)
	{
		if(ofertas!==null)
		{
			tabla_ofertas(ofertas);
		}
	}
	);
}