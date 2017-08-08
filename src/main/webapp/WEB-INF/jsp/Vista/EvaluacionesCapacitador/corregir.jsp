<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2>Corregir examen</h2>

<%
// Aqui va la lista de preguntas del examen y las respuestas que dio el estudiante.
%>
<form:form commandName="correccion" method="post" action="${pageContext.request.contextPath}/evaluaciones_capacitador/corregir/${evaluacion_tomada.id}">
<form:input type="hidden" path="id" value="${id}"/>
<table class="table">
<tr><th>Estudiante</th><th>Curso</th><th>Fecha evaluaci&oacute;n</th></tr>
<tr>
<td><c:out value="${estudiante.nombre}"/></td>
<td><c:out value="${curso.nombre}"/></td>
<td><c:out value="${evaluacion_tomada.fecha_evaluacion}"/></td>
</tr>
</table>

<p>A continuaci&oacute;n se presentan las preguntas realizadas en el examen
y las respuestas dadas por el estudiante.</p>

<table class="table">
<tr><th>Pregunta</th><th>Respuesta</th><th>Comentario</th><th>Nota</th><th>Acciones</th></tr>
<c:forEach items="${evaluacion_tomada.respuestas}" var="respuesta">
	<tr>
		<c:choose>
			<c:when test="${empty respuesta.correcciones}">
				<td>${respuesta.pregunta.texto_pregunta}</td>
				<td>${respuesta.valor_respuesta}</td>
			</c:when>
			<c:otherwise>
				<td rowspan="${fn:length(respuesta.correcciones)}">
					${respuesta.pregunta.texto_pregunta}
				</td>
				<td rowspan="${fn:length(respuesta.correcciones)}">
					${respuesta.valor_respuesta}
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${empty respuesta.correcciones}">
				<td></td>
				<td></td>
			</c:when>
			<c:otherwise>
				<c:forEach items="${respuesta.correcciones}" var="correccion" varStatus="fila">
					<c:if test="${fila.index >=1}"><tr></c:if>
					<td>
						${correccion.comentarios}
					</td>
					<td>
						${correccion.nota_pregunta.nota_nombre}
					</td>
					<c:if test="${fila.index >=1}"></tr></c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<td><a href="${pageContext.request.contextPath}/evaluaciones_capacitador/mostrar_respuesta/${respuesta.id}">Corregir</a>
		<input type="submit" id="corregir" name="_eventId_corregir" value="Corregir"/>
		</td>
	</tr>
</c:forEach>
</table>
<fieldset>
<div class="form-group">
<form:label path="nota">Nota</form:label>
<form:input path="nota"/>
</div>
</fieldset>
<input type="submit" name="grabar_correccion" value="Grabar Correccion">
</form:form>