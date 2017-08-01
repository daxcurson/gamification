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

<p>A continuaci&oacute;n se presentan las preguntas realizadas en el examen
y las respuestas dadas por el estudiante.</p>

<table>
<tr><th>Pregunta</th><th>Respuesta</th><th>Acciones</th></tr>
<c:forEach items="${evaluacion_tomada.respuestas}" var="respuesta">
<tr>
<td>${respuesta.pregunta.texto_pregunta}</td><td>${respuesta.valor_respuesta}</td>
<td>
<a href="${pageContext.request.contextPath}/evaluaciones_capacitador/mostrar_respuesta/${respuesta.id}">Corregir</a>
</td>
</tr>
</c:forEach>
</table>