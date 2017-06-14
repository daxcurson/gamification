$(document).ready(function()
{
	var workspace = Blockly.inject('blocklyDiv',
			{
		toolbox: document.getElementById('toolbox'),
		media: "${pageContext.request.contextPath}/js/blockly/media/"
			});
});
$('#form_evaluacion').on('submit',function()
{
	var code = Blockly.PHP.workspaceToCode(workspace);
	$('#respuestas-${preguntaNro.index}').val(code);
	return true;
}
);