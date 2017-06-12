<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Ex&aacute;menes para corregir</h2>

<table class="table">
<tr><th>Nombre Estudiante</th><th>C&oacute;digo Curso</th><th>Fecha</th><th>Acciones</th></tr>
<c:forEach items="${evaluaciones_corregir}" var="evaluacion">
<tr>
<td><c:out value="${evaluacion.inscripcion.estudiante.nombre}"/></td>
<td><c:out value="${evaluacion.curso_oferta.curso.codigo_curso}"/></td>
<td><c:out value="${evaluacion.fecha_evaluacion}"/></td>
<td>
<a href="${pageContext.request.contextPath}/evaluaciones_capacitador/corregir/${evaluacion.id}">Corregir</a>
</td>
</tr>
</c:forEach>
</table>