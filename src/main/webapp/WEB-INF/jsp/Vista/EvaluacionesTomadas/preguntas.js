$(document).ready(function()
{
	leer_preguntas();
	$("#form_evaluacion").conversationalForm({
		
	});
});
/**
 * Esta es una llamada Ajax para leer las preguntas desde el sistema
 * y presentar aqui con el codigo y atributos que se necesitan para mostrar
 * en el conversational form.
 * @returns
 */
function leer_preguntas()
{
	// Recibo un objeto de tipo pregunta. 
	$('div.pregunta_examen').each(function(index,d)
	{
		var id=$(d).attr("id").split("-");
		$.getJSON("${pageContext.request.contextPath}/evaluaciones_tomadas/pregunta/"+id[1],{},
		function(json)
		{
			if(json.tipo_pregunta.codigo=="COMPLETAR")
			{
				$('#pregunta-'+json.id).html(
						json.texto_pregunta+"<br/>"+
						'<input name="respuestas['+index+'].valor_respuesta" id="respuestas-'+index+'"/>');
			}
		}
		);
	}
	)
}
