<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<fieldset>
<div class="form-group">
<form:label path="descripcion" id="LabelUserGroup">Descripci&oacute;n de la Evaluaci&oacute;n</form:label>
<form:input id="EvaluacionDescripcion" class="form-control" path="descripcion"/>
<form:errors path="descripcion"/>
</div>
<div class="form-group">
<form:label path="curso_oferta" id="LabelEvaluacionCursoOferta">Curso Ofrecido</form:label>
<form:select path="curso_oferta" id="EvaluacionCursoOferta" class="form-control">
<form:options items="${cursos_ofertas}" itemValue="id" itemLabel="curso.codigo_curso" />
</form:select>
<form:errors path="curso_oferta"/>
</div>
<p>Preguntas:</p>
<a href="${pageContext.request.contextPath}/evaluaciones/add_pregunta">Agregar pregunta</a>
<div id="Preguntas">
<table class="table">
<tr><th>Elegir</th><th>Tipo Pregunta</th><th>Texto Pregunta</th><th>Opciones pregunta</th></tr>
<c:choose>
	<c:when test="${not empty preguntas}">
		<c:forEach items="${preguntas}" var="pregunta">
			<tr>
			<td><input type="checkbox" name="preguntas[${pregunta.id}]" value="${pregunta.id}" /></td>
			<td><c:out value="${pregunta.tipoPregunta.descripcion}" /></td>
			<td><c:out value="${pregunta.texto_pregunta}" /></td>
			<td>
			<c:if test="${preguntas.tipoPregunta.codigo=='CMAGNET'}">
				<c:out value="${pregunta.texto_ordenar}"/>
			</c:if>
			</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr><td colspan="5" style="text-align:center">-- No hay Preguntas --</td></tr>
	</c:otherwise>
</c:choose>
</table>
</div>
</fieldset>