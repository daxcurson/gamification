<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<form:form method="post" action="edit" commandName="capacitador">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<form:input id="CapacitadorId" class="form-control" path="id"/>
	<tiles:insertAttribute name="form_capacitador"/>
	<input type="submit" name="editar_capacitador" value="Editar Capacitador">
</form:form>