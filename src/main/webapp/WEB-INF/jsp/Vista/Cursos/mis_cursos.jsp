<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Mis cursos asignados</h2>

<table class="table">
<tr><th>Id curso</th><th>C&oacute;digo Curso</th><th>Nombre Curso</th><th>Acciones</th></tr>
<c:forEach items="${mis_cursos}" var="curso">
<tr>
<td><c:out value="${curso.id}"/></td><td><c:out value="${curso.codigo_curso}"/></td>
<td><c:out value="${curso.nombre}"/></td>
<td>
<a href="${pageContext.request.contextPath}/evaluaciones_capacitador/listar/${curso.id}">Ex&aacute;menes</a> 
</td>
</tr>
</c:forEach>
</table>