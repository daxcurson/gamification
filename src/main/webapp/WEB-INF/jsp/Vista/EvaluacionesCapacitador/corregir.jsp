<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Corregir examen</h2>

<%
// Aqui va la lista de preguntas del examen y las respuestas que dio el estudiante.
%>

<table class="table">
<tr><th>Estudiante</th><th>Curso</th><th>Fecha evaluaci&oacute;n</th></tr>
<tr>
<td><c:out value="${estudiante_pedido.nombre}"/></td>
<td><c:out value="${curso.nombre}"/></td>
<td><c:out value="${evaluacion_tomada.fecha_evaluacion}"/></td>
</tr>
</table>