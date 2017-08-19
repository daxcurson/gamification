<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<% pageContext.setAttribute("newLineChar", "\n"); %>
<script>
$(document).ready(function()
{
	// Agregar atributos a los controles.
	<c:forTokens items="${pregunta.texto_ordenar}" delims="${newLineChar}" var="linea">
	$("#respuestas-${preguntaNro.index}").attr("cf-label","${linea}");
	</c:forTokens>
});
</script>

<%
// En el multiple choice, el cuadro de opciones se interpreta de a una opcion por linea.
%>

<c:out value="${pregunta.texto_pregunta}"/><br/>
<c:forTokens items="${pregunta.texto_ordenar}" delims="${newLineChar}" var="linea">
<form:radiobutton path="respuestas[${preguntaNro.index}].valor_respuesta" value="${linea}" id="respuestas-${preguntaNro.index}"/><br/>
</c:forTokens>
<form:input path="respuestas[${preguntaNro.index}].pregunta" type="hidden" value="${pregunta.id}"/>