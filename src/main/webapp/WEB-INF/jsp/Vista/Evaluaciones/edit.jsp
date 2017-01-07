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
	<input type="submit" name="editar_evaluacion" value="Editar Evaluacion">
</form:form>
<div id="DialogoPregunta">
	<form method="post" action="${pageContext.request.contextPath}/evaluaciones/agregar_pregunta">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<label for="tipo_pregunta" id="LabelTipoPregunta">Curso Ofrecido</label>
	<select name="tipo_pregunta" id="PreguntaTipoPregunta" class="form-control">
		<c:forEach items="${tipos_preguntas}" var="tipo">
			<option value="<c:out value="${tipo.id}"/>"><c:out value="${tipo.descripcion}"/></option>
		</c:forEach>
	</select>
	<label for="texto_pregunta" id="LabelPreguntaTextoPregunta">Texto de la pregunta</label>
	<input id="PreguntaTextoPregunta" class="form-control" name="texto_pregunta"/>
	</form>
</div>