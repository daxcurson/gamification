<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
// En el multiple choice, el cuadro de opciones se interpreta de a una opcion por linea.
%>

<c:out value="${pregunta.texto_pregunta}"/><br/>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<c:forTokens items="${pregunta.texto_ordenar}" delims="${newLineChar}" var="linea">
<form:radiobutton path="respuestas[${preguntaNro.index}].valor_respuesta" value="${linea}" id="respuestas-${preguntaNro.index}"/><c:out value="${linea}"/><br/>
</c:forTokens>
<form:input path="respuestas[${preguntaNro.index}].pregunta" type="hidden" value="${pregunta.id}"/>