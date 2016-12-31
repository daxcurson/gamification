<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Estudiantes</h2>

<p>
<a href="${pageContext.request.contextPath}/estudiantes/add">Agregar nuevo estudiante</a>
</p>

<p>
A continuaci&oacute;n se listan los estudiantes. Para definir grupos y permisos, haga 
click <a href="${pageContext.request.contextPath}/users/index">aqu&iacute;</a>.
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
