<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%
// En el completar parrafo, por cada ____ que encuentre, voy a poner un input. 
%>

<c:out value="${pregunta.texto_pregunta}"/><br/>
<c:forTokens items="${pregunta.texto_ordenar}" delims=" " var="palabra">
<c:choose>
	<c:when test="${palabra=='_'}">
		<form:input path="respuestas[${preguntaNro.index}].valor_respuesta" id="respuestas-${preguntaNro.index}"/>
	</c:when>
	<c:otherwise>
		<c:out value="${palabra}"/>
	</c:otherwise>
</c:choose>

</c:forTokens>
<form:input path="respuestas[${preguntaNro.index}].pregunta" type="hidden" value="${pregunta.id}"/>