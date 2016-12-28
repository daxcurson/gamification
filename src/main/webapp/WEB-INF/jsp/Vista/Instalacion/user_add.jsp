<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h2>Creaci&oacute;n del usuario administrador</h2>

<p>
Por favor seleccione un nombre de usuario y clave para el usuario administrador
del sistema. Luego podr&aacute; usar este usuario para crear configuraciones adicionales.
</p>

<form:form method="post" action="user_add" commandName="user">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="form-group">
<label for="UserUsername">Login</label>
<form:input id="UserUsername" class="form-control" path="username"/>
<form:errors path="username"/>
</div>
<form:errors path=""/>
<div class="form-group">
<form:label path="password">Clave</form:label>
<form:input type="password" id="UserPassword" class="form-control" path="password"/>
</div>
<div class="form-group">
<form:label path="confirm_password">Confirmar Clave</form:label>
<form:input type="password" id="UserConfirmPassword" class="form-control" path="confirm_password"/>
</div>
        <input type="submit" value="Crear usuario"/>
</form:form>
