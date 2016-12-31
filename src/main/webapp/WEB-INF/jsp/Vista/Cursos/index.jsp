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
<th>Nombre</th>
<th>Es usuario?</th>
<th>Habilitada</th>
<th>Acciones</th>
</tr>
<c:forEach items="${estudiantes}" var="estudiante">
<tr>
<td><c:out value="${estudiante.id}"/></td>
<td>${estudiante.nombre}</td>
<td>${estudiante.user!=null ? "S&iacute;":"No" }</td>
<td>${estudiante.habilitada==1 ? "S&iacute;":"No"}</td>
<td><a href="${pageContext.request.contextPath}/estudiantes/edit/${estudiante.id}">Editar</a></td>
</tr>
</c:forEach>
</table>
