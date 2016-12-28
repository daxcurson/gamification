<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Configuraci&oacute;n</h2>

<p>Utilice estas opciones para configurar el sistema.</p>

<ul>
<li><a href="${pageContext.request.contextPath}/users/index">Administrar usuarios</a></li>
<li><a href="${pageContext.request.contextPath}/groups/index">Administrar grupos</a></li>
<li><a href="${pageContext.request.contextPath}/permisos/index">Administrar permisos</a></li>
</ul>