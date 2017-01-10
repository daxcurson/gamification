<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<script type="text/javascript">
<%@include file="/WEB-INF/jsp/Vista/Evaluaciones/preguntas.js" %>
</script>

<form:form method="post" action="edit" commandName="evaluacion">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:input id="EvaluacionId" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_evaluacion"/>
	<tiles:insertAttribute name="form_preguntas"/>
	<input type="submit" name="editar_evaluacion" value="Editar Evaluacion">
</form:form>