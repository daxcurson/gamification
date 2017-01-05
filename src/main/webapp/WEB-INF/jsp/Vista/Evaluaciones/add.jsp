<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Evaluaciones/preguntas.js" %>
</script>

<form:form method="post" action="add" commandName="evaluacion">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<tiles:insertAttribute name="form_evaluacion"/>
	<input type="submit" name="agregar_evaluacion" value="Agregar Evaluacion">
</form:form>