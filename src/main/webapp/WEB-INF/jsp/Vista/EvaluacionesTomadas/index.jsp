<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Mis evaluaciones tomadas</h1>

<p>Estos son los resultados de las evaluaciones tomadas.</p>

<table class="table">
<tr><th>Fecha evaluaci&oacute;n</th><th>Nombre evaluaci&oacute;n</th><th>Curso</th><th>Calificaci&oacute;n</th><th>Capacitador</th><th>Acciones</th></tr>
<c:forEach items="${evaluaciones_tomadas}" var="evaluacion_tomada">
<c:choose>
		<c:when test="${not empty evaluacion_tomada.correcciones}">
			<c:forEach items="${evaluacion_tomada.correcciones}" var="correccion">
				<tr>
					<td><c:out value="${evaluacion_tomada.fecha_evaluacion}"/></td>
					<td><c:out value="${evaluacion_tomada.evaluacion.descripcion}"/></td>
					<td><c:out value="${evaluacion_tomada.inscripcion.curso_oferta.curso.nombre}"/></td>
					<td><c:out value="${correccion.nota}"/></td>
					<td><c:out value="${correccion.capacitador.nombre}"/></td>
					<td><a href="${pageContext.request.contextPath}/evaluacion_tomada/ver_correccion/${correccion.id}">Ver correcci&oacute;n</a></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td><c:out value="${evaluacion_tomada.fecha_evaluacion}"/></td>
				<td><c:out value="${evaluacion_tomada.evaluacion.descripcion}"/></td>
				<td><c:out value="${evaluacion_tomada.inscripcion.curso_oferta.curso.nombre}"/></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:otherwise>
</c:choose>
</c:forEach>
</table>