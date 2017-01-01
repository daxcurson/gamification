<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2>Men&uacute; principal</h2>

<p>Bienvendo a la aplicaci&oacute;n Gamification. Haga click en alguna de las siguientes opciones para continuar.</p>

<ul>
<sec:authorize access="hasRole('ROLE_ESTUDIANTES_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/estudiantes/index">Estudiantes</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_CURSOS_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/cursos/index">Cursos</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_EVALUACIONES_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/evaluaciones/index">Evaluaciones</a></li>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_CONFIGURACION_MOSTRAR_MENU')">
<li><a href="${pageContext.request.contextPath}/configuracion/index">Configuraci&oacute;n</a></li>
</sec:authorize>
</ul>