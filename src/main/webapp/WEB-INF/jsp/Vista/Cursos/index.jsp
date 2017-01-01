<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Cursos</h2>

<p>
<a href="${pageContext.request.contextPath}/cursos/add">Agregar nuevo curso</a>
</p>

<p>
Estos son los cursos que se han creado. Para ellos se define un temario sobre el que
se pueden crear Preguntas, y definir Evaluaciones donde se formulen estas pregunatas
y se esperen respuestas. Luego estos cursos pueden ofrecerse para que los estudiantes
se inscriban, y rindan evaluaciones. Estas evaluaciones luego deber&aacute;n ser
corregidas por un Evaluador.
</p>

<table class="table">
<tr>
<th>Id</th>
<th>C&oacute;digo Curso</th>
<th>Nombre</th>
<th>Acciones</th>
</tr>
<c:forEach items="${cursos}" var="curso">
<tr>
<td><c:out value="${curso.id}"/></td>
<td>${curso.codigo_curso}</td>
<td>${curso.nombre}</td>
<td>
<a href="${pageContext.request.contextPath}/cursos/edit/${curso.id}">Editar</a> |
<a href="${pageContext.request.contextPath}/cursos/temario/${curso.id}">Temario</a> |
<a href="${pageContext.request.contextPath}/cursos/ofertas/${curso.id}">Ofertas</a> |
</td>
</tr>
</c:forEach>
</table>
