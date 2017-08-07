<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<%
//<script type="text/javascript" src="https://cf-4053.kxcdn.com/conversational-form/0.9.6/conversational-form.min.js"></script>
%>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/EvaluacionesTomadas/preguntas.js" %>
</script>

<form:form method="post" commandName="evaluacion_tomada" id="form_evaluacion">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<tiles:insertAttribute name="form_evaluacion_tomada"/>
	<input type="submit" name="agregar_evaluacion" value="Entregar examen">
</form:form>