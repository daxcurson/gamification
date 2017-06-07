$(document).ready(function()
{
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