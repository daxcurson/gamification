$(document).ready(function()
{
	$('#DialogoPregunta').dialog({
		//position: {my: "top", at: "top", of: comentarios_estudiante},
		autoOpen: false,
		modal: true,
		buttons: [
		{
			text:"Agregar Pregunta",
			click: function()
			{
				// Accion del boton click. Realizo la llamada para agregar el comentario.
				agregar_pregunta_llamar_sistema();
				alert('Pregunta agregada!');
				leer_preguntas();
				$('#DialogoPregunta').dialog('close');
			}
		},
		{
			text:"Cerrar",
			click: function()
			{
				// No hago nada. Cierro el dialogo.
				$('#DialogoPregunta').dialog('close');
			}
		}
		]
	});
	leer_preguntas();
});
/**
 * Hace una llamada ajax para agregar comentarios, estoy a full con esto de las llamadas
 * Ajax!!!
 * @returns
 */
function agregar_pregunta_llamar_sistema()
{
	// Aca hago la llamada ajax al sistema!!!
	// Busco del dialogo el persona_id
	var persona_id=$('#ComentariosPersonaId').val();
	$.post("${pageContext.request.contextPath}/evaluaciones/agregar_pregunta",
			{
				pregunta: $('#ComentariosComentarios').val()
			},
			function(data) // Si agregar la pregunta tuvo exito
			{
			});
}
/**
 * Esta es una llamada Ajax para refrescar la lista de preguntas
 * @returns
 */
function leer_preguntas()
{
}