<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Mis inscripciones a cursos</h1>

<p>
<a href="${pageContext.request.contextPath}/inscripciones/add">Inscribirse en curso</a>
</p>

<table class="table">
<tr><th></th><th>C&oacute;digo del Curso</th><th>Nombre del Curso</th><th>Fechas en oferta</th></tr>
<c:forEach items="${inscripciones}" var="inscripcion">
<tr class="clickable" data-toggle="collapse" id="row1" data-target=".row1">
<td><i class="glyphicon glyphicon-plus"></i></td>
<td><c:out value="${inscripcion.curso_oferta.curso.codigo_curso}"/></td>
<td><c:out value="${inscripcion.curso_oferta.curso.nombre}"/></td>
<td><c:out value="${inscripcion.curso_oferta.fecha_comienzo}"/>-<c:out value="${inscripcion.curso_oferta.fecha_fin}"/></td>
</tr>
<tr class="collapse row1">
<td colspan="5">
<table class="table table-bordered">
<c:forEach items="${inscripcion.curso_oferta.evaluaciones}" var="evaluacion_a_tomar">
<tr><td><c:out value="${evaluacion_a_tomar.descripcion}"/></td><td>Calificacion</td><td>Evaluador</td><td><a href="${pageContext.request.contextPath}/evaluaciones_tomadas/${inscripcion.id}/add/${evaluacion_a_tomar.id}">Tomar Evaluaci&oacute;n</a></td></tr>
</c:forEach>
</table>
</td>
</tr>
</c:forEach>
</table>