<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="contanier-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Gamification</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}/menu"><span class="glyphicon glyphicon-home"></span>Pantalla Inicial</a></li>
				<sec:authorize access="hasRole('ROLE_ESTUDIANTES_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/estudiantes/"><span class="glyphicon glyphicon-user"></span>Asistentes</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_CURSOS_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/cursos/"><span class="glyphicon glyphicon-user"></span>Cursos</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_MIS_CURSOS')">
					<li><a href="${pageContext.request.contextPath}/cursos/mis_cursos"><span class="glyphicon glyphicon-user"></span>Mis Cursos</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_CAPACITADORES_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/capacitadores/"><span class="glyphicon glyphicon-user"></span>Capacitadores</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_INSCRIPCIONES_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/inscripciones/"><span class="glyphicon glyphicon-user"></span>Mis Inscripciones a Cursos</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_EVALUACIONES_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/evaluaciones/"><span class="glyphicon glyphicon-user"></span>Evaluaciones</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_EVALUACIONES_TOMADAS_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/evaluaciones_tomadas/"><span class="glyphicon glyphicon-user"></span>Mis Evaluaciones</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ESTUDIANTES_MI_PERFIL')">
					<li><a href="${pageContext.request.contextPath}/estudiantes/mi_perfil"><span class="glyphicon glyphicon-user"></span>Mi perfil</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_CONFIGURACION_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/configuracion/"><span class="glyphicon glyphicon-cog"></span>Configuraci&oacute;n</a></li>
				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="hasRole('ROLE_USER')">
					<li>
						<sec:authentication var="user" property="principal"/>
						<p class="navbar-text">
						Usuario: ${user.username} (${user.user.persona.nombre})
						</p>
					</li>
					<li>
						<c:url value="/users/logout" var="logoutUrl"/>
						<a href="${logoutUrl}"><span class="glyphicon glyphicon-off"></span>Salir</a>
					</li>
				</sec:authorize>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
