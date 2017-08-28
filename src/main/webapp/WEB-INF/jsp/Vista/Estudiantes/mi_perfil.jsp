<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<h1>Mi perfil</h1>

<p>Mis puntos: <c:out value="${estudiante.puntos}" />
</p>

<p>Cursos tomados</p>

<table class="table">
<tr><th>C&oacute;digo Curso</th><th>Nombre</th><th>Aprobado</th><th>Puntos obtenidos</th></tr>
<c:forEach items="${mis_inscripciones}" var="inscripcion">
<tr>
<td><c:out value="${inscripcion.curso_oferta.curso.codigo_curso}"/></td>
<td><c:out value="${inscripcion.curso_oferta.curso.nombre}"/></td>
<td><c:if test="${inscripcion.aprobada == true }">
<img src="${pageContext.request.contextPath}/img/medalla.png" height="50" width="50"/>
</c:if>
</td>
<td><c:out value="${inscripcion.puntos}"/></td>
</tr>
</c:forEach>
</table>