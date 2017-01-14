<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Mis inscripciones a cursos</h1>

<p>
<a href="${pageContext.request.contextPath}/inscripciones/add">Inscribirse en curso</a>
</p>

<table class="table">
<tr><th></th><th>C&oacute;digo del Curso</th><th>Nombre del Curso</th><th>Fechas en oferta</th><th>Acciones</th></tr>
<c:forEach items="${inscripciones}" var="inscripcion">
<tr class="clickable" data-toggle="collapse" id="row1" data-target=".row1">
<td><i class="glyphicon glyphicon-plus"></i></td>
<td><c:out value="${inscripcion.curso_oferta.curso.codigo_curso}"/></td>
<td><c:out value="${inscripcion.curso_oferta.curso.nombre}"/></td>
<td><c:out value="${inscripcion.curso_oferta.fecha_comienzo}"/>-<c:out value="${inscripcion.curso_oferta.fecha_fin}"/></td><td><a href="${pageContext.request.contextPath}/evaluaciones_tomadas/add">Tomar Evaluaci&oacute;n</a></td>
</tr>
<tr class="collapse row1">
<td colspan="5">
<table class="table table-bordered">
<c:forEach items="${evaluaciones_tomadas}" var="evaluacion_tomada">
<tr><td>Intento: <c:out value="${evaluacion_tomada.fecha_evaluacion}"/></td><td>Calificacion</td><td>Evaluador</td></tr>
</c:forEach>
</table>
</td>
</tr>
</c:forEach>
</table>