$(document).ready(function()
{
	var workspace = Blockly.inject('blocklyDiv',
			{
		toolbox: document.getElementById('toolbox'),
		media: "${pageContext.request.contextPath}/js/blockly/media"
			});
});
$('#form_evaluacion').on('submit',function()
{
	// Recorremos los renglones, en el orden que esten, y los agregamos como texto
	// a un hidden para que despues se envie con el form.
	var texto="";
	$('#pregunta-${preguntaNro.index} li').each(function(index)
	{
		texto+=$(this).find("div").html();
	});
	$('#respuestas-${preguntaNro.index}').val(texto);
	return true;
}
);