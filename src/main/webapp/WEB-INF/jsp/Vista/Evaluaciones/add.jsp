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
<div id="DialogoPregunta">
	<form:form commandName="pregunta" method="post" action="${pageContext.request.contextPath}/evaluaciones/agregar_pregunta">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:label path="tipo_pregunta" id="LabelTipoPregunta">Curso Ofrecido</form:label>
	<form:select path="tipo_pregunta" id="PreguntaTipoPregunta" class="form-control">
		<c:forEach items="${tipos_preguntas}" var="tipo">
			<option value="<c:out value="${tipo.id}"/>"><c:out value="${tipo.descripcion}"/></option>
		</c:forEach>
	</form:select>
	<form:label path="texto_pregunta" id="LabelPreguntaTextoPregunta">Texto de la pregunta</form:label>
	<form:input id="PreguntaTextoPregunta" class="form-control" path="texto_pregunta"/>
	</form:form>
</div>