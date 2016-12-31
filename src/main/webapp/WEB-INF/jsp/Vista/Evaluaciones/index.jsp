<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Evaluaciones</h2>

<p>
<a href="${pageContext.request.contextPath}/evaluaciones/add">Agregar nueva Evaluaci&oacute;n</a>
</p>

<p>
Evaluaciones sobre temas de los cursos. Estas evaluaciones pueden ser tomadas
por los estudiantes cuando hacen un curso. Cada una de estas evaluaciones corresponde
a un curso ofrecido. Para distintos cursos ofrecidos, pueden haber distintas evaluaciones.
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Descripci&oacute;n</th>
<th>C&oacute;digo del Curso</th>
<th>Acciones</th>
</tr>
<c:forEach items="${evaluaciones}" var="evaluacion">
<tr>
<td><c:out value="${evaluacion.id}"/></td>
<td>${evaluacion.descripcion}</td>
<td>${evaluacion.curso_oferta.curso.codigo_curso}</td>
<td><a href="${pageContext.request.contextPath}/evaluaciones/edit/${evaluacion.id}">Editar</a></td>
</tr>
</c:forEach>
</table>