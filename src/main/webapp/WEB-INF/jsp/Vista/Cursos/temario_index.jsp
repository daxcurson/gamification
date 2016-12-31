<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Temario del curso ${curso.nombre}</h2>

<p>
<a href="${pageContext.request.contextPath}/cursos/temario_add/${curso.id}">Agregar nuevo tema</a>
</p>

<p>
Temas del curso <c:out value="${curso.codigo_curso}"/>-<c:out value="${curso.nombre}"/>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Nombre</th>
<th>Descripci&oacute;n</th>
<th>Acciones</th>
</tr>
<c:forEach items="${temario}" var="tema">
<tr>
<td><c:out value="${tema.id}"/></td>
<td>${tema.nombre}</td>
<td>${tema.descripcion}</td>
<td>
<a href="${pageContext.request.contextPath}/cursos/temario_edit/${tema.id}">Editar</a> 
</td>
</tr>
</c:forEach>
</table>
