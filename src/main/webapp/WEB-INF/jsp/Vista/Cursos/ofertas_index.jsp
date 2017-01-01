<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h2>Ofertas del curso ${curso.nombre}</h2>

<p>
<a href="${pageContext.request.contextPath}/cursos/ofertas_add/${curso.id}">Agregar nueva oferta</a>
</p>

<p>
Ofertas del curso <c:out value="${curso.codigo_curso}"/>-<c:out value="${curso.nombre}"/>
</p>

<table class="table">
<tr>
<th>Id</th>
<th>Fecha de Comienzo</th>
<th>Fecha de Fin</th>
<th>Acciones</th>
</tr>
<c:forEach items="${ofertas}" var="oferta">
<tr>
<td><c:out value="${oferta.id}"/></td>
<td>${oferta.fecha_comienzo}</td>
<td>${oferta.fecha_fin}</td>
<td>
<a href="${pageContext.request.contextPath}/cursos/oferta_edit/${oferta.id}">Editar</a> 
</td>
</tr>
</c:forEach>
</table>