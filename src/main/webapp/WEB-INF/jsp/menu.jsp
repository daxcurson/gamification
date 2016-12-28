<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Agile Meetings</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}/menu"><span class="glyphicon glyphicon-home"></span>Pantalla Inicial</a></li>
				<sec:authorize access="hasRole('ROLE_PROYECTOS_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/proyectos/"><span class="glyphicon glyphicon-briefcase"></span>Proyectos</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_PERSONAS_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/personas/"><span class="glyphicon glyphicon-user"></span>Personas</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_REUNIONES_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/reuniones/"><span class="glyphicon glyphicon-thumbs-up"></span>Reuniones</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_CONFIGURACION_MOSTRAR_MENU')">
					<li><a href="${pageContext.request.contextPath}/configuracion/"><span class="glyphicon glyphicon-cog"></span>Configuraci&oacute;n</a></li>
				</sec:authorize>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">Nav header</li>
						<li><a href="#">Separated link</a></li>
						<li><a href="#">One more separated link</a></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="hasRole('ROLE_USER')">
					<li>
						<c:url value="/users/logout" var="logoutUrl"/>
						<a href="${logoutUrl}"><span class="glyphicon glyphicon-off"></span>Salir</a>
					</li>
				</sec:authorize>
			</ul>
		</div><!--/.nav-collapse -->
	</div>