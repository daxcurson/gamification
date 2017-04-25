<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Capacitadores</h2>

<p>
<a href="${pageContext.request.contextPath}/capacitadores/add">Agregar nuevo capacitador</a>
</p>

<p>
A continuaci&oacute;n se listan los capacitadores. Para definir grupos y permisos, haga 
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
<c:forEach items="${capacitadores}" var="capacitador">
<tr>
<td><c:out value="${capacitador.id}"/></td>
<td>${capacitador.nombre}</td>
<td>${capacitador.user!=null ? "S&iacute;":"No" }</td>
<td>${capacitador.habilitada==1 ? "S&iacute;":"No"}</td>
<td><a href="${pageContext.request.contextPath}/capacitadores/edit/${capacitador.id}">Editar</a></td>
</tr>
</c:forEach>
</table>
